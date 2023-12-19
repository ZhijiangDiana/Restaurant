package com.restaurant.restaurant.controller.dish_comment;

import com.restaurant.restaurant.pojo.entity.CanteenAdmin;
import com.restaurant.restaurant.pojo.entity.DishComment;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.service.dish_comment.GetDishCommentService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GetUserDishComment" ,value = "/GetUserDishComment")
public class GetUserDishComment extends HttpServlet {
    /**
     * 用户获取所有发过的评价
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset= UTF-8");
        PrintWriter pw = resp.getWriter();

        HttpSession session = req.getSession(true);
        User user = (User) session.getAttribute("user");

//        // 测试
//        user = new User();
//        user.setUserId(1);

        if (user == null) {
            resp.setStatus(500);
            pw.print(FrontEndUtils.objectToBody("未登录", "1", null));
            return;
        }

        GetDishCommentService getDishCommentService = new GetDishCommentService();
        List<DishComment> res =
                getDishCommentService.getDishCommentByUser(user.getUserId());
        if (res == null || res.isEmpty())
            pw.print(FrontEndUtils.objectToBody("暂无评价", "1", null));
        else
            pw.print(FrontEndUtils.objectToBody("", "0", res));

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
