package com.restaurant.restaurant.controller;

import java.io.*;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.entity.Dish;
import com.restaurant.restaurant.service.SearchDishService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "SearchDish", value = "/SearchDish")
public class SearchDish extends HttpServlet {
    // TODO: 2023/12/10 未测试，等mapper出来之后测试 
    private static final int SERIES = 0;
    private static final int PRICE = 1;
    private static final int CANTEEN = 2;
    private static final int SCORE = 3;

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        JSONObject reqJson = FrontEndUtils.bodyToJson(req);

        Integer option = reqJson.getInteger("option");
        SearchDishService searchDish = new SearchDishService();
        if (option.equals(SERIES)) {
            Integer series = reqJson.getInteger("series");
            if (series == null) {
                resp.setStatus(500);
                pw.print(FrontEndUtils.objectToBody("option缺少对应的参数", "1", null));
                return;
            }
            List<Dish> res = searchDish.searchDishBySeries(series);
            pw.print(FrontEndUtils.objectToBody("", "0", res));
        } else if (option.equals(PRICE)) {
            Integer minPrice = reqJson.getInteger("minPrice");
            Integer maxPrice = reqJson.getInteger("maxPrice");
            if (minPrice == null || maxPrice == null) {
                resp.setStatus(500);
                pw.print(FrontEndUtils.objectToBody("option缺少对应的参数", "1", null));
                return;
            }
            List<Dish> res = searchDish.searchDishByPrice(minPrice, maxPrice);
            pw.print(FrontEndUtils.objectToBody("", "0", res));
        } else if (option.equals(CANTEEN)) {
            String canteen = reqJson.getString("canteen");
            if (canteen == null) {
                resp.setStatus(500);
                pw.print(FrontEndUtils.objectToBody("option缺少对应的参数", "1", null));
                return;
            }
            List<Dish> res = searchDish.searchDishByCanteen(canteen);
            pw.print(FrontEndUtils.objectToBody("", "0", res));
        } else if (option.equals(SCORE)) {
            Double score = reqJson.getDouble("score");
            if (score == null) {
                resp.setStatus(500);
                pw.print(FrontEndUtils.objectToBody("option缺少对应的参数", "1", null));
                return;
            }
            List<Dish> res = searchDish.searchDishByScore(score);
            pw.print(FrontEndUtils.objectToBody("", "0", res));
        } else {
            resp.setStatus(500);
            pw.print(FrontEndUtils.objectToBody("option参数无效或缺失", "1", null));
            return;
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doPost(req, resp);
    }

    @Override
    public void destroy() {

    }
}