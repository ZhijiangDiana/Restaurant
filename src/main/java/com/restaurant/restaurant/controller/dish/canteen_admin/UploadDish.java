package com.restaurant.restaurant.controller.dish.canteen_admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.R;
import com.restaurant.restaurant.pojo.entity.CanteenAdmin;
import com.restaurant.restaurant.pojo.entity.Dish;
import com.restaurant.restaurant.service.canteen_manager.UploadDishService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;
import java.io.PrintWriter;

/*
    增加菜品
 */
@WebServlet(name = "UploadDish", value = "/UploadDish")
public class UploadDish extends HttpServlet {
    @Override
    public void init(){
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();
        JSONObject reqJson = FrontEndUtils.bodyToJson(req);
        HttpSession session = req.getSession(true);

        String dishName = reqJson.getString("dishName");
        Integer series  = reqJson.getInteger("series");
        Integer price = reqJson.getInteger("price");
        byte[] image = reqJson.getBytes("image");
        CanteenAdmin canteenAdmin = (CanteenAdmin) session.getAttribute("canteenAdmin");

//        // 测试
//        canteenAdmin = new CanteenAdmin();
//        canteenAdmin.setCanteenId(2);

        if (canteenAdmin == null || dishName == null || series == null || price == null || image == null) {
            resp.setStatus(500);
            R respText = new R("未登录或给定参数不全","1","");
            String json = JSON.toJSONString(respText);
            pw.print(json);
            return;
        }

        Dish dish = new Dish();
        dish.setCanteenId(canteenAdmin.getCanteenId());
        dish.setDishName(dishName);
        dish.setSeries(series);
        dish.setPrice(price);
        dish.setImage(image);

        UploadDishService uploadDishService = new UploadDishService();
        boolean isSuccess = uploadDishService.uploadDish(dish);
        if (isSuccess) {
            String json = FrontEndUtils.objectToBody("提交成功", "0", null);
            pw.print(json);
        } else {
            pw.print(FrontEndUtils.objectToBody("提交失败", "1", null));
        }


    }

    @Override
    public void destroy() {
    }
}
