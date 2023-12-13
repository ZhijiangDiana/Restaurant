package com.restaurant.restaurant.controller.current_user_info;

import com.alibaba.fastjson.JSON;
import com.restaurant.restaurant.pojo.LevelInfo;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.utils.ExpLevelCaculate;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/getCurrentUserName")
public class GetCurrentUserName extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        FrontEndUtils.objectToBody(null,"0",user.getUsername());
    }
}
