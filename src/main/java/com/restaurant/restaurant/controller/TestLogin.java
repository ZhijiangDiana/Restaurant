package com.restaurant.restaurant.controller;

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


        String username = req.getParameter("username");
        String password1 = req.getParameter("password");
        req.getSession().setAttribute("username",username);
        Random random = new Random();

        // 生成2000000000至2200000000范围内的随机数
        Integer id = 2000000000 + random.nextInt(200000001);


        List<User> userList = (List<User>) req.getServletContext().getAttribute("userList");
        if (userList == null){
            userList = new ArrayList<>();
            userList.add(new User(id,username,password1,0,false,0));
            req.getServletContext().setAttribute("userList",userList);
        }
        else
            userList.add(new User(id,username,password1,0,false,0));

        String password = req.getParameter("password");
        System.out.println(username + password);
        resp.getWriter().print(FrontEndUtils.objectToBody(null,"0",null));
    }
}
