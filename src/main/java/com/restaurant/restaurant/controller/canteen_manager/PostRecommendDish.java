package com.restaurant.restaurant.controller.canteen_manager;

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
        JSONArray dishIdsArray = reqJson.getJSONArray("dishIds");

        List<Integer> dishIds = new ArrayList<>();
        for (int i = 0; i < dishIdsArray.size(); i++) {
              dishIds.add(dishIdsArray.getInteger(i));
        }

        boolean success = postRecommendDishService.publishRecommendations(canteenId, dishIds);

        String respJson;
        if(!success) {
            respJson = FrontEndUtils.objectToBody("推荐菜品失败", "1", null);
        } else {
            respJson = FrontEndUtils.objectToBody("推荐菜品成功", "0", null);
        }
        pw.print(respJson);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
