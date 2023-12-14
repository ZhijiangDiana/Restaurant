package com.restaurant.restaurant.controller.canteen;

import java.io.*;

import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.entity.Report;
import com.restaurant.restaurant.pojo.entity.ReportReply;
import com.restaurant.restaurant.service.canteen.ReportService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import com.restaurant.restaurant.utils.Pair;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "GetReportResult", value = "/GetReportResult")
public class GetReportResult extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        JSONObject reqJson = FrontEndUtils.bodyToJson(req);
        Integer reportId = reqJson.getInteger("reportId");

        ReportService reportService = new ReportService();
        Pair<Report, ReportReply> reportAndReply = reportService.getReportAndReply(reportId);

        // 未找到投诉id
        if (reportAndReply.first == null) {
            pw.print(FrontEndUtils.objectToBody("未找到投诉id", "1", ""));
            resp.setStatus(404);
            return;
        }

        // 投诉没有回应
        if (reportAndReply.second == null) {
            pw.print(FrontEndUtils.objectToBody("", "0", "投诉暂时没有回应"));
            resp.setStatus(200);
            return;
        }

        // 投诉有回应
        String respJson = FrontEndUtils.objectToBody("", "0", reportAndReply.second);
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