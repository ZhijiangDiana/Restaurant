package com.restaurant.restaurant.controller.community;

import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.service.CommentService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;

@WebServlet("/thumb")
public class Thumbup extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flag = request.getParameter("flag");
        String commentId = request.getParameter("commentId");
        System.out.println(flag + commentId);
        CommentService commentService = new CommentService();
        HashMap<Integer,Integer> thumbsupCounts = (HashMap<Integer, Integer>) request.getServletContext().getAttribute("thumbsupCounts");
        User user = (User) request.getSession().getAttribute("user");
        Integer userId = user.getUserId();
        if ("1".equals(flag))
            thumbsupCounts.put(userId,thumbsupCounts.get(userId) + 1);
        if ("0".equals(flag))
            thumbsupCounts.put(userId,thumbsupCounts.get(userId) - 1);

        response.getWriter().print(commentService.thumbup(flag, commentId));
    }
}
