package com.restaurant.restaurant.controller.admin.communityManage;

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

@WebServlet("/deleteReply")
public class DeleteReply extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String replyId = request.getParameter("replyId");
        AdminService adminService = new AdminService();
        response.getWriter().print(adminService.deleteReplyById(replyId));
    }
}
