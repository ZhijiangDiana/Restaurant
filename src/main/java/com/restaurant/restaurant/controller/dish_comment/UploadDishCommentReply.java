package com.restaurant.restaurant.controller.dish_comment;

import java.io.*;

import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.entity.CanteenAdmin;
import com.restaurant.restaurant.pojo.entity.DishCommentReply;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.service.dish_comment.UploadDishCommentReplyService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "UploadDishCommentReply", value = "/UploadDishCommentReply")
public class UploadDishCommentReply extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        HttpSession session = req.getSession(true);

        JSONObject reqJson = FrontEndUtils.bodyToJson(req);
        Integer dishCommentId = reqJson.getInteger("dishCommentId");
        String title = reqJson.getString("title");
        String body = reqJson.getString("body");
        CanteenAdmin canteenAdmin = (CanteenAdmin) session.getAttribute("canteenAdmin");

//        // 测试
//        canteenAdmin = new CanteenAdmin();
//        canteenAdmin.setCanteenAdminId(111111);

        if (dishCommentId == null || title == null || body == null || canteenAdmin == null) {
            resp.setStatus(500);
            pw.print(FrontEndUtils.objectToBody("参数缺失或未登录", "1", null));
            return;
        }

        DishCommentReply dishCommentReply = new DishCommentReply();
        dishCommentReply.setDishCommentId(dishCommentId);
        dishCommentReply.setTitle(title);
        dishCommentReply.setBody(body);
        dishCommentReply.setCanteenAdminId(canteenAdmin.getCanteenAdminId());

        UploadDishCommentReplyService uploadDishCommentReplyService = new UploadDishCommentReplyService();
        boolean isSuccess = uploadDishCommentReplyService.replyComment(dishCommentReply);
        if (isSuccess)
            pw.print(FrontEndUtils.objectToBody("", "0", null));
        else
            pw.print(FrontEndUtils.objectToBody("提交失败", "1", null));
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doPost(req, resp);
    }

    @Override
    public void destroy() {

    }
}