package com.restaurant.restaurant.controller;

import com.alibaba.fastjson.JSON;
import com.restaurant.restaurant.pojo.R;
import com.restaurant.restaurant.pojo.entity.Reply;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/reply")
public class ReplyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String commentId = request.getParameter("commentId");
//        System.out.println(commentId);
//        Reply reply1 = new Reply("张三","李四","asdfadf");
//        Reply reply2 = new Reply("啊违法","二哥","as 哈儿dfadf");
//        Reply reply3 = new Reply("噶人","知道地方","a为adf");
//        Reply reply4 = new Reply("张三","李四","asdfadf");
//        Reply reply5 = new Reply("啊违法","二哥","as 哈儿dfadf");
//        Reply reply6 = new Reply("噶人","知道地方","a为adf");
//        List<Reply> replyList = new ArrayList<>();
//        replyList.add(reply1);
//        replyList.add(reply2);
//        replyList.add(reply3);
//        replyList.add(reply4);
//        replyList.add(reply5);
//        replyList.add(reply6);
//        String s1 = JSON.toJSONString(replyList);
//        R r = new R("ok","0",s1);
//        String s = JSON.toJSONString(r);
//        response.getWriter().print(s);
    }
}
