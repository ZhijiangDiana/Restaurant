package com.restaurant.restaurant.controller.community;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.mapper.CommentMapper;
import com.restaurant.restaurant.pojo.entity.Comment;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.service.ReplyService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import com.restaurant.restaurant.utils.LegalSpeakFilter;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/publishReply")
public class PublishReply extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        // 使用 Fastjson 解析 JSON 字符串
        JSONObject jsonObject = JSON.parseObject(sb.toString());
        // 从 JSON 对象中提取数据
        String commentId = jsonObject.getString("commentId");
        String body = jsonObject.getString("body");

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.setStatus(403);
            response.getWriter().print(FrontEndUtils.objectToBody("未登录","1",null));
            return ;
        }

        String id = String.valueOf(user.getUserId());
        System.out.println(commentId + body + id);
        if (body == null || body.isEmpty()){
            response.getWriter().print(FrontEndUtils.objectToBody("内容不能为空","1",null));
            return ;
        }


//        if (LegalSpeakFilter.filterSensitiveWords(body) == true){
//            response.getWriter().print(FrontEndUtils.objectToBody("涉及敏感发言","1",null));
//            return ;
//        }
//
//        if (LegalSpeakFilter.banFromSpeaking(Integer.parseInt(id))){
//            response.getWriter().print(FrontEndUtils.objectToBody("由于过往潜在不文明行为被禁止评论","1",null));
//        }

        ReplyService replyService = new ReplyService();
        replyService.addReply(id,commentId,body);
        // 小红点提醒

        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
        try (sqlSession) {
            Comment comment = commentMapper.selectById(Integer.parseInt(commentId));
            Integer userId = comment.getUserId();
            HashMap<Integer,Integer> replyCounts =(HashMap<Integer, Integer>) request.getServletContext().getAttribute("replyCounts");
            Integer counts = replyCounts.get(userId);
            counts += 1;
            replyCounts.put(userId,counts);
            request.getServletContext().setAttribute("replyCounts",replyCounts);
            response.getWriter().print(FrontEndUtils.objectToBody("添加成功","0",null));
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
            response.getWriter().print(FrontEndUtils.objectToBody("添加失败","1",null));
        }

    }
}
