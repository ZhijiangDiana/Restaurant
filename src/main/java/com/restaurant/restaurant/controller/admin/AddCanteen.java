package com.restaurant.restaurant.controller.admin;

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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@WebServlet("/addCanteen")
public class AddCanteen extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonObject = FrontEndUtils.bodyToJson(request);
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
        AdminService adminService = new AdminService();
        System.out.println(startTime);
        response.getWriter().print(adminService.addCanteen(name,location,startTime,endTime,description,images));

    }
}
