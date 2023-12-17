package com.restaurant.restaurant.controller.admin.dishCommentManage;

import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.service.AdminService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;

@WebServlet("/updateDishComment")
public class UpdateDishComment extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonObject = FrontEndUtils.bodyToJson(request);
        String dishCommentId = jsonObject.getString("dishCommentId");
        String userId = jsonObject.getString("userId");
        String dishId = jsonObject.getString("dishId");
        String score = jsonObject.getString("score");
        String tittle = jsonObject.getString("tittle");
        String body = jsonObject.getString("body");
        byte[] image = jsonObject.getBytes("image");
        AdminService adminService = new AdminService();
        response.getWriter().print(adminService.updateDishComment(dishCommentId,userId,dishId,score,tittle,body,image));
    }
}