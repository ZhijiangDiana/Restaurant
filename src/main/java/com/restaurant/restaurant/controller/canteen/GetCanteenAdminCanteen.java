package com.restaurant.restaurant.controller.canteen;

import java.io.*;

import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.entity.Canteen;
import com.restaurant.restaurant.pojo.entity.CanteenAdmin;
import com.restaurant.restaurant.service.canteen.GetCanteenService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "GetCanteenAdminCanteen", value = "/GetCanteenAdminCanteen")
public class GetCanteenAdminCanteen extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        HttpSession session = req.getSession(true);
        CanteenAdmin canteenAdmin = (CanteenAdmin) session.getAttribute("canteenAdmin");
        if (canteenAdmin == null) {
            resp.setStatus(403);
            pw.print(FrontEndUtils.objectToBody("未登录", "1", null));
            return;
        }

        Integer canteenId = canteenAdmin.getCanteenId();

        GetCanteenService getCanteenService = new GetCanteenService();
        Canteen res = getCanteenService.getCanteenInfo(canteenId);

        if (res == null) {
            resp.setStatus(404);
            String respJson = FrontEndUtils.objectToBody("未找到该食堂", "1", null);
            pw.print(respJson);
        } else {
            resp.setStatus(200);
            String respJson = FrontEndUtils.objectToBody("", "0", res);
            pw.print(respJson);
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