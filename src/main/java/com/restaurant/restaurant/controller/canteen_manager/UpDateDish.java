package com.restaurant.restaurant.controller.canteen_manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.R;
import com.restaurant.restaurant.pojo.entity.Dish;
import com.restaurant.restaurant.service.canteen_manager.Update_Dish;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UpDateDish",value = "/UpDateDish")
public class UpDateDish extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;chatset=UTF-8");
        PrintWriter pw = resp.getWriter();
        JSONObject jsonObject = FrontEndUtils.bodyToJson(req);

        Integer dishId = jsonObject.getInteger("dishid");
        Integer canteenId = jsonObject.getInteger("canteenid");
        String dishName = jsonObject.getString("dishname");
        Integer series  = jsonObject.getInteger("series");
        Integer price = jsonObject.getInteger("price");
        Integer priceSale = jsonObject.getInteger("priceSale");
        Double totalScore = jsonObject.getDouble("totalScore");
        byte[] image = jsonObject.getBytes("image");

        if(dishId.intValue()<0||canteenId.intValue()<=0||dishName==null || series.intValue()>10 ||series.intValue()<0 ||price.intValue()<0||priceSale.intValue()<0){
            resp.setStatus(500);
            R respText = new R("数据错误","1","");
            String json = JSON.toJSONString(respText);
            pw.print(json);
            return;
        }

        Dish dish = new Dish(dishId,canteenId,dishName,series,price,priceSale,totalScore,image);
        Update_Dish upDateDish = new Update_Dish(dish);
        String json = FrontEndUtils.objectToBody("", "0", null);
        pw.print(json);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
