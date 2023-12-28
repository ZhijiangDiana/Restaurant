package com.restaurant.restaurant.controller.report;

import java.io.*;
import java.util.List;

import com.restaurant.restaurant.pojo.ReportReplyShow;
import com.restaurant.restaurant.pojo.entity.ReportReply;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.service.report.SelectReportReply;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "GetUserReportReply", value = "/GetUserReportReply")
public class GetUserReportReply extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.setStatus(403);
            pw.print(FrontEndUtils.objectToBody("未登录", "1", null));
            return;
        }

        int userId = user.getUserId();
        ServletContext context = getServletContext();
        SelectReportReply selectReportReply = new SelectReportReply();
        List<ReportReplyShow> res = selectReportReply.selectReportReply(userId, context);

        pw.print(FrontEndUtils.objectToBody("", "0", res));
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doPost(req, resp);
    }

    @Override
    public void destroy() {

    }
}