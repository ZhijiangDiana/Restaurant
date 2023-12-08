package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.utils.GenericVerification;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet("/captcha")
public class VerificationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/png");
        String verification = GenericVerification.generateRandomString();
        request.getSession().setAttribute("verification",verification);
        BufferedImage bufferedImage = GenericVerification.genericImage(verification);
        // 输出图片
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(bufferedImage, "png", outputStream);
        outputStream.close();
    }

}
