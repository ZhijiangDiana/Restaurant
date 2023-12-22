package com.restaurant.restaurant.controller.current_user_info;

import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.service.CommentService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/getMessage")
public class GetMessage extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        User user = (User)req.getSession().getAttribute("user");
        //c测试
        User user = new User();
        user.setUserId(1);
        Integer userId = user.getUserId();
        CommentService commentService = new CommentService();
        String res = commentService.selectUserId(userId);
        resp.getWriter().print(res);

    }


}
