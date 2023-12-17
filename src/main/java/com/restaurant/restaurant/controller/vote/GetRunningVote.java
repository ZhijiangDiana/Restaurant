package com.restaurant.restaurant.controller.vote;

import java.io.*;
import java.util.List;

import com.restaurant.restaurant.pojo.entity.Vote;
import com.restaurant.restaurant.service.vote.GetVoteService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "GetRunningVote", value = "/GetRunningVote")
public class GetRunningVote extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        ServletContext context = getServletContext();

        GetVoteService getVoteService = new GetVoteService();
        List<Vote> runningVotes = getVoteService.getRunningVotes(context);

        if(runningVotes == null || runningVotes.isEmpty()){
            pw.print(FrontEndUtils.objectToBody("投票列表为空","1",null));
        } else {
            String respJson_ = FrontEndUtils.objectToBody("", "0", runningVotes);
            String respJson = respJson_.replace("\\\"", "\"");
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