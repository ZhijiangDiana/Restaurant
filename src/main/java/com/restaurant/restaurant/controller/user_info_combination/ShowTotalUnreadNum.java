package com.restaurant.restaurant.controller.user_info_combination;

import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/showTotalUnreadNum")
public class ShowTotalUnreadNum extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: 2023/12/27 注册用户时将这三个attribute加入context中
        HashMap<Integer,Integer> replyCounts = (HashMap<Integer,Integer>)request.getServletContext().getAttribute("replyCounts");
        HashMap<Integer,Integer> thumbsupCounts = (HashMap<Integer, Integer>) request.getServletContext().getAttribute("thumbsupCounts");
        HashMap<Integer,Integer> reportReplyCounts = (HashMap<Integer, Integer>) request.getServletContext().getAttribute("reportReplyCounts");
        User user = (User)request.getSession().getAttribute("user");
        if (user == null){
            response.getWriter().print(FrontEndUtils.objectToBody("用户未登录","1",null));
            return;
        }
        Integer userId = user.getUserId();
        Integer replyNum = replyCounts.get(userId);
        Integer thumbsupNum = thumbsupCounts.get(userId);
        Integer reportReplyNum = reportReplyCounts.get(userId);
        response.getWriter().print(FrontEndUtils.objectToBody(null,"0",(replyNum + thumbsupNum + reportReplyNum)));
    }
}
