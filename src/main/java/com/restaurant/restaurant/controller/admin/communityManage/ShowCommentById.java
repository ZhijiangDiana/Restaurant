package com.restaurant.restaurant.controller.admin.communityManage;

import com.restaurant.restaurant.mapper.UserMapper;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.service.AdminService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/showCommentById")
public class ShowCommentById extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commentId = request.getParameter("commentId");
        /*//测试
        commentId="1";*/
        AdminService adminService = new AdminService();
        String res = adminService.selectCommentById(commentId);
        response.getWriter().print(res);
    }
}