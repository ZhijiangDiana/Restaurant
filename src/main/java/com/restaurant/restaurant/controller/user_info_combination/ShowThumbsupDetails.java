package com.restaurant.restaurant.controller.user_info_combination;

import com.restaurant.restaurant.mapper.CommentMapper;
import com.restaurant.restaurant.mapper.UserMapper;
import com.restaurant.restaurant.pojo.ReplyMessage;
import com.restaurant.restaurant.pojo.entity.Comment;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.utils.FrontEndUtils;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(value = "/showThumbsupDetails",loadOnStartup = 1)
public class ShowThumbsupDetails extends HttpServlet {
    @Override
    public void init() throws ServletException {
        // 三个Integer分别代表：1.目前获取消息的对象的id  2.点赞的人id   3.点赞的标题id
        HashMap<Integer, Map<Integer,Integer>> thumbsupDetails = new HashMap<>();
        ServletContext servletContext = getServletContext();
        Map<Integer,Integer> info = new HashMap<>();
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try(sqlSession){
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userMapper.selectAll();
            for (User user : userList) {
                thumbsupDetails.put(user.getUserId(),info);
            }
            servletContext.setAttribute("thumbsupDetails",thumbsupDetails);
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        Integer userId = user.getUserId();
        HashMap<Integer, Map<Integer,Integer>> thumbsupDetails = (HashMap<Integer, Map<Integer, Integer>>) request.getServletContext().getAttribute("thumbsupDetails");
        Map<Integer, Integer> info = thumbsupDetails.get(userId);
        List<ReplyMessage> replyMessages = new ArrayList<>();
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try(sqlSession){
            for (Map.Entry<Integer, Integer> entry : info.entrySet()) {
                Integer fromId = entry.getKey();
                Integer commentId = entry.getValue();

                UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
                CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);

                User thumbsupUser = userMapper.selectById(fromId);
                String username = thumbsupUser.getUsername();

                Comment comment = commentMapper.selectById(commentId);
                String title = comment.getTitle();

                ReplyMessage replyMessage = new ReplyMessage(username,title,"点赞了");
                replyMessages.add(replyMessage);
            }
            response.getWriter().print(FrontEndUtils.objectToBody(null,"0",replyMessages));
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.close();
            response.getWriter().print(FrontEndUtils.objectToBody("系统繁忙","1",null));
        }
    }
}
