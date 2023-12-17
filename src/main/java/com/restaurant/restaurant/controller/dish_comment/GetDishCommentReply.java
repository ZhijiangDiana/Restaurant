package com.restaurant.restaurant.controller.dish_comment;

import java.io.*;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.entity.DishComment;
import com.restaurant.restaurant.pojo.entity.DishCommentReply;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.service.dish_comment.GetDishCommentReplyService;
import com.restaurant.restaurant.service.dish_comment.GetDishCommentService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "GetDishCommentReply", value = "/GetDishCommentReply")
public class GetDishCommentReply extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        JSONObject reqJson = FrontEndUtils.bodyToJson(req);
        Integer dishCommentId = reqJson.getInteger("dishCommentId");
        if (dishCommentId == null) {
            resp.setStatus(500);
            pw.print(FrontEndUtils.objectToBody("参数不全", "1", null));
            return;
        }

        GetDishCommentReplyService getDishCommentReplyService =
                new GetDishCommentReplyService();
        List<DishCommentReply> res =
                getDishCommentReplyService.getDishCommentReplyById(dishCommentId);
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