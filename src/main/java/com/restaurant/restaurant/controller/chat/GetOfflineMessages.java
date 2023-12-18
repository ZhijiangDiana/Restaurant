package com.restaurant.restaurant.controller.chat;

import com.alibaba.fastjson.JSON;
import com.restaurant.restaurant.mapper.MessageMapper;
import com.restaurant.restaurant.mapper.UserMapper;
import com.restaurant.restaurant.pojo.entity.Message;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import com.restaurant.restaurant.ws.pojo.ResultMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet("/getOfflineMessages")
public class GetOfflineMessages extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer fromId = Integer.parseInt(request.getParameter("fromId"));
        Integer toId = Integer.parseInt(request.getParameter("toId"));
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        MessageMapper mapper = sqlSession.getMapper(MessageMapper.class);
        List<Message> messages = mapper.selectById(fromId);

        Iterator<Message> iterator = messages.iterator();
        while (iterator.hasNext()) {
            Message message = iterator.next();
            Integer receiverUserId = message.getReceiverUserId();
            Integer senderUserId = message.getSenderUserId();
            if (receiverUserId != toId && senderUserId != toId) {
                iterator.remove();
            }
        }


        List<ResultMessage> resultMessages = new ArrayList<>();
        for (Message message : messages) {
            String body = message.getBody();
            Integer senderUserId = message.getSenderUserId();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.selectById(senderUserId);
            String username = user.getUsername();
            ResultMessage resultMessage = new ResultMessage();
            // 用去前端区分谁在聊天框左谁在聊天框右
            if (senderUserId == fromId){
                resultMessage.setFromName(username);
            }
            resultMessage.setMessage(body);
            resultMessage.setSystem(false);
            resultMessages.add(resultMessage);
        }
        response.getWriter().print(JSON.toJSONString(resultMessages));


    }
}