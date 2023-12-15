package com.restaurant.restaurant.ws;

import com.alibaba.fastjson.JSON;
import com.restaurant.restaurant.config.GetHttpSessionConfig;
import com.restaurant.restaurant.mapper.MessageMapper;
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
        sqlSession.close();

        // onlineUsers的列表可以从servletcontext里拿 不一定要进入这个聊天框，只要登录了餐厅管理就算在线

        onlineUsers.put(user,session);
        //2，广播消息。需要将登陆的所有的用户推送给所有的用户 不广播 只把和自己聊过天的人的信息展示
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
            // 形成Message存入数据库中 TODO:判断狗叫
            Message message1 = new Message(Integer.parseInt(fromId),Integer.parseInt(toId),new Date(),mess);
            SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
            MessageMapper mapper = sqlSession.getMapper(MessageMapper.class);

            //获取消息接收方用户对象的session对象
            Session session = onlineUsers.get(toName);
            String user = (String) this.httpSession.getAttribute("username");
            String msg1 = MessageUtils.getMessage(false, user, mess);
            session.getBasicRemote().sendText(msg1);
        } catch (Exception e) {
            //记录日志
        }
    }

    /**
     * 断开 websocket 连接时被调用
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        //1,从onlineUsers中剔除当前用户的session对象
        String user = (String) this.httpSession.getAttribute("username");
        onlineUsers.remove(user);
        //2,通知其他所有的用户，当前用户下线了
        String message = MessageUtils.getMessage(true,null,getFriends());
        broadcastAllUsers(message);
    }
}
