package com.restaurant.restaurant.controller.canteen;

import java.io.*;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.entity.Announcement;
import com.restaurant.restaurant.pojo.entity.CanteenAdmin;
import com.restaurant.restaurant.pojo.entity.Dish;
import com.restaurant.restaurant.service.canteen.GetCanteenAnnouncementService;
import com.restaurant.restaurant.service.canteen.GetCanteenDishService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "GetCanteenDish", value = "/GetCanteenDish")
public class GetCanteenDish extends HttpServlet {

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
            pw.print(FrontEndUtils.objectToBody("未登录或参数不全", "1", null));
            return;
        }
        Integer canteenId = canteenAdmin.getCanteenId();

        GetCanteenDishService getCanteenDishService = new GetCanteenDishService();
        List<Dish> res = getCanteenDishService.getCanteenDish(canteenId);

        if (res == null || res.isEmpty()) {
            String respJson = FrontEndUtils.objectToBody("食堂暂时没有菜品", "1", null);
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