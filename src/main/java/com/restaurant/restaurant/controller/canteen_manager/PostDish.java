package com.restaurant.restaurant.controller.canteen_manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.R;
import com.restaurant.restaurant.pojo.entity.Dish;
import com.restaurant.restaurant.service.canteen_manager.UploadDish;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;

/*
    增加菜品
 */
@WebServlet(name = "PostDish", value = "/PostDish")
public class PostDish extends HttpServlet {
    @Override
    public void init(){
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();
        JSONObject reqJson = FrontEndUtils.bodyToJson(req);

        Integer dishid = reqJson.getInteger("dishid");
        Integer canteenid = reqJson.getInteger("canteenid");
        String dishName = reqJson.getString("dishName");
        Integer series  = reqJson.getInteger("series");
        Integer price = reqJson.getInteger("price");
        Integer priceSale = reqJson.getInteger("priceSale");
        Double totalScore = reqJson.getDouble("totalScore");
        byte[] image = reqJson.getBytes("image");

        if(dishid.intValue()<0||canteenid.intValue()<=0||dishName==null || series.intValue()>10 ||series.intValue()<0 ||price.intValue()<0||priceSale.intValue()<0){
            resp.setStatus(500);
            R respText = new R("数据错误","1","");
            String json = JSON.toJSONString(respText);
            pw.print(json);
            return;
        }
        Dish dish = new Dish(dishid,canteenid,dishName,series,price,priceSale,totalScore,image);
        UploadDish uploadDish = new UploadDish(dish);
        String json = FrontEndUtils.objectToBody("", "0", null);
        pw.print(json);
    }

    @Override
    public void destroy() {
    }
}
