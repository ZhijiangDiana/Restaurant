package com.restaurant.restaurant.controller.dish.canteen_admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.R;
import com.restaurant.restaurant.pojo.entity.Dish;
import com.restaurant.restaurant.service.canteen_manager.Update_Dish;
import com.restaurant.restaurant.utils.FrontEndUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UpdateDish",value = "/UpdateDish")
public class UpdateDish extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }


    /**
     * 要求前端预先把菜品的原始信息放到文本框中
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        JSONObject jsonObject = FrontEndUtils.bodyToJson(req);

        Integer dishId = jsonObject.getInteger("dishId");
        String dishName = jsonObject.getString("dishName");
        Integer series  = jsonObject.getInteger("series");
        Integer price = jsonObject.getInteger("price");
        byte[] image = jsonObject.getBytes("image");

        if(dishId == null || dishName == null || series == null || price == null){
            resp.setStatus(500);
            R respText = new R("参数不全","1","");
            String json = JSON.toJSONString(respText);
            pw.print(json);
            return;
        }

        Dish dish = new Dish();
        dish.setDishId(dishId);
        dish.setDishName(dishName);
        dish.setSeries(series);
        dish.setPrice(price);
        dish.setImage(image);

        Update_Dish upDateDish = new Update_Dish();
        boolean isSuccess = upDateDish.updateDish(dish);
        if (isSuccess) {
            String json = FrontEndUtils.objectToBody("修改成功", "0", null);
            pw.print(json);
        } else {
            pw.print(FrontEndUtils.objectToBody("修改失败", "1", null));
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
