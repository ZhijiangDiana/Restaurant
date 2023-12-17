package com.restaurant.restaurant.controller.vote;

import java.io.*;

import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.service.vote.GetVoteService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "GetCanVote", value = "/GetCanVote")
public class GetCanVote extends HttpServlet {

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
        User user = (User) session.getAttribute("user");

//        // 测试用
//        user = new User();
//        user.setUserId(1);

        if (voteId == null || user == null) {
            resp.setStatus(500);
            pw.print(FrontEndUtils.objectToBody("参数缺失或未登录", "1", null));
            return;
        }

        GetVoteService getVoteService = new GetVoteService();
        int canVote = getVoteService.getCanVote(context, voteId, user.getUserId());
        switch (canVote) {
            case GetVoteService.VOTE_FINISHED:
                pw.print(FrontEndUtils.objectToBody("投票已结束", "1", canVote));
                break;
            case GetVoteService.HAS_VOTED:
                pw.print(FrontEndUtils.objectToBody("已投票，请勿重复投票", "1", canVote));
                break;
            case GetVoteService.CAN_VOTE:
                pw.print(FrontEndUtils.objectToBody("", "0", canVote));
                break;
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