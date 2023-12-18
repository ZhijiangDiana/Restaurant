package com.restaurant.restaurant.controller.dish;

import java.io.*;

import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.entity.Dish;
import com.restaurant.restaurant.service.dish.GetDishInfoService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "GetDishInfo", value = "/GetDishInfo")
public class GetDishInfo extends HttpServlet {
    @Override
    public void init() {

    }

    /**
     * 传入dishId，返回dish的完整记录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        JSONObject reqJson = FrontEndUtils.bodyToJson(req);
        Integer dishId = reqJson.getInteger("dishId");
        if (dishId == null) {
            resp.setStatus(500);
            pw.print(FrontEndUtils.objectToBody("缺少dishId参数", "1", null));
            return;
        }

        GetDishInfoService dishInfoService = new GetDishInfoService();
        Dish res = dishInfoService.getDishInfo(dishId);
        if (res != null) {
            pw.print(FrontEndUtils.objectToBody("", "0", res));
        } else {
            pw.print(FrontEndUtils.objectToBody("查询失败", "1", null));
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