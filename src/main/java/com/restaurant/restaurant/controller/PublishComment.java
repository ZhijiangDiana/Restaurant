package com.restaurant.restaurant.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.entity.Comment;
import com.restaurant.restaurant.service.CommentService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

@WebServlet("/publishComment")
public class PublishComment extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        // 使用 Fastjson 解析 JSON 字符串
        JSONObject jsonObject = JSON.parseObject(sb.toString());
        // 从 JSON 对象中提取数据
        String title = jsonObject.getString("title");
        String body = jsonObject.getString("body");
        String img = jsonObject.getString("image");
        if (title == null || body == null || img == null){
            response.getWriter().print(FrontEndUtils.objectToBody("有敏感词汇","0",null));
            return;
        }
        img = img.substring(img.indexOf(",") + 1);
        System.out.println(img);
        byte[] imageBytes = Base64.getDecoder().decode(img);
        Comment c = new Comment(1,title,body,imageBytes,new Date(),666);
        CommentService commentService = new CommentService();
        commentService.insertComment(c);
        response.getWriter().print(FrontEndUtils.objectToBody("有敏感词汇","1",null));

    }
}
