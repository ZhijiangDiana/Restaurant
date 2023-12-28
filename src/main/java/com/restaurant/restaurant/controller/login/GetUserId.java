package com.restaurant.restaurant.controller.login;

import com.restaurant.restaurant.mapper.UserMapper;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.utils.FrontEndUtils;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.net.http.HttpClient;

@WebServlet("/getUserId")
public class GetUserId extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.setStatus(403);
            return;
        }
        int userId = user.getUserId();

        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        try (sqlSession) {
            user = userMapper.selectById(userId);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
            return;
        }

        response.getWriter().print(FrontEndUtils.objectToBody(null,"0",user));
    }
}
