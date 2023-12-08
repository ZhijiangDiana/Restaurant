package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.utils.MyConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;

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
        String imageBase64 = "";
        if (filePart != null) {
            // 转换图像为 Base64
            InputStream fileContent = filePart.getInputStream();
            byte[] imageBytes = fileContent.readAllBytes();
            imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
        }
        try {
            Connection connection = MyConnection.getConnection();
            System.out.println(connection);
            String sql = "INSERT INTO comment (title, content, image, time, likes) VALUES (?, ?, ?, ?, ?)";
            try {
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1,title);
                pst.setString(2,content);
                pst.setBlob(3,imageStream);
                System.out.println(new Date().getTime());
                Timestamp timestamp1 = new Timestamp(new Date().getTime());
                pst.setTimestamp(4,timestamp1);
                pst.setString(5,"10");
                pst.execute();
                request.getRequestDispatcher("/info.html").forward(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
