package com.restaurant.restaurant.controller.canteen_manager;

import com.alibaba.fastjson.JSON;
import com.restaurant.restaurant.mapper.DishMapper;
import com.restaurant.restaurant.pojo.R;
import com.restaurant.restaurant.pojo.entity.Announcement;
import com.restaurant.restaurant.service.canteen_manager.SelectAnnouncement;
import com.restaurant.restaurant.utils.FrontEndUtils;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "PostAnnouncement" ,  value = "/PostAnnouncement")
public class PostAnnouncement extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;chatset=UTF-8");
        PrintWriter pw = resp.getWriter();

        SelectAnnouncement selectAnnouncement = new SelectAnnouncement();
        List<Announcement> announcementList = selectAnnouncement.SelectAnnouncement();

        String announcement = FrontEndUtils.objectToBody("", "0", announcementList);
        resp.getWriter().print(announcement);
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
