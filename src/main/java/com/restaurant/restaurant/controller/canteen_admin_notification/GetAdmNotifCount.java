package com.restaurant.restaurant.controller.canteen_admin_notification;

import java.io.*;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.restaurant.restaurant.pojo.entity.CanteenAdmin;
import com.restaurant.restaurant.pojo.entity.DishComment;
import com.restaurant.restaurant.pojo.entity.Report;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

// 必须启动时加载，否则login会报NPE
@WebServlet(name = "GetAdmNotifCount", value = "/GetAdmNotifCount", loadOnStartup = 0)
public class GetAdmNotifCount extends HttpServlet {

    // TODO: 2023/12/19
    //  （完成）每次管理员登录后都应检查map中是否有键，无键则添加entry，注意使用ConcurrentHashSet
    //  （完成）每次用户发起投诉和评论后都应添加notification
    //  （完成）每次管理员请求notification列表后清空notification
    //  init时从磁盘中读取记录，destroy时向磁盘写入记录（可选）
    @Override
    public void init() {
        ServletContext context = getServletContext();
        // K：canteenId   V：投诉或评论对象
        Map<Integer, Map<Integer, Report>> reportNotif = new ConcurrentHashMap<>();
        Map<Integer, Map<Integer, DishComment>> dishCommentNotif = new ConcurrentHashMap<>();
        context.setAttribute("reportNotif", reportNotif);
        context.setAttribute("dishCommentNotif", dishCommentNotif);

//        // 测试
//        reportNotif.put(2, new ConcurrentHashMap<>());
//        dishCommentNotif.put(2, new ConcurrentHashMap<>());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        HttpSession session = req.getSession(true);
        ServletContext context = getServletContext();
        CanteenAdmin canteenAdmin = (CanteenAdmin) session.getAttribute("canteenAdmin");

//        // 测试
//        canteenAdmin = new CanteenAdmin();
//        canteenAdmin.setCanteenId(2);

        if (canteenAdmin == null) {
            resp.setStatus(500);
            pw.print(FrontEndUtils.objectToBody("未登录", "1", null));
            return;
        }

        Map<Integer, Map<Integer, Report>> reportNotif =
                (Map<Integer, Map<Integer, Report>>) context.getAttribute("reportNotif");
        Map<Integer, Map<Integer, DishComment>> dishCommentNotif =
                (Map<Integer, Map<Integer, DishComment>>) context.getAttribute("dishCommentNotif");
        Integer cnt = reportNotif.get(canteenAdmin.getCanteenId()).size() +
                dishCommentNotif.get(canteenAdmin.getCanteenId()).size();

        pw.print(FrontEndUtils.objectToBody("", "0", cnt));
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doPost(req, resp);
    }

    @Override
    public void destroy() {

    }
}