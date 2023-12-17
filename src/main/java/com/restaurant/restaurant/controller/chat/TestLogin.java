package com.restaurant.restaurant.controller.chat;

import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet("/Login")
public class TestLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        // 这里懒得改了
        String username = req.getParameter("username");
        req.getSession().setAttribute("username",username);

        String userId = req.getParameter("userId");
        System.out.println(userId);
        req.getSession().setAttribute("userId",userId);


        List<User> userList = (List<User>) req.getServletContext().getAttribute("userList");
        if (userList == null){
            userList = new ArrayList<>();
            userList.add(new User(Integer.parseInt(userId),username,"111",0,false,0));
            req.getServletContext().setAttribute("userList",userList);
        }
        else
            userList.add(new User(Integer.parseInt(userId),username,"111",0,false,0));

        resp.getWriter().print(FrontEndUtils.objectToBody(null,"0",null));
    }
}
