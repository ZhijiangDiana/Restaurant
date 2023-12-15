package com.restaurant.restaurant.controller.login;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.LevelInfo;
import com.restaurant.restaurant.pojo.entity.CanteenAdmin;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.service.LoginService;
import com.restaurant.restaurant.utils.Constants;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;


@WebServlet("/login")
public class LoginServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
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
        String id = jsonObject.getString("id");
        String password = jsonObject.getString("password");
        String captcha = jsonObject.getString("captcha");

        // 验证验证码
        String sessionVerification = (String) request.getSession().getAttribute("verification");
        System.out.println("账号:" + id +"密码：" +password+ sessionVerification);
        // 验证登录信息
        LoginService loginService = new LoginService();
        String info = loginService.loginCheck(id, password, captcha, sessionVerification);
        User user = loginService.getUserById(id, password, captcha, sessionVerification);
        CanteenAdmin canteenAdmin = loginService.getCanteenAdminById(id, password, captcha, sessionVerification);
        // 向session中存一些重要信息
        HttpSession session = request.getSession();
        if(user != null){
            System.out.println("你告诉我我没执行这个吗");
            session.setAttribute("user",user);
            session.setAttribute("userCommentsPerOnline",0);
            Queue<Date> commentQueue = new LinkedList<>();
            session.setAttribute("commentQueue", commentQueue);
        }

        if(canteenAdmin != null){
            request.getSession().setAttribute("canteenAdmin",canteenAdmin);
            request.getSession().setAttribute("userCommentsPerOnline",0);
        }

        response.getWriter().print(info);
    }
}
