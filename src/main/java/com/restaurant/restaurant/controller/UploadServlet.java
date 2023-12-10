package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.mapper.CommentMapper;
import com.restaurant.restaurant.pojo.entity.Comment;
import com.restaurant.restaurant.utils.MyConnection;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;

/**
 * 上传评论信息
 */
@WebServlet("/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // 获取表单文本数据
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        // 获取上传的文件 二进制输入流
        Part filePart = request.getPart("image");
        InputStream imageStream = filePart.getInputStream();
        InputStream fileContent = filePart.getInputStream();
        byte[] imageBytes = fileContent.readAllBytes();
        String imageBase64 = "";
        Comment comment = new Comment(2,"臭","真的好臭",imageBytes,new Date(),114);
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);
        mapper.insertComment(comment);
        sqlSession.commit();
        request.getRequestDispatcher("/test.html").forward(request,response);
    }
}
