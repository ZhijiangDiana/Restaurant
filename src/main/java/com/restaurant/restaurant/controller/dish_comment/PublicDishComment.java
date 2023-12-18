package com.restaurant.restaurant.controller.dish_comment;

import java.io.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.R;
import com.restaurant.restaurant.pojo.entity.DishComment;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.service.dish_comment.UploadDishComment;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "PublicDishComment", value = "/PublicDishComment")
public class PublicDishComment extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        HttpSession session = req.getSession(true);

        JSONObject reqJson = FrontEndUtils.bodyToJson(req);
        System.out.println(reqJson==null);
        Integer dishId = reqJson.getInteger("dishId");
        Double score = reqJson.getDouble("score");
        String title = reqJson.getString("title");
        String body = reqJson.getString("body");
        byte[] image = reqJson.getBytes("image");

        User user = (User) session.getAttribute("user");
        Integer userCommentsPerOnline = (Integer) session.getAttribute("userCommentsPerOnline");

//        // 测试
//        user = new User();
//        user.setUserId(1);
//        user.setExperience(114);
//        userCommentsPerOnline = 1;

        if (user == null || userCommentsPerOnline == null || dishId == null || score == null ||
                title == null || body == null) {
            resp.setStatus(500);
            R respText = new R("给定数据不全或未登录", "1", "");
            String json = JSON.toJSONString(respText);
            pw.print(json);
            return;
        }

        DishComment dishComment = new DishComment();
        dishComment.setUserId(user.getUserId());
        dishComment.setDishId(dishId);
        dishComment.setScore(score);
        dishComment.setTittle(title);
        dishComment.setBody(body);
        dishComment.setImage(image);

        UploadDishComment uploadDishComment = new UploadDishComment();

        boolean isSuccess = uploadDishComment.commentDish(dishComment, user, userCommentsPerOnline);
        if (isSuccess) {
            session.setAttribute("userCommentsPerOnline", userCommentsPerOnline+1);
            pw.print(FrontEndUtils.objectToBody("", "0", null));
        } else {
            pw.print(FrontEndUtils.objectToBody("提交失败","1", null));
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doPost(req, resp);
    }

    @Override
    public void destroy() {

    }
}