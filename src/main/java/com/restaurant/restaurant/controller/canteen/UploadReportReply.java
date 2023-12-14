package com.restaurant.restaurant.controller.canteen;

import java.io.*;

import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.entity.CanteenAdmin;
import com.restaurant.restaurant.pojo.entity.ReportReply;
import com.restaurant.restaurant.service.canteen.ReportService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "UploadReportReply", value = "/UploadReportReply")
public class UploadReportReply extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        JSONObject reqJson = FrontEndUtils.bodyToJson(req);
        Integer reportId = reqJson.getInteger("reportId");
        String body = reqJson.getString("body");

        HttpSession session = req.getSession(true);
        CanteenAdmin canteenAdmin = (CanteenAdmin) session.getAttribute("canteenAdmin");

//        // 测试用
//        canteenAdmin = new CanteenAdmin();
//        canteenAdmin.setCanteenAdminId(111111);

        // 参数判空
        if (canteenAdmin == null || reportId == null || body == null) {
            String respJson = FrontEndUtils.objectToBody("给定数据不全或未登录", "1", "");
            pw.print(respJson);
            resp.setStatus(500);
            return;
        }

        // TODO: 2023/12/14 没写敏感词检测，等zjy合并完之后再写

        ReportReply reportReply = new ReportReply();
        reportReply.setCanteenAdminId(canteenAdmin.getCanteenAdminId());
        reportReply.setReportId(reportId);
        reportReply.setBody(body);

        ReportService replyReportService = new ReportService();
        replyReportService.replyReport(reportReply);

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