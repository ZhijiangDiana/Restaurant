package com.restaurant.restaurant.controller.admin.canteenManage;

import com.restaurant.restaurant.mapper.CanteenMapper;
import com.restaurant.restaurant.pojo.entity.Canteen;
import com.restaurant.restaurant.utils.FrontEndUtils;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/showAllCanteen")
public class ShowAllCanteen extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        CanteenMapper canteenMapper = sqlSession.getMapper(CanteenMapper.class);
        List<Canteen> canteenList = canteenMapper.selectAllWithFile();
        sqlSession.close();
        response.getWriter().print(FrontEndUtils.objectToBody(null,"0",canteenList));
    }
}
