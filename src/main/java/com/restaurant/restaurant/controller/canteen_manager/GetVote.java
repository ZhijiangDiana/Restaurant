package com.restaurant.restaurant.controller.canteen_manager;

import com.restaurant.restaurant.pojo.entity.Vote;
import com.restaurant.restaurant.service.canteen_manager.SelectVote;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GetVote",value = "/GetVote")
public class GetVote extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = resp.getWriter();

        List<Vote> voteList = new ArrayList<>();
        SelectVote selectVote = new SelectVote();
        voteList = selectVote.SelectVote();

        if(voteList==null){
            pw.print(FrontEndUtils.objectToBody("投票列表为空","1",null));
        }
        else{
            pw.print(FrontEndUtils.objectToBody("","0",voteList));
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
