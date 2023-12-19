package com.restaurant.restaurant.controller.report;

import java.io.*;

import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.entity.Report;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.service.report.ReportService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "UploadReport", value = "/UploadReport")
public class UploadReport extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        JSONObject reqJson = FrontEndUtils.bodyToJson(req);
        Integer canteenId = reqJson.getInteger("canteenId");
        String title = reqJson.getString("title");
        String body = reqJson.getString("body");

        ServletContext context = getServletContext();
        HttpSession session = req.getSession(true);
        User user = (User) session.getAttribute("user");

//        // 测试用
//        user = new User();
//        user.setUserId(2120550332);

        // 参数判空
        if (canteenId == null || title == null || body == null || user == null) {
            String respJson = FrontEndUtils.objectToBody("给定数据不全或未登录", "1", "");
            pw.print(respJson);
            resp.setStatus(500);
            return;
        }

        Report report = new Report();
        report.setUserId(user.getUserId());
        report.setCanteenId(canteenId);
        report.setTitle(title);
        report.setBody(body);



        ReportService reportService = new ReportService();
        boolean isSuccess = reportService.reportCanteen(report, context);
        if (isSuccess) {
            String respJson = FrontEndUtils.objectToBody("提交成功", "0", "");
            pw.print(respJson);
        } else {
            pw.print(FrontEndUtils.objectToBody("提交失败", "1" , null));
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doPost(req, resp);
    }

    @Override
    public void destroy() {

    }
}