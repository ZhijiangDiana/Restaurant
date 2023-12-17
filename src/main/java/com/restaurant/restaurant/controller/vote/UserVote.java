package com.restaurant.restaurant.controller.vote;

import java.io.*;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.RunningVote;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.service.vote.UserVoteService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "UserVote", value = "/UserVote")
public class UserVote extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        JSONObject reqJson = FrontEndUtils.bodyToJson(req);
        HttpSession session = req.getSession(true);
        ServletContext context = getServletContext();

        Integer voteId = reqJson.getInteger("voteId");
        JSONArray choices = reqJson.getJSONArray("choices");
        User user = (User) session.getAttribute("user");

//        // 测试用
//        user = new User();
//        user.setUserId(1);

        if (voteId == null || choices == null || user == null) {
            resp.setStatus(500);
            pw.print(FrontEndUtils.objectToBody("参数缺失或未登录", "1", null));
            return;
        }

        UserVoteService userVoteService = new UserVoteService();
        boolean isSuccess = userVoteService.userVote(context, voteId, user.getUserId(), choices);
        if (isSuccess)
            pw.print(FrontEndUtils.objectToBody("投票成功", "0", null));
        else
            pw.print(FrontEndUtils.objectToBody("投票不存在", "1", null));
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doPost(req, resp);
    }

    @Override
    public void destroy() {

    }
}