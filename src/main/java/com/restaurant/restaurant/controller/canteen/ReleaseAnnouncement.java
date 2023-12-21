package com.restaurant.restaurant.controller.canteen;

import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.entity.CanteenAdmin;
import com.restaurant.restaurant.service.canteen.AnnouncementService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ReleaseAnnouncement" ,value = "/ReleaseAnnouncement")
public class ReleaseAnnouncement extends HttpServlet  {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //首先要获取食堂管理员的id，这个id应该是存在session里的，然后拿到食堂管理员的id，根据这个id查到所管理的食堂canteenid
            PrintWriter pw = resp.getWriter();
            resp.setContentType("text/html;charset= UTF-8");

            CanteenAdmin canteenAdmin;
            canteenAdmin= (CanteenAdmin) req.getSession().getAttribute("canteenAdmin");

//            // 测试
//            canteenAdmin=new CanteenAdmin( 11,1,"拟跌","123456");
            if (canteenAdmin==null){
                String respJson = FrontEndUtils.objectToBody("未登录", "1", null);
                resp.getWriter().print(respJson);
                resp.setStatus(500);
                return;
            }
            Integer canteenId = canteenAdmin.getCanteenId();

            JSONObject reqJson = FrontEndUtils.bodyToJson(req);
            String announceTitle = reqJson.getString("announce_title");
            String announceBody = reqJson.getString("announce_body");



            AnnouncementService announcementService = new AnnouncementService();
            String res = announcementService.InsertAnnouncement(canteenId, announceTitle, announceBody);

            pw.print(res);



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


