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

/**
 * 服务器启动时执行init方法
 */
@WebServlet(value = "/showThumbsupNum",loadOnStartup = 0)
public class ShowThumbsupNum extends HttpServlet {
    @Override
    public void init() throws ServletException {
        ServletContext servletContext = getServletContext();
        HashMap<Integer,Integer> thumbsupCounts = new HashMap<>();
        HashMap<Integer, List<Integer>> thumbsupDetails = new HashMap<>();
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try(sqlSession){
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userMapper.selectAll();
            for (User user : userList) {
                thumbsupCounts.put(user.getUserId(),0);
                thumbsupDetails.put(user.getUserId(),null);
            }
            servletContext.setAttribute("thumbsupCounts",thumbsupCounts);
            servletContext.setAttribute("thumbsupDetails",thumbsupDetails);
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        HashMap<Integer,Integer> thumbsupCounts = (HashMap<Integer, Integer>) request.getServletContext().getAttribute("thumbsupCounts");
        System.out.println("showThumbsupNum: " + thumbsupCounts);
        if (user == null){
            response.setStatus(403);
            response.getWriter().print(FrontEndUtils.objectToBody("暂未登陆","1",null));
            return;
        }
        response.getWriter().print(FrontEndUtils.objectToBody(null,"0", thumbsupCounts.get(user.getUserId())));
    }
}
