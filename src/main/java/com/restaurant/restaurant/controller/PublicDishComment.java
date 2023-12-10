package com.restaurant.restaurant.controller;

import java.io.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.R;
import com.restaurant.restaurant.pojo.entity.DishComment;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.service.UploadDishComment;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "PublicDishComment", value = "/PublicDishComment")
public class PublicDishComment extends HttpServlet {
    // TODO: 2023/12/10 未测试，等mapper出来之后测试

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        HttpSession session = req.getSession(true);

        JSONObject reqJson = FrontEndUtils.bodyToJson(req);

        Integer dishId = reqJson.getInteger("dishId");
        Double score = reqJson.getDouble("score");
        String title = reqJson.getString("title");
        String body = reqJson.getString("body");

        User user = (User) session.getAttribute("user");
        if (user == null || dishId == null || score == null ||
                title == null || body == null) {
            resp.setStatus(500);
            R respText = new R("给定数据不全或未登录", "1", "");
            String json = JSON.toJSONString(respText);
            pw.print(json);
            return;
        }

        DishComment dishComment = new DishComment();
        dishComment.setUserId(user.getUserId());
        dishComment.setDishId(dishId);
        dishComment.setScore(score);
        dishComment.setTittle(title);
        dishComment.setBody(body);

        UploadDishComment uploadDishComment = new UploadDishComment();
        uploadDishComment.commentDish(dishComment);

        String json = FrontEndUtils.objectToBody("", "0", dishComment);

        pw.print(json);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doPost(req, resp);
    }

    @Override
    public void destroy() {

    }
}