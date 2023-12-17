package com.restaurant.restaurant.service;

import com.restaurant.restaurant.mapper.*;
import com.restaurant.restaurant.pojo.entity.Canteen;
import com.restaurant.restaurant.pojo.entity.CanteenAdmin;
import com.restaurant.restaurant.pojo.entity.DishComment;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.utils.FrontEndUtils;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class AdminService {
    SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();

    public String deleteCanteenById(String id){
        CanteenMapper canteenMapper = sqlSession.getMapper(CanteenMapper.class);
        int isSuccess = canteenMapper.deleteCanteenById(Integer.parseInt(id));
        sqlSession.commit();
        List<Canteen> canteenList = canteenMapper.selectAll();
        if (isSuccess == 1){
            sqlSession.commit();
            return FrontEndUtils.objectToBody("删除成功","0",canteenList);
        }
        else {
            sqlSession.commit();
            return FrontEndUtils.objectToBody("删除失败","1",null);
        }

    }

    public String addCanteen(String name, String location, Time startTime, Time endTime, String description, byte[] image){
        CanteenMapper mapper = sqlSession.getMapper(CanteenMapper.class);
        if (name == null || name == ""){
            return FrontEndUtils.objectToBody("餐厅姓名不能为空","1",null);
        } else if (location == null || location == "") {
            return FrontEndUtils.objectToBody("餐厅地址不能为空","1",null);
        } else if (startTime == null) {
            return FrontEndUtils.objectToBody("餐厅营业时间不能为空","1",null);
        }else if (endTime == null) {
            return FrontEndUtils.objectToBody("餐厅营业时间不能为空","1",null);
        } else if (description == null || description == "") {
            return FrontEndUtils.objectToBody("餐厅描述不能为空","1",null);
        }else if (image == null) {
            return FrontEndUtils.objectToBody("餐厅图像不能为空","1",null);
        }else {
            List<Canteen> canteens = mapper.selectAll();
            for (Canteen canteen : canteens) {
                String canteenName = canteen.getName();
                if (canteenName.equals(name))
                    return FrontEndUtils.objectToBody("餐厅姓名重复","0",null);
            }
            Canteen canteen = new Canteen(name,location,startTime,endTime,description,image);
            int isSuccess = mapper.insertCanteen(canteen);
            sqlSession.commit();
            List<Canteen> canteenList = mapper.selectAll();
            sqlSession.close();
            if (isSuccess == 1){
                return FrontEndUtils.objectToBody("创建成功","0",canteenList);
            }
            else {
                return FrontEndUtils.objectToBody("创建失败","1",null);
            }
        }

    }

    public String updateCanteen(String id,String name, String location, Time startTime, Time endTime, String description, byte[] image,String reportCount){
        CanteenMapper mapper = sqlSession.getMapper(CanteenMapper.class);
        if (name == null || name == ""){
            return FrontEndUtils.objectToBody("餐厅姓名不能为空","1",null);
        } else if (location == null || location == "") {
            return FrontEndUtils.objectToBody("餐厅地址不能为空","1",null);
        } else if (startTime == null) {
            return FrontEndUtils.objectToBody("餐厅营业时间不能为空","1",null);
        }else if (endTime == null) {
            return FrontEndUtils.objectToBody("餐厅营业时间不能为空","1",null);
        } else if (description == null || description == "") {
            return FrontEndUtils.objectToBody("餐厅描述不能为空","1",null);
        }else if (image == null) {
            return FrontEndUtils.objectToBody("餐厅图像不能为空","1",null);
        }else if (id == null){
            return FrontEndUtils.objectToBody("餐厅id不能为空","1",null);
        }else if (reportCount == null || reportCount == ""){
            return FrontEndUtils.objectToBody("餐厅投诉数量不能为空","1",null);
        }else {
            List<Canteen> canteens = mapper.selectAll();
            for (Canteen canteen : canteens) {
                String canteenName = canteen.getName();
                if (canteenName.equals(name))
                    return FrontEndUtils.objectToBody("餐厅姓名重复","0",null);
            }
            Canteen canteen = new Canteen(Integer.parseInt(id),name,location,startTime,endTime,description,image,Integer.parseInt(reportCount));
            int isSuccess = mapper.updateCanteen(canteen);
            sqlSession.commit();
            List<Canteen> canteenList = mapper.selectAll();
            sqlSession.close();
            if (isSuccess == 1){
                return FrontEndUtils.objectToBody("修改成功","0",canteenList);
            }
            else {
                return FrontEndUtils.objectToBody("修改失败","1",null);
            }
        }
    }

    /**
     * 不用再挨个判断是否为空了，如果是空前端会告知
     * @param id
     * @param username
     * @param password
     * @param experience
     * @param isForbidden
     * @param illegality
     * @return
     */
    public String updateUser(String id,String username,String password,String experience,String isForbidden,String illegality){
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            if(user.getUsername().equals(username)){
                return FrontEndUtils.objectToBody("姓名不能重复","1",null);
            }
        }
        Boolean isForbiddenBool = null;
        if (isForbidden.equals("是")){
            isForbiddenBool = true;
        }
        else
            isForbiddenBool = false;
        User user = new User(Integer.parseInt(id),username,password,Integer.parseInt(experience),isForbiddenBool,Integer.parseInt(illegality));
        int isSuccess = userMapper.updateUser(user);
        sqlSession.commit();
        if (isSuccess == 1){
            List<User> userList = userMapper.selectAll();
            sqlSession.commit();
            return FrontEndUtils.objectToBody("修改成功","0",userList);
        }
        else
            return FrontEndUtils.objectToBody("修改失败","1",null);
    }

    public String deleteUserById(String id){
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int isSuccess = userMapper.deleteUserById(Integer.parseInt(id));
        sqlSession.commit();
        sqlSession.close();
        if (isSuccess == 1)
            return FrontEndUtils.objectToBody("删除成功","0",null);
        else
            return FrontEndUtils.objectToBody("删除失败","1",null);
    }

    public String addUser(String id,String username,String password,String experience,String isForbidden,String illegality){
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.selectAll();
        for (User user : userList) {
            if (user.getUserId() == Integer.parseInt(id))
                return FrontEndUtils.objectToBody("重复用户,重新输入","1",null);
        }
        Boolean isForbiddenBool = null;
        if ("是".equals(isForbidden))
            isForbiddenBool = true;
        else
            isForbiddenBool = false;
        User user = new User(Integer.parseInt(id),username,password,Integer.parseInt(experience),isForbiddenBool,Integer.parseInt(illegality));
        userMapper.insertUser(user);
        sqlSession.commit();
        sqlSession.close();
        return FrontEndUtils.objectToBody("增添成功","0",null);
    }

    /**
     * 这里的食堂名字不可能拿空，食堂名字要从数据库拿
     * 先委派成食堂管理员然后在师生中删除该用户
     * @param id
     * @param canteenName
     * @return
     */
    public String setUserToCanteenAdmin(String id,String canteenName){
        CanteenMapper canteenMapper = sqlSession.getMapper(CanteenMapper.class);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        CanteenAdminMapper canteenAdminMapper = sqlSession.getMapper(CanteenAdminMapper.class);
        User user = userMapper.selectById(Integer.parseInt(id));
        List<Canteen> canteenList = canteenMapper.selectAll();
        Integer appointedCanteenId = null;
        for (Canteen canteen : canteenList) {
            if (canteen.getName().equals(canteenName)) {
                appointedCanteenId = canteen.getCanteenId();
            }
        }
        CanteenAdmin canteenAdmin = new CanteenAdmin(Integer.parseInt(id),appointedCanteenId,user.getUsername(),user.getPassword());
        canteenAdminMapper.insertCanteenAdmin(canteenAdmin);
        int isSuccess = userMapper.deleteUserById(Integer.parseInt(id));
        sqlSession.commit();
        sqlSession.close();
        if (isSuccess == 1)
            return FrontEndUtils.objectToBody("委任成功","0",null);
        else
            return FrontEndUtils.objectToBody("委任失败","1",null);

    }

    public String deleteDishCommentById(String id){
        DishCommentMapper dishMapper = sqlSession.getMapper(DishCommentMapper.class);
        int isSuccess = dishMapper.deletedishCommentById(Integer.parseInt(id));
        sqlSession.commit();
        sqlSession.close();
        if (isSuccess == 1)
            return FrontEndUtils.objectToBody("删除成功","0",null);
        else
            return FrontEndUtils.objectToBody("删除失败","1",null);
    }


}
