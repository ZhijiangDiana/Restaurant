package com.restaurant.restaurant.controller.admin.canteenManage;

import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.mapper.CanteenMapper;
import com.restaurant.restaurant.pojo.entity.Canteen;
import com.restaurant.restaurant.service.AdminService;
import com.restaurant.restaurant.service.canteen_manager.SelectCanteenById;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/showCanteenById")
public class ShowCanteenById extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 前端肯定会带着指定人的id过来
        String canteenId = request.getParameter("canteenId");

        SelectCanteenById selectCanteenById = new SelectCanteenById();
        Canteen res = selectCanteenById.SelectCanteenById(Integer.parseInt(canteenId));
        response.getWriter().print(FrontEndUtils.objectToBody("", "0", res));
    }
}
