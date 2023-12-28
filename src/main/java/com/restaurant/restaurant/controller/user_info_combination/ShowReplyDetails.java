package com.restaurant.restaurant.controller.user_info_combination;

import com.restaurant.restaurant.mapper.ReplyMapper;
import com.restaurant.restaurant.pojo.ReplyMessage;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.utils.FrontEndUtils;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.logging.jdbc.PreparedStatementLogger;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/showReplyDetails")
public class ShowReplyDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.setStatus(403);
            return;
        }

        ServletContext context = getServletContext();
        Map<Integer, Integer> replyCounts = (Map<Integer, Integer>) context.getAttribute("replyCounts");
        replyCounts.replace(user.getUserId(), 0);

        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try(sqlSession){
            ReplyMapper replyMapper = sqlSession.getMapper(ReplyMapper.class);
            List<ReplyMessage> replyMessages = replyMapper.selectDetails(user.getUserId());
            response.getWriter().print(FrontEndUtils.objectToBody(null,"0",replyMessages));
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.close();
            response.getWriter().print(FrontEndUtils.objectToBody("系统繁忙","1",null));
        }
    }
}
