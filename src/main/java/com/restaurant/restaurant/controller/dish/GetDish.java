package com.restaurant.restaurant.controller.dish;

import java.io.*;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.entity.Dish;
import com.restaurant.restaurant.service.dish.SearchDishService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "GetDish", value = "/GetDish")
public class GetDish extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        SearchDishService searchDish = new SearchDishService();
        List<Dish> res = searchDish.getAllDish();
        pw.print(FrontEndUtils.objectToBody("", "0", res));
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doPost(req, resp);
    }

    @Override
    public void destroy() {

    }
}