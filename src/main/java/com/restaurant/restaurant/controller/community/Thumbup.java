package com.restaurant.restaurant.controller.community;

import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.service.CommentService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/thumb")
public class Thumbup extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flag = request.getParameter("flag");
        String commentId = request.getParameter("commentId");
        // 根据commentId拿到发帖子人的userId
        CommentService commentService = new CommentService();
        Integer commentPublisher = commentService.getCommentPublisher(commentId);
        if (commentPublisher == 0){
            response.getWriter().print(FrontEndUtils.objectToBody("系统繁忙","1",null));
        }
        else {
            HashMap<Integer,Integer> thumbsupCounts = (HashMap<Integer, Integer>) request.getServletContext().getAttribute("thumbsupCounts");
            HashMap<Integer, Map<Integer,Integer>> thumbsupDetails = (HashMap<Integer, Map<Integer, Integer>>) request.getServletContext().getAttribute("thumbsupDetails");
            User user = (User) request.getSession().getAttribute("user");
            Integer userId = user.getUserId();
            if ("1".equals(flag)) {
                thumbsupCounts.put(commentPublisher, thumbsupCounts.get(commentPublisher) + 1);
                Map<Integer, Integer> info = thumbsupDetails.get(commentPublisher);
                info.put(user.getUserId(),Integer.parseInt(commentId));
                thumbsupDetails.put(commentPublisher,info);
            }
            if ("0".equals(flag)) {
                thumbsupCounts.put(commentPublisher, thumbsupCounts.get(commentPublisher) - 1);
                Map<Integer, Integer> info = thumbsupDetails.get(commentPublisher);
                info.remove(user.getUserId());
                thumbsupDetails.put(commentPublisher,info);
            }
            response.getWriter().print(commentService.thumbup(flag, commentId));
        }
    }
}
