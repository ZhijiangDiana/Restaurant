package com.restaurant.restaurant.controller.community;

import com.restaurant.restaurant.service.CommentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 点击用户名寻找指定用户获取全部评论
 */
@WebServlet("/searchUser")
public class SearchSpecificUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
        String id = requset.getParameter("id");
        System.out.println(id);
        CommentService commentService = new CommentService();
        response.getWriter().print(commentService.getUserInfoById(id));
    }
}
