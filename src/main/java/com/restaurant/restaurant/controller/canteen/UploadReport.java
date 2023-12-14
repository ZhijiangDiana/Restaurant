package com.restaurant.restaurant.controller.canteen;

import java.io.*;

import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.entity.Report;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.service.canteen.ReportService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import com.restaurant.restaurant.utils.LegalSpeakFilter;
import com.restaurant.restaurant.utils.Pair;
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

        Pair<Boolean, Boolean> banOrSensitive = LegalSpeakFilter.isBanOrSensitive(title + body, user.getUserId());
        if (banOrSensitive.first) {
            resp.setStatus(403);
            pw.print(FrontEndUtils.objectToBody("被禁言了还狗叫啥","1",null));
            return;
        } else if (banOrSensitive.second) {
            resp.setStatus(403);
            pw.print(FrontEndUtils.objectToBody("你在狗叫什么", "1", null));
            return;
        }

        Report report = new Report();
        report.setUserId(user.getUserId());
        report.setCanteenId(canteenId);
        report.setTitle(title);
        report.setBody(body);



        ReportService reportService = new ReportService();
        reportService.reportCanteen(report);

        String respJson = FrontEndUtils.objectToBody("", "0", "");
        pw.print(respJson);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doPost(req, resp);
    }

    @Override
    public void destroy() {

    }
}