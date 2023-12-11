package com.restaurant.restaurant.controller.community;

import com.restaurant.restaurant.pojo.ReplyShow;
import com.restaurant.restaurant.service.ReplyService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/showReply")
public class ShowReply extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 直接在url后面挂参数
        String commentId = request.getParameter("commentId");
        ReplyService replyService = new ReplyService();
        List<ReplyShow> allReplyById = replyService.getAllReplyById(commentId);
        PrintWriter writer = response.getWriter();
        if (allReplyById == null){
            writer.print(FrontEndUtils.objectToBody("暂无评论","1",null));
        }
        else {
            writer.print(FrontEndUtils.objectToBody("","0",allReplyById));
        }
    }
}
