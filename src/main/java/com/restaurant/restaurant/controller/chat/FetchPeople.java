package com.restaurant.restaurant.controller.chat;

import com.restaurant.restaurant.mapper.MessageMapper;
import com.restaurant.restaurant.mapper.UserMapper;
import com.restaurant.restaurant.pojo.entity.Message;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.utils.FrontEndUtils;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/fetchPeople")
public class FetchPeople extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try(sqlSession){
            MessageMapper messageMapper = sqlSession.getMapper(MessageMapper.class);
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            if (userId == null || userId == ""){
                List<User> users = userMapper.selectAll();
                response.getWriter().print(FrontEndUtils.objectToBody(null,"0",users));
            }
            else {
                // 拿出所有和我说过话的人
                List<Message> messages = messageMapper.selectById(Integer.parseInt(userId));

                Set<User> users = new HashSet<>();
                // 里面可能有重复数据用set去重  自己发的消息不算
                for (Message message : messages) {
                    Integer senderUserId = message.getSenderUserId();
                    Integer receiverUserId = message.getReceiverUserId();
                    if (senderUserId != Integer.parseInt(userId)) {
                        User user = userMapper.selectById(senderUserId);
                        users.add(user);
                    }
                    if (receiverUserId != Integer.parseInt(userId)) {
                        User user = userMapper.selectById(receiverUserId);
                        users.add(user);
                    }
                }
                sqlSession.close();
                response.getWriter().print(FrontEndUtils.objectToBody(null, "0", users));
            }
        }catch (Exception e){
            sqlSession.close();
            response.getWriter().print(FrontEndUtils.objectToBody("系统繁忙","1",null));
        }

    }

}
