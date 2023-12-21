package com.restaurant.restaurant.controller.admin.canteenAdminManage;

import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/updateCanteenAdmin")
public class UpdateCanteenAdmin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonObject = FrontEndUtils.bodyToJson(request);
        String canteenAdminId = jsonObject.getString("canteenAdminId");
        String canteenId = jsonObject.getString("canteenId");
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");

    }
}
