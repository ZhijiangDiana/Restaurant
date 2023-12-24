package com.restaurant.restaurant.controller.canteen_manager;

import com.alibaba.fastjson.JSON;
import com.restaurant.restaurant.pojo.entity.Dish;
import com.restaurant.restaurant.service.canteen_manager.GetRecommendDishService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GetRecommendDishInfo" , value = "/GetRecommendDishInfo")
public class GetRecommendDishInfo extends HttpServlet {
    GetRecommendDishService dishService = new GetRecommendDishService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        List<Dish> dishes = dishService.GetDish();

        pw.print(FrontEndUtils.objectToBody("", "0", dishes));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
