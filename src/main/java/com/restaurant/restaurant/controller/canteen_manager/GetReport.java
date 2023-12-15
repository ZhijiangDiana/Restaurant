package com.restaurant.restaurant.controller.canteen_manager;
import com.restaurant.restaurant.pojo.entity.Report;
import com.restaurant.restaurant.service.canteen_manager.SelectReport;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GetReport",value = "/GetReport")
public class GetReport extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:7000");
        resp.setContentType("text/html;chatset=UTF-8");

        SelectReport selectReport = new SelectReport();
        List<Report> reportList = selectReport.SelectReport();

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
