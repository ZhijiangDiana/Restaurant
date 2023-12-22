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
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/updateCanteen")
public class UpdateCanteen extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonObject = FrontEndUtils.bodyToJson(request);
        String id = jsonObject.getString("canteenId");
        String name = jsonObject.getString("name");
        String location = jsonObject.getString("location");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Time startTime;
        Time endTime;
        try {
            startTime = new Time(timeFormat.parse(jsonObject.getString("startTime")).getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            endTime = new Time(timeFormat.parse(jsonObject.getString("endTime")).getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String description = jsonObject.getString("description");
        byte[] images = jsonObject.getBytes("image");
        String reportCount = jsonObject.getString("reportCount");
        AdminService adminService = new AdminService();
        response.getWriter().print(adminService.updateCanteen(id,name,location,startTime,endTime,description,images,reportCount));
    }
}
