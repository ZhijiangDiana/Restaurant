package com.restaurant.restaurant.ws;

import com.alibaba.fastjson.JSON;
import com.restaurant.restaurant.config.GetHttpSessionConfig;
import com.restaurant.restaurant.mapper.MessageMapper;
import com.restaurant.restaurant.mapper.UserMapper;
import com.restaurant.restaurant.pojo.entity.Message;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.utils.MessageUtils;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import com.restaurant.restaurant.ws.pojo.Info;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.apache.ibatis.session.SqlSession;
import org.codehaus.plexus.component.annotations.Component;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version v1.0
 * @ClassName: ChatEndpoint
 * @Description: TODO:之后要改 这里是username，餐厅的逻辑里session存的是user对象
 * @Author:
 */
@ServerEndpoint(value = "/chat",configurator = GetHttpSessionConfig.class)
public class ChatEndpoint {

    private static final Map<String,Session> onlineUsers = new ConcurrentHashMap<>();
    private static final Map<String,Session> relatedUsers = new ConcurrentHashMap<>();  // 和当前用户说过话的相关人的对象

    private HttpSession httpSession;

    /**
     * 建立websocket连接后，被调用
     * @param session
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        // TODO:如果我是刚上线的这里要判断是不是刚上线，那我就从数据库里读message了就不是从session里读了
        // 所有人如果打开会话框 都会进这个方法
        // 查数据库取出所有跟我聊过天的人的信息
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        MessageMapper mapper = sqlSession.getMapper(MessageMapper.class);

        // 能够成功拿到
        ServletContext servletContext = (ServletContext) session.getUserProperties().get("servletContext");
        List<User> userList = (List<User>) servletContext.getAttribute("userList");

        //1，将session进行保存
        this.httpSession = (HttpSession) session.getUserProperties().get("httpSession");
        String user = (String) this.httpSession.getAttribute("username");
        List<Message> messageMappers = mapper.selectAll();
        List<Message> messages = mapper.selectById(1);
        HashSet<String> friends = new HashSet<>();
        UserMapper mapper1 = sqlSession.getMapper(UserMapper.class);
        for (Message message : messages) {
            Integer senderUserId = message.getSenderUserId();
            Integer receiverUserId = message.getReceiverUserId();
            User sender_user = mapper1.selectById(senderUserId);
            User receiver_user = mapper1.selectById(receiverUserId);
            String senderName = sender_user.getUsername();
            String receiverName = receiver_user.getUsername();
            if (!senderName.equals(user)){
                friends.add(senderName);
            }
            if (!receiverName.contains(user)){
                friends.add(receiverName);
            }
        }
        sqlSession.close();

        // onlineUsers的列表可以从servletcontext里拿 不一定要进入这个聊天框，只要登录了餐厅管理就算在线
        onlineUsers.put(user,session);
        //2，广播消息。需要将登陆的所有的用户推送给所有的用户 不广播 只把和自己聊过天的人的信息展示
        // TODO:问题就在这里 我需要判断 1：我通过发送信息主动找别人  2：我通过小铃铛找别人
        String message = MessageUtils.getMessage(true,null,getFriends());
        broadcastAllUsers(message);
    }

    public Set getFriends() {
        Set<String> set = onlineUsers.keySet();
        return set;
    }

    private void broadcastAllUsers(String message) {
        try {
            //遍历map集合
            Set<Map.Entry<String, Session>> entries = onlineUsers.entrySet();
            for (Map.Entry<String, Session> entry : entries) {
                //获取到所有用户对应的session对象
                Session session = entry.getValue();
                //发送消息
                session.getBasicRemote().sendText(message);
            }
        } catch (Exception e) {
            //记录日志
        }
    }

    /**
     * 浏览器发送消息到服务端，该方法被调用
     *
     * 张三  -->  李四
     * @param message
     */
    @OnMessage
    public void onMessage(String message) {
        try {
            //将消息推送给指定的用户
            Info msg = JSON.parseObject(message, Info.class);
            //获取 消息接收方的用户名
            String toName = msg.getToName();
            String mess = msg.getMessage();
            String fromId = msg.getFromId();
            String toId = msg.getToId();

            SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user1 = userMapper.selectById(Integer.parseInt(fromId));
            String fromName = user1.getUsername();

            System.out.println("谁发：" +fromId + " 发给谁：" + toId + " 发了什么" + mess);  // 这里的fromid要从页面获取 这个和餐厅项目合并再做 这里模拟成1

            Set friends = getFriends();
            // 形成Message存入数据库中 TODO:判断狗叫 这里fromid是空所以会有问题
            MessageMapper mapper = sqlSession.getMapper(MessageMapper.class);
            Message newMessage = new Message(Integer.parseInt(fromId),Integer.parseInt(toId),new Date(),mess);
            mapper.insertMessage(newMessage);
            sqlSession.commit();
            sqlSession.close();

            //获取消息接收方用户对象的session对象
            Session sessionTo = onlineUsers.get(toName);
            Session sessionFrom = onlineUsers.get(fromName);
            String user = (String) this.httpSession.getAttribute("username");
            String msg1 = MessageUtils.getMessage(false, toName, mess);
            // jakarta.websocket.Session.getBasicRemote()" because "session" is null
            // 问题在于对方还没开聊天框 所以session是空。所以应该先存进数据库里。
            if (sessionTo == null){
                // 用于告知前端对方不在线 直接再调一次fetchMessage就行
                String msg2 = MessageUtils.getMessage(false, toId, 0);
                sessionFrom.getBasicRemote().sendText(msg2);
                // 这里不能调接收方session 因为是空的
            }
            else {
                sessionFrom.getBasicRemote().sendText(msg1);
                // 不然永远都是发送方发消息
                String msg3 = MessageUtils.getMessage(false,null,mess);
                sessionTo.getBasicRemote().sendText(msg3);
            }
            sqlSession.close();

        } catch (Exception e) {
            //记录日志
            e.printStackTrace();
        }
    }

    /**
     * 断开 websocket 连接时被调用
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
//        //1,从onlineUsers中剔除当前用户的session对象
//        String user = (String) this.httpSession.getAttribute("username");
//        onlineUsers.remove(user);
//        //2,通知其他所有的用户，当前用户下线了
//        String message = MessageUtils.getMessage(true,null,getFriends());
//        broadcastAllUsers(message);
    }
}
