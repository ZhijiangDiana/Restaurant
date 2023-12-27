package com.restaurant.restaurant.controller.user_info_combination;

import java.io.*;
import java.util.HashMap;
import java.util.List;

import com.restaurant.restaurant.mapper.UserMapper;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.utils.FrontEndUtils;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.ibatis.session.SqlSession;

@WebServlet(name = "showReportReplyNum", value = "/showReportReplyNum", loadOnStartup = 0)
public class ShowReportReplyNum extends HttpServlet {

    @Override
    public void init() {
        ServletContext servletContext = getServletContext();
        HashMap<Integer,Integer> replyCounts = new HashMap<>();
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try(sqlSession){
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userMapper.selectAll();
            for (User user : userList) {
                System.out.println(user.getUserId());
                replyCounts.put(user.getUserId(),0);
            }
            servletContext.setAttribute("reportReplyCounts",replyCounts);
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        // TODO: 2023/12/28 未在回复servlet中给变量+1
        HashMap<Integer,Integer> reportReplyCount = (HashMap<Integer, Integer>) req.getServletContext().getAttribute("reportReplyCounts");
        System.out.println("reportReplyCount: " + reportReplyCount);
        User user = (User)req.getSession().getAttribute("user");
        if (user == null) {
            resp.setStatus(403);
            resp.getWriter().print(FrontEndUtils.objectToBody("账户未登录","1",null));
            return;
        }
        resp.getWriter().print(FrontEndUtils.objectToBody(null,"0", reportReplyCount.get(user.getUserId())));
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doPost(req, resp);
    }

    @Override
    public void destroy() {

    }
}