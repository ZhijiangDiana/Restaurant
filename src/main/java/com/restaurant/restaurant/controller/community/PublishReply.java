package com.restaurant.restaurant.controller.community;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.service.ReplyService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import com.restaurant.restaurant.utils.LegalSpeakFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/publishReply")
public class PublishReply extends HttpServlet {
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
        String commentId = jsonObject.getString("commentId");
        String body = jsonObject.getString("body");
        String id = jsonObject.getString("Id");
        System.out.println(commentId + body + id);
        if (body == null || body == ""){
            response.getWriter().print(FrontEndUtils.objectToBody("内容不能为空","1",null));
            return ;
        }

        if (LegalSpeakFilter.filterSensitiveWords(body) == true){
            response.getWriter().print(FrontEndUtils.objectToBody("涉及敏感发言","1",null));
            return ;
        }

        if (LegalSpeakFilter.banFromSpeaking(Integer.parseInt(id))){
            response.getWriter().print(FrontEndUtils.objectToBody("由于过往潜在不文明行为被禁止评论","1",null));
        }

        ReplyService replyService = new ReplyService();
        replyService.addReply(id,commentId,body);
        response.getWriter().print(FrontEndUtils.objectToBody("添加成功","0",null));
    }
}
