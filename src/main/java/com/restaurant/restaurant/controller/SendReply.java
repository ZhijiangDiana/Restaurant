package com.restaurant.restaurant.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.R;
import com.restaurant.restaurant.pojo.Reply;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/sendReply")
public class SendReply extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        String replyId = jsonObject.getString("replyId");
        String content = jsonObject.getString("content");
        String receiver = jsonObject.getString("receiver");
        String sender = jsonObject.getString("sender");
        System.out.println(sender + receiver + content);
        // 进行评论过滤

        if(0 != 0){
            // 有问题 测通了 可以弹出消息框
            R r = new R("敏感发言倾向","1",null);
            String s = JSON.toJSONString(r);
            response.getWriter().print(s);
        }
        else {
            // 没问题
            //R r = new R("回复成功","0","回复成功");
            // 把这条数据添加到回复数据库中
            // 下面仅为模拟
            Reply reply1 = new Reply("张三","李四","asdfadf");
            Reply reply2 = new Reply("啊违法","二哥","as 哈儿dfadf");
            Reply reply3 = new Reply("噶人","知道地方","a为adf");
            Reply reply4 = new Reply("张三","李四","asdfadf");
            Reply reply5 = new Reply("啊违法","二哥","as 哈儿dfadf");
            Reply reply6 = new Reply("噶人","知道地方","a为adf");
            Reply reply7 = new Reply(sender,receiver,content);
            List<Reply> replyList = new ArrayList<>();
            replyList.add(reply1);
            replyList.add(reply2);
            replyList.add(reply3);
            replyList.add(reply4);
            replyList.add(reply5);
            replyList.add(reply6);
            replyList.add(reply7);
            String s = JSON.toJSONString(replyList);
            R r = new R("回复成功","0",s);
            String s1 = JSON.toJSONString(r);
            response.getWriter().print(s1);
        }
    }
}
