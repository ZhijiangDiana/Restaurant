package com.restaurant.restaurant.controller.admin.dishCommentManage;

import com.restaurant.restaurant.mapper.DishCommentMapper;
import com.restaurant.restaurant.pojo.entity.DishComment;
import com.restaurant.restaurant.utils.FrontEndUtils;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@WebServlet("/showAllDishComment")
public class ShowAllDishComment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        DishCommentMapper dishCommentMapper = sqlSession.getMapper(DishCommentMapper.class);
        List<DishComment> dishComments = dishCommentMapper.selectAll();
        sqlSession.close();
        response.getWriter().print(FrontEndUtils.objectToBody(null,"0",dishComments));
    }
}
