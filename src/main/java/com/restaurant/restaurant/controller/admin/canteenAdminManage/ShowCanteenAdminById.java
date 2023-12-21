package com.restaurant.restaurant.controller.admin.canteenAdminManage;

import com.restaurant.restaurant.service.CanteenAdminService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/showCanteenAdminById")
public class ShowCanteenAdminById extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String canteenAdminId = request.getParameter("canteenAdminId");
        response.getWriter().print(new CanteenAdminService().selectCanteenAdminById(canteenAdminId));
    }
}
