package com.restaurant.restaurant.controller.admin.communityManage;

import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.service.AdminService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/updateReply")
public class UpdateReply extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonObject = FrontEndUtils.bodyToJson(request);
        String replyId = jsonObject.getString("replyId");
        String commentId = jsonObject.getString("commentId");
        String userId = jsonObject.getString("userId");
        String canteenAdminId = jsonObject.getString("canteenAdminId");
        String body = jsonObject.getString("body");
        AdminService adminService = new AdminService();
        response.getWriter().print(adminService.updateReply(replyId,commentId,userId,canteenAdminId,body));
    }
}
