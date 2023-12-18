package com.restaurant.restaurant.controller.admin.canteenManage;

import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.service.AdminService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/setCanteenAdmin")
public class SetCanteenAdmin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonObject = FrontEndUtils.bodyToJson(request);
        // 前端肯定会带着指定人的id过来
        String id = jsonObject.getString("id");
        String canteenName = jsonObject.getString("canteenName");
        AdminService adminService = new AdminService();
        response.getWriter().print(adminService.setUserToCanteenAdmin(id,canteenName));
    }
}
