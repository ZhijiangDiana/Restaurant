package com.restaurant.restaurant.controller.canteen;

import java.io.*;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.entity.Canteen;
import com.restaurant.restaurant.service.canteen.GetCanteenService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "GetCanteen", value = "/GetCanteen")
public class GetCanteen extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        GetCanteenService getCanteenService = new GetCanteenService();
        List<Canteen> res = getCanteenService.getAllCanteen();

        if (res == null || res.isEmpty()) {
            String respJson = FrontEndUtils.objectToBody("暂没有食堂入驻", "1", null);
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