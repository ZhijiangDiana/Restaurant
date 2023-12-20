package com.restaurant.restaurant.controller.user_info_combination;

import com.restaurant.restaurant.service.vote.CombinedInfoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.annotations.Param;

import java.io.IOException;

@WebServlet("/showLatestVote")
public class ShowLatestVote extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print(new CombinedInfoService().showLatestVote());
    }
}
