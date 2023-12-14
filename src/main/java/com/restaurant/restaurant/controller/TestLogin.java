package com.restaurant.restaurant.controller;

import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/Login")
public class TestLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String username = req.getParameter("username");
        req.getSession().setAttribute("username",username);
        String password = req.getParameter("password");
        System.out.println(username + password);
        resp.getWriter().print(FrontEndUtils.objectToBody(null,"0",null));
    }
}
