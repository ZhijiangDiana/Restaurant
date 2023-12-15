package com.restaurant.restaurant.controller;

import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.entity.CanteenAdmin;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.service.FrequentCommentFilter;
import com.restaurant.restaurant.utils.FrontEndUtils;
import com.restaurant.restaurant.utils.LegalSpeakFilter;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

@WebFilter(urlPatterns = {"/PublicDishComment", "/UploadReport"})
public class CommentFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("经过过滤器");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        JSONObject reqJson = FrontEndUtils.bodyToJson(req);
        String title = reqJson.getString("title");
        String body = reqJson.getString("body");

        HttpSession session = req.getSession(true);
        User user = (User) session.getAttribute("user");
        CanteenAdmin canteenAdmin = (CanteenAdmin) session.getAttribute("canteenAdmin");
        Queue<Date> commentQueue = (Queue<Date>) session.getAttribute("commentQueue");

        // 测试
        user = new User();
        user.setUserId(1);
        commentQueue = new LinkedList<>();

        // 登录检测
        String message;
        if (user == null && canteenAdmin == null) {
            message = "未登录，请登录";
            resp.setStatus(403);
            resp.setContentType("text/html; charset = UTF-8");
            PrintWriter pw = resp.getWriter();
            pw.print(FrontEndUtils.objectToBody(message,"1",null));
            return;
        }

        // 对管理员不执行检测
        if (canteenAdmin != null) {
            filterChain.doFilter(req, resp);
            System.out.println("放行");
            return;
        }

        // 狗叫检测
        message = LegalSpeakFilter.returnMessage(title + body, user.getUserId());
        if (message != null) {
            resp.setStatus(403);
            resp.setContentType("text/html; charset = UTF-8");
            PrintWriter pw = resp.getWriter();
            pw.print(FrontEndUtils.objectToBody(message,"1",null));
            return;
        }
        // 疯狗检测
        message = FrequentCommentFilter.returnMessage(commentQueue);
        if (message != null) {
            resp.setStatus(403);
            resp.setContentType("text/html; charset = UTF-8");
            PrintWriter pw = resp.getWriter();
            pw.print(FrontEndUtils.objectToBody(message,"1",null));
            return;
        }

        // 放行
        filterChain.doFilter(req, resp);
        System.out.println("放行");
        return;
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}