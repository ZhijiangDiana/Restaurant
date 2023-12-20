package com.restaurant.restaurant.controller.login;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.LevelInfo;
import com.restaurant.restaurant.pojo.entity.CanteenAdmin;
import com.restaurant.restaurant.pojo.entity.DishComment;
import com.restaurant.restaurant.pojo.entity.Report;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.service.LoginService;
import com.restaurant.restaurant.utils.Constants;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Date;
import java.util.HashMap;
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
            // 设置信息数据结构  <k,v>--<学号,数量>,名字对应某某消息,这里不负责具体消息展示。只显示小红点数量。
            // 点击小红点跳转到类似b站的信息页面对应页面会发请求获取相对应的资源
            // 这里要放入servletcontext是为了有人发消息或者点赞后能够实时更新 看过后数量自然减少
            HashMap<Integer,Integer> replyCounts = new HashMap<>();
            HashMap<Integer,Integer> thumbsupCounts = new HashMap<>();
            HashMap<Integer,Integer> reportReplyCounts = new HashMap<>();

            HashMap<Integer,Integer> replyCounts1 = (HashMap<Integer,Integer>)request.getServletContext().getAttribute("replyCounts");
            HashMap<Integer,Integer> thumbsupCounts1 = (HashMap<Integer, Integer>) request.getServletContext().getAttribute("thumbsupCounts");
            HashMap<Integer,Integer> reportReplyCounts1 = (HashMap<Integer, Integer>) request.getServletContext().getAttribute("reportReplyCounts");
            if (replyCounts1 == null)
                request.getServletContext().setAttribute("replyCounts",replyCounts);
            if (thumbsupCounts1 == null)
                request.getServletContext().setAttribute("thumbsupCounts",thumbsupCounts);
            if (reportReplyCounts1 == null)
                request.getServletContext().setAttribute("reportReplyCounts",reportReplyCounts);
            session.setAttribute("user",user);
            session.setAttribute("userCommentsPerOnline",0);
            Queue<Date> commentQueue = new ConcurrentLinkedQueue<>();
            session.setAttribute("commentQueue", commentQueue);
        }

        if(canteenAdmin != null){
            request.getSession().setAttribute("canteenAdmin",canteenAdmin);
            request.getSession().setAttribute("userCommentsPerOnline",0);

            // 添加notification的集合是否在context中
            int canteenId = canteenAdmin.getCanteenId();
            ServletContext context = getServletContext();
            Map<Integer, Map<Integer, Report>> reportNotif =
                    (Map<Integer, Map<Integer, Report>>) context.getAttribute("reportNotif");
            Map<Integer, Map<Integer, DishComment>> dishCommentNotif =
                    (Map<Integer, Map<Integer, DishComment>>) context.getAttribute("dishCommentNotif");
            if (reportNotif.get(canteenId) == null ||
                    dishCommentNotif.get(canteenId) == null) {
                Map<Integer, Report> reportSet = new ConcurrentHashMap<>();
                Map<Integer, DishComment> dishCommentSet = new ConcurrentHashMap<>();
                reportNotif.put(canteenId, reportSet);
                dishCommentNotif.put(canteenId, dishCommentSet);
            }
        }

        response.getWriter().print(info);
    }
}
