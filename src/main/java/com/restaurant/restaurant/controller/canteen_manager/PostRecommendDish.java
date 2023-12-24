package com.restaurant.restaurant.controller.canteen_manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.service.canteen_manager.PostRecommendDishService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PostRecommendDish", value = "/PostRecommendDish")
public class PostRecommendDish extends HttpServlet {
    PostRecommendDishService postRecommendDishService = new PostRecommendDishService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();
        JSONObject reqJson = FrontEndUtils.bodyToJson(req);
        Integer canteenId = reqJson.getInteger("canteenId");
        String title = reqJson.getString("title");
        JSONArray body = reqJson.getJSONArray("body");
        String jsonString = body.toJSONString();
        System.out.println(canteenId);
        System.out.println(title);
        System.out.println(jsonString);

        String respJson = postRecommendDishService.publishRecommendations(canteenId, title,jsonString);

        pw.print(respJson);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
