package com.restaurant.restaurant.controller.register;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.service.RegisterService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/register")
public class Register extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        // 使用 Fastjson 解析 JSON 字符串
        JSONObject jsonObject = JSON.parseObject(sb.toString());
        // 从 JSON 对象中提取数据
        String id = jsonObject.getString("id");
        String password = jsonObject.getString("password");
        String repassword = jsonObject.getString("repassword");

        RegisterService registerService = new RegisterService();
        response.getWriter().print(registerService.registerUser(id, password, repassword));
    }
}
