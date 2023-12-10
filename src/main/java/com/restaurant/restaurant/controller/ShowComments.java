package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.HelloServlet;
import com.restaurant.restaurant.service.CommentService;
import com.restaurant.restaurant.utils.Constants;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/showComments")
public class ShowComments extends HelloServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 前端访问后面URL跟参数 分别代表 默认:0 按照热度:1 按照时间升序:2 按照时间降序:3
        String type = request.getParameter("type");
        System.out.println(type);
        CommentService commentService = new CommentService();
        String commentList = commentService.getCommentList(type);
        response.getWriter().print(commentList);
    }
}
