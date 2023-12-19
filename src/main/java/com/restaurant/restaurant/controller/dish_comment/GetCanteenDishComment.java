package com.restaurant.restaurant.controller.dish_comment;

import com.restaurant.restaurant.pojo.entity.CanteenAdmin;
import com.restaurant.restaurant.pojo.entity.DishComment;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.service.dish_comment.GetDishCommentService;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GetCanteenDishComment" ,value = "/GetCanteenDishComment")
public class GetCanteenDishComment extends HttpServlet {
    /**
     * 食堂管理员获取所有本食堂菜品的评价
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset= UTF-8");
        PrintWriter pw = resp.getWriter();

        ServletContext context = getServletContext();
        HttpSession session = req.getSession(true);
        CanteenAdmin canteenAdmin = (CanteenAdmin) session.getAttribute("canteenAdmin");

//        // 测试
//        canteenAdmin = new CanteenAdmin();
//        canteenAdmin.setCanteenId(2);

        if (canteenAdmin == null) {
            resp.setStatus(500);
            pw.print(FrontEndUtils.objectToBody("未登录", "1", null));
            return;
        }

        GetDishCommentService getDishCommentService = new GetDishCommentService();
        List<DishComment> res =
                getDishCommentService.getDishCommentByCanteen(canteenAdmin.getCanteenId(), context);
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
