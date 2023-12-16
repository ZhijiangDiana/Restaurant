package com.restaurant.restaurant.controller.canteen_manager;

import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.entity.Vote;
import com.restaurant.restaurant.service.canteen_manager.UpdateVote;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class PostVote extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        JSONObject jsonObject = FrontEndUtils.bodyToJson(req);

        Integer voteId = jsonObject.getInteger("vote_id");
        Integer canteenId = jsonObject.getInteger("canteen_id");
        String result = jsonObject.getString("result");

        if(result!=null){
            Vote vote = new Vote(voteId,canteenId,result);
            UpdateVote updateVote = new UpdateVote(vote);
            pw.print(FrontEndUtils.objectToBody("ok","0",null));
        }

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
