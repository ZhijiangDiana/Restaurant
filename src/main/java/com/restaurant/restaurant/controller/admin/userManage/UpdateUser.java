package com.restaurant.restaurant.controller.admin.userManage;

import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.service.AdminService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/updateUser")
public class UpdateUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonObject = FrontEndUtils.bodyToJson(request);
        String id = jsonObject.getString("id");
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String experience = jsonObject.getString("experience");
        String isForbidden = jsonObject.getString("isForbidden");
        String illegality = jsonObject.getString("illegality");

        AdminService adminService = new AdminService();
        response.getWriter().print(adminService.updateUser(id,username,password,experience,isForbidden,illegality));
    }
}
