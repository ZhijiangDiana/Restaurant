package com.restaurant.restaurant.controller.admin;

import com.restaurant.restaurant.service.AdminService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteCanteen")
public class DeleteCanteen extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String canteenId = request.getParameter("canteenId");
        AdminService adminService = new AdminService();
        response.getWriter().print(adminService.deleteCanteenById(canteenId));
    }
}
