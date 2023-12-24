package com.restaurant.restaurant.controller.admin.dishCommentManage;

import com.restaurant.restaurant.pojo.entity.DishComment;
import com.restaurant.restaurant.service.AdminService;
import com.restaurant.restaurant.service.dish_comment.GetDishCommentService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/ShowDishCommentById")
public class ShowDishCommentById extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GetDishCommentService getDishCommentService = new GetDishCommentService();
        int id = Integer.parseInt(request.getParameter("id"));
//        int id=24;
        DishComment dishComment = getDishCommentService.showDishCommentByID(id);
        String flag = dishComment == null ? "1" : "0";
        response.getWriter().print(FrontEndUtils.objectToBody("",flag,dishComment));
    }
}

