package com.restaurant.restaurant.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GenericVerification {

    public static String generateRandomString() {
        int length = 6;
        Random rand = new Random();
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder captcha = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            captcha.append(characters.charAt(rand.nextInt(characters.length())));
        }
        return captcha.toString();
    }

    public static BufferedImage genericImage(String verification){
        // 创建图片和绘制环境
        BufferedImage bufferedImage = new BufferedImage(120, 30, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        Font font = new Font("Arial", Font.BOLD, 18);
        g2d.setFont(font);
        g2d.setColor(new Color(220, 220, 220));
        g2d.fillRect(0, 0, 120, 30);

        g2d.setColor(new Color(150, 150, 150));
        g2d.drawRect(0, 0, 119, 29);

        g2d.setColor(Color.BLACK);
        g2d.drawString(verification, 10, 20);

        // 清理资源
        g2d.dispose();
        return bufferedImage;
    }
}
