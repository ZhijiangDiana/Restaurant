package com.restaurant.restaurant.controller.vote;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.entity.CanteenAdmin;
import com.restaurant.restaurant.pojo.entity.Vote;
import com.restaurant.restaurant.service.vote.StoreVoteResultTask;
import com.restaurant.restaurant.service.vote.UploadVoteService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "UploadVote",value = "/UploadVote")
public class UploadVote extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        JSONObject jsonObject = FrontEndUtils.bodyToJson(req);
        HttpSession session = req.getSession(true);
        ServletContext context = getServletContext();

        Integer canteenId = jsonObject.getInteger("canteenId");
        Date startTime = jsonObject.getDate("startTime");
        Date endTime = jsonObject.getDate("endTime");
        String title = jsonObject.getString("title");
        JSONArray choices = jsonObject.getJSONArray("choices");
        CanteenAdmin canteenAdmin = (CanteenAdmin) session.getAttribute("canteenAdmin");
        Map<Integer, Map<String, Integer>> votes =
                (Map<Integer, Map<String, Integer>>) context.getAttribute("votes");

//        // 测试用
//        canteenAdmin = new CanteenAdmin();
//        canteenAdmin.setCanteenId(1);

        // 参数判空
        if (canteenAdmin == null || canteenId == null ||
                startTime == null || endTime == null || title == null || choices == null) {
            resp.setStatus(500);
            pw.print(FrontEndUtils.objectToBody("参数缺失或未登录", "1", null));
            return;
        }
        // 对endTime的合法性进行检查
        if (endTime.before(new Date(System.currentTimeMillis()))) {
            resp.setStatus(500);
            pw.print(FrontEndUtils.objectToBody("所选时间已过", "1", null));
            return;
        }

        // 在数据库创建vote记录并获得记录id
        Vote vote = new Vote();
        vote.setCanteenId(canteenAdmin.getCanteenId());
        vote.setTitle(title);
        vote.setStartTime(startTime);
        vote.setEndTime(endTime);
        UploadVoteService uploadVoteService = new UploadVoteService();
        int voteId = uploadVoteService.uploadVote(vote);

        // 将投票放入context中
        Map<String, Integer> voteResult = new HashMap<>(114);
        List<String> choiceList = choices.toJavaList(String.class);
        for (String choice : choiceList)  // 遍历choices，将voteResult表放入context中
            voteResult.put(choice, 0);
        votes.put(voteId, voteResult);
        context.setAttribute("votes", votes);

        // 定时器，启动！
        Timer collectResultTimer = new Timer(true);
        TimerTask collectResult = new StoreVoteResultTask(vote, context);
        collectResultTimer.schedule(collectResult, endTime);

        pw.print(FrontEndUtils.objectToBody("","0",null));
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();

        ServletContext context = getServletContext();
        Map<Integer, Map<String, Integer>> vote = new HashMap<>();
        context.setAttribute("votes", vote);
    }
}
