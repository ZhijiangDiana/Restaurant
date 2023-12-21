package com.restaurant.restaurant.controller.admin.canteenAdminManage;

import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.service.CanteenAdminService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/updateCanteenAdmin")
public class UpdateCanteenAdmin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonObject = FrontEndUtils.bodyToJson(request);
        PrintWriter pw = response.getWriter();
        String canteenAdminId = jsonObject.getString("canteenAdminId");
        String canteenId = jsonObject.getString("canteenId");

        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        CanteenAdminService canteenAdminService = new CanteenAdminService();
        String res = canteenAdminService.updateCanteenAdmin(canteenAdminId, canteenId, username, password);
        pw.print(res);

    }
}
