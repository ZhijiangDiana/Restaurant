package com.restaurant.restaurant.controller.canteen;

import java.io.*;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.entity.Canteen;
import com.restaurant.restaurant.service.canteen.CalculateCanteenRank;
import com.restaurant.restaurant.utils.FrontEndUtils;
import com.restaurant.restaurant.utils.Pair;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "GetCanteenRank", value = "/GetCanteenRank")
public class GetCanteenRank extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        CalculateCanteenRank calculateCanteenRank = new CalculateCanteenRank();
        List<Pair<Double, Canteen>> res = calculateCanteenRank.calculateRank();

        String respJson = FrontEndUtils.objectToBody("", "0", res);
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