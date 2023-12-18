package com.restaurant.restaurant.controller.admin.communityManage;

import com.restaurant.restaurant.mapper.CommentMapper;
import com.restaurant.restaurant.pojo.entity.Comment;
import com.restaurant.restaurant.utils.FrontEndUtils;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/showAllComment")
public class ShowAllComment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try {
            CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
            List<Comment> commentList = commentMapper.selectAll();
            sqlSession.close();
            response.getWriter().print(FrontEndUtils.objectToBody(null,"0",commentList));
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.close();
            response.getWriter().print(FrontEndUtils.objectToBody("系统繁忙","1",null));
        }
    }
}
