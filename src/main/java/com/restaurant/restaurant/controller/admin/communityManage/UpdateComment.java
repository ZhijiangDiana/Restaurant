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
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/updateComment")
public class UpdateComment extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonObject = FrontEndUtils.bodyToJson(request);
        String commentId = jsonObject.getString("commentId");
        String userId = jsonObject.getString("userId");
        String title = jsonObject.getString("title");
        String body = jsonObject.getString("body");
        byte[] image = jsonObject.getBytes("image");
        Date publishTime = jsonObject.getDate("publishTime");
        String likes = jsonObject.getString("likes");
        AdminService adminService = new AdminService();
        response.getWriter().print(adminService.updateComment(commentId,userId,title,body,image,publishTime,likes));
    }
}
