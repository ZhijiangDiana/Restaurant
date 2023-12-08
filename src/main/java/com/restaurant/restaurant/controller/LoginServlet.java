package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.pojo.LevelInfo;
import com.restaurant.restaurant.service.LoginService;
import com.restaurant.restaurant.utils.Constants;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String captcha = request.getParameter("captcha");
        System.out.println(id + password + captcha);

        // 验证验证码
        String sessionVerification = (String) request.getSession().getAttribute("verification");
        System.out.println(sessionVerification);
        LoginService loginService = new LoginService();
        int isVerified = loginService.loginCheck(id,password,captcha,sessionVerification);
        if (isVerified == Constants.LOGIN_INVALID){
            System.out.println("fail");
            // 写入信息 密码或账号错误
            response.sendRedirect(request.getContextPath() + "/login.html");
        }
        else {
            LevelInfo levelInfo = loginService.getExp(id,password,isVerified);
            // 存入session中到各个页面显示
            request.getSession().setAttribute("levelInfo",levelInfo);
            // 下面存的名字应该是凭借id去查数据库找到的
            request.getSession().setAttribute("username","  应该查数据库获得");
            if(isVerified == Constants.USER_VERIFIED){
                System.out.println("true");
                request.getRequestDispatcher("/a.html").forward(request,response);
            }
            else if(isVerified == Constants.RESTAURANT_ADMIN_VERIFIED){
                request.getRequestDispatcher("/restAdminInfo.jsp").forward(request,response);
            }
            else if(isVerified == Constants.ADMIN_VERIFIED){
                request.getRequestDispatcher("/adminInfo.jsp").forward(request,response);
            }
        }



    }
}
