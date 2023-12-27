package com.restaurant.restaurant.controller.user_info_combination;

import com.restaurant.restaurant.mapper.UserMapper;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.utils.FrontEndUtils;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(value = "/showReplyNum",loadOnStartup = 0)
public class ShowReplyNum extends HttpServlet {
    @Override
    public void init() throws ServletException {
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
            servletContext.setAttribute("replyCounts",replyCounts);
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object a = request.getServletContext().getAttribute("a");
        HashMap<Integer,Integer> replyCounts = (HashMap<Integer, Integer>) request.getServletContext().getAttribute("replyCounts");
        System.out.println("showReplyNum: " + replyCounts);
        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
            response.setStatus(403);
            response.getWriter().print(FrontEndUtils.objectToBody("账户未登录","1",null));
            return;
        }
        response.getWriter().print(FrontEndUtils.objectToBody(null,"0",replyCounts.get(user.getUserId())));
    }
}
