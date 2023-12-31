package com.restaurant.restaurant.controller.canteen;

import java.io.*;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.entity.Announcement;
import com.restaurant.restaurant.pojo.entity.Canteen;
import com.restaurant.restaurant.pojo.entity.CanteenAdmin;
import com.restaurant.restaurant.service.canteen.GetCanteenAnnouncementService;
import com.restaurant.restaurant.service.canteen.GetCanteenService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "GetCanteenAnnouncement", value = "/GetCanteenAnnouncement")
public class GetCanteenAnnouncement extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        HttpSession session = req.getSession(true);
        CanteenAdmin canteenAdmin = (CanteenAdmin) session.getAttribute("canteenAdmin");
        Integer canteenId;
        if (canteenAdmin == null) {
            JSONObject reqJson = FrontEndUtils.bodyToJson(req);
            canteenId = reqJson.getInteger("canteenId");
            if (canteenId == null) {
                resp.setStatus(403);
                pw.print(FrontEndUtils.objectToBody("未登录", "1", null));
                return;
            }
        } else {
            canteenId = canteenAdmin.getCanteenId();
        }

        GetCanteenAnnouncementService getCanteenAnnouncementService = new GetCanteenAnnouncementService();
        List<Announcement> res = getCanteenAnnouncementService.getCanteenAnnouncement(canteenId);

        if (res == null || res.isEmpty()) {
            String respJson = FrontEndUtils.objectToBody("食堂暂时没有公告", "1", null);
            pw.print(respJson);
        } else {
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