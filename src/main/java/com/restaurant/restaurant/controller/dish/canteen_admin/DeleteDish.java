package com.restaurant.restaurant.controller.dish.canteen_admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.R;
import com.restaurant.restaurant.service.canteen_manager.Delete_Dish;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="DeleteDish",value = "/DeleteDish")
public class DeleteDish extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        JSONObject reqJson = FrontEndUtils.bodyToJson(req);

        Integer dishId = reqJson.getInteger("dishId");
        if(dishId == null){
            resp.setStatus(500);
            R respText = new R("给定参数不全","1","");
            String json = JSON.toJSONString(respText);
            pw.print(json);
            return;
        }

        Delete_Dish deleteDish = new Delete_Dish();
        deleteDish.deleteDish(dishId);

        String json = FrontEndUtils.objectToBody("删除成功", "0", null);
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
