package com.restaurant.restaurant.controller.canteen_manager;
import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.entity.Canteen;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.service.canteen_manager.SelectByAdminName;
import com.restaurant.restaurant.service.canteen_manager.SelectCanteenById;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/*
    查询当前用户所管理的餐厅的具体信息
 */
@WebServlet(name ="SelectCanteenByName",value = "/SelectCanteenByName")
public class SelectCanteenByName extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset = UTF-8");
        PrintWriter pw = resp.getWriter();
        User user = (User)req.getSession().getAttribute("user");
        String username = user.getUsername();

        //用用户名查管理的食堂
        SelectByAdminName selectByAdminName=new SelectByAdminName();
        int canteen_id = selectByAdminName.SelectByAdminName(username);

        //用食堂id查对应的详细信息
        SelectCanteenById selectCanteenById = new SelectCanteenById();
        Canteen canteen = selectCanteenById.SelectCanteenById(canteen_id);

        if(canteen==null){
            resp.setStatus(500);
            pw.print(FrontEndUtils.objectToBody("查询失败","1",null));
            return;
        }
        else{
           pw.print(FrontEndUtils.objectToBody("","0",canteen));
        }
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
