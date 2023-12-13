package com.restaurant.restaurant.controller.current_user_info;

import com.restaurant.restaurant.pojo.LevelInfo;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.utils.ExpLevelCaculate;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/getCurrentUserExp")
public class GetCurrentUserExp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        Integer experience = user.getExperience();
        LevelInfo levelInfo = ExpLevelCaculate.caculateLevel(experience);
        FrontEndUtils.objectToBody("等级信息","0",levelInfo);
    }
}
