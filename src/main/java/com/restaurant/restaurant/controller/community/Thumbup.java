package com.restaurant.restaurant.controller.community;

import com.restaurant.restaurant.service.CommentService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/thumb")
public class Thumbup extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flag = request.getParameter("flag");
        String commentId = request.getParameter("commentId");
        System.out.println(flag + commentId);
        CommentService commentService = new CommentService();
        response.getWriter().print(commentService.thumbup(flag, commentId));
    }
}
