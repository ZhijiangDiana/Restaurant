package com.restaurant.restaurant.controller.vote;

import com.restaurant.restaurant.pojo.entity.CanteenAdmin;
import com.restaurant.restaurant.pojo.entity.Vote;
import com.restaurant.restaurant.service.vote.GetVoteService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GetFinishedVote",value = "/GetFinishedVote")
public class GetFinishedVote extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = resp.getWriter();

        HttpSession session = req.getSession(true);
        CanteenAdmin canteenAdmin = (CanteenAdmin) session.getAttribute("canteenAdmin");

//        // 测试用
//        canteenAdmin = new CanteenAdmin();
//        canteenAdmin.setCanteenId(1);

        if (canteenAdmin == null) {
            resp.setStatus(403);
            pw.print(FrontEndUtils.objectToBody("未登录", "1", null));
            return;
        }

        GetVoteService getVoteService = new GetVoteService();
        List<Vote> voteList = getVoteService.getCanteenFinishedVotes(canteenAdmin.getCanteenId());

        if(voteList == null || voteList.isEmpty()){
            pw.print(FrontEndUtils.objectToBody("投票列表为空","1",null));
        } else {
            String respJson = FrontEndUtils.objectToBody("", "0", voteList);
            pw.print(respJson);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
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
