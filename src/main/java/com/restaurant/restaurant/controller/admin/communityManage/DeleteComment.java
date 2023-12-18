package com.restaurant.restaurant.controller.admin.communityManage;

import com.restaurant.restaurant.service.AdminService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteComment")
public class DeleteComment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commentId = request.getParameter("commentId");
        AdminService adminService = new AdminService();
        response.getWriter().print(adminService.deleteCommentById(commentId));
    }
}
