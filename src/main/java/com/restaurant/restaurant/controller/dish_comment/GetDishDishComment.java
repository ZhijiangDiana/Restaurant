package com.restaurant.restaurant.controller.dish_comment;

import java.io.*;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.DishCommentShow;
import com.restaurant.restaurant.pojo.entity.DishComment;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.service.dish_comment.GetDishCommentService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "GetDishDishComment", value = "/GetDishDishComment")
public class GetDishDishComment extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        JSONObject reqJson = FrontEndUtils.bodyToJson(req);
        Integer dishId = reqJson.getInteger("dishId");
        if (dishId == null) {
            resp.setStatus(500);
            pw.print(FrontEndUtils.objectToBody("参数缺失", "1", null));
            return;
        }

        GetDishCommentService getDishCommentService = new GetDishCommentService();
        List<DishCommentShow> res =
                getDishCommentService.getDishCommentByDish(dishId);
        if (res == null || res.isEmpty())
            pw.print(FrontEndUtils.objectToBody("暂无评价", "1", null));
        else
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