package com.restaurant.restaurant.controller.canteen_manager;

import com.restaurant.restaurant.pojo.entity.Comment;
import com.restaurant.restaurant.service.canteen_manager.SelectComment;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GetComment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset= UTF-8");
        PrintWriter pw = resp.getWriter();

        SelectComment selectComment = new SelectComment();
        List<Comment> commentList = selectComment.SelectComment();

        if(commentList == null){
            pw.print(FrontEndUtils.objectToBody("评论列表为空","1",null));
        }else{
            pw.print(FrontEndUtils.objectToBody("","0",commentList));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
