package com.restaurant.restaurant.controller.canteen_manager;

import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.entity.Canteen;
import com.restaurant.restaurant.service.canteen_manager.Update_Canteen;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "Update_canteen_info",value = "/Update_canteen_info")
public class Update_canteen_info extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = resp.getWriter();

        JSONObject resJson = FrontEndUtils.bodyToJson(req);

        Integer canteen_id  = resJson.getInteger("canteen_id");
        String name = resJson.getString("name");
        String location = resJson.getString("location");
        Date start_time = resJson.getDate("start_time");
        Date end_time = resJson.getDate("end_time");
        String description = resJson.getString("description");
        byte[] image = resJson.getBytes("image");
        Integer report_count = resJson.getInteger("report_count");

        if(canteen_id<0 || name==null || location==null || start_time==null || end_time==null ||description==null||report_count<0){
            resp.setStatus(500);
            pw.print(FrontEndUtils.objectToBody("输入参数不正确","1",null));
            return;
        }
        Canteen canteen = new Canteen(canteen_id,name,location,start_time,end_time,description,image,report_count);
        Update_Canteen updateCanteen = new Update_Canteen();
        boolean isSuccess = updateCanteen.Update_Canteen(canteen);
        if (isSuccess) {
            String json = FrontEndUtils.objectToBody("提交成功", "0", null);
            pw.print(json);
        } else {
            pw.print(FrontEndUtils.objectToBody("提交失败", "1", null));
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
