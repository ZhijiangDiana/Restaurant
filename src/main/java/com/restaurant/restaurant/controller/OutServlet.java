package com.restaurant.restaurant.controller;

import com.alibaba.fastjson.JSON;
import com.restaurant.restaurant.pojo.Comment;
import com.restaurant.restaurant.utils.MyConnection;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@WebServlet("/ou")
public class OutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
    }

        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            List<Comment> commentList = new ArrayList<>();
            Comment comment = null;
            PrintWriter out = response.getWriter();
            try {
                Connection conn = MyConnection.getConnection();
                String sql = "SELECT comment_id,title, content, image ,time FROM comment";
                try (PreparedStatement statement = conn.prepareStatement(sql);
                     ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Integer id = resultSet.getInt("comment_id");
                        String title = resultSet.getString("title");
                        String content = resultSet.getString("content");
                        byte[] imgBytes = resultSet.getBytes("image");
                        Date d = resultSet.getDate("time");
                        String imgBase64 = Base64.getEncoder().encodeToString(imgBytes);
                        comment = new Comment(id,title,content,imgBase64,d,10);
                        commentList.add(comment);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

                String s = JSON.toJSONString(commentList);
                out.print(s);

    }
}
