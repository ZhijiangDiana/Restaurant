package com.restaurant.restaurant.controller.report;
import com.restaurant.restaurant.pojo.entity.CanteenAdmin;
import com.restaurant.restaurant.pojo.entity.Report;
import com.restaurant.restaurant.service.report.SelectReportService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GetNoReplyReport",value = "/GetNoReplyReport")
public class GetNoReplyReport extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        ServletContext context = getServletContext();
        HttpSession session = req.getSession(true);
        CanteenAdmin canteenAdmin = (CanteenAdmin) session.getAttribute("canteenAdmin");

//        // 测试用
//        canteenAdmin = new CanteenAdmin();
//        canteenAdmin.setCanteenId(2);

        if (canteenAdmin == null) {
            resp.setStatus(500);
            pw.print(FrontEndUtils.objectToBody("未登录", "1", null));
            return;
        }

        SelectReportService selectReportService = new SelectReportService();
        List<Report> reportList = selectReportService.selectReport(canteenAdmin.getCanteenId(), context);
        if (reportList == null || reportList.isEmpty()) {
            pw.print(FrontEndUtils.objectToBody("暂时没有未回复投诉", "1", null));
            return;
        }

        String report = FrontEndUtils.objectToBody("","0",reportList);
        resp.getWriter().print(report);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          doGet(req,resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
