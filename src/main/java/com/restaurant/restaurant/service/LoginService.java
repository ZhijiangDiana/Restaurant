package com.restaurant.restaurant.service;

import com.restaurant.restaurant.mapper.AdminMapper;
import com.restaurant.restaurant.mapper.CanteenAdminMapper;
import com.restaurant.restaurant.mapper.UserMapper;
import com.restaurant.restaurant.pojo.LevelInfo;
import com.restaurant.restaurant.pojo.entity.Admin;
import com.restaurant.restaurant.pojo.entity.CanteenAdmin;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.utils.Constants;
import com.restaurant.restaurant.utils.ExpLevelCaculate;
import com.restaurant.restaurant.utils.FrontEndUtils;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import jdk.jfr.Frequency;
import org.apache.ibatis.session.SqlSession;

public class LoginService {
    SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
    // TODO：逻辑有待商榷好吧
    public String loginCheck(String id,String password,String verification,String sessionVerification){
        if(!sessionVerification.equals(verification)){
            return FrontEndUtils.objectToBody("验证码错误","1",null);
        }
        else {
            if (id.length() != 4 && id.length() != 6 && id.length() != 10){
                return FrontEndUtils.objectToBody("输入账号有误,请重新输入","1",null);
            }
            else {
                // 总管理员
                if (id.length() == 4){
                    AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
                    Admin admin = mapper.selectById(Integer.parseInt(id));
                    if (admin.getPassword().equals(password)) {
                        return FrontEndUtils.objectToBody("管理员先生您好","0",Constants.ADMIN_VERIFIED);
                    }
                    else {
                        return FrontEndUtils.objectToBody("密码错误","1",null);
                    }
                }

                // 食堂管理员
                else if (id.length() == 6){
                    CanteenAdminMapper mapper = sqlSession.getMapper(CanteenAdminMapper.class);
                    CanteenAdmin canteenAdmin = mapper.selectById(Integer.parseInt(id));
                    if(canteenAdmin.getPassword().equals(password)){
                        return FrontEndUtils.objectToBody("食堂管理员你好","0",null);
                    }
                    else {
                        return FrontEndUtils.objectToBody("密码错误","1",null);
                    }
                }

                // 师生
                else {
                    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
                    User user = mapper.selectById(Integer.parseInt(id));
                    if(user.getPassword().equals(password)){
                        Integer experience = user.getExperience();
                        // obj设为了到servlet存session
                        return FrontEndUtils.objectToBody(user.getUsername() + "你好","0",user);
                    }
                    else {
                        return FrontEndUtils.objectToBody("密码错误","1",null);
                    }
                }
            }
        }
    }

    public User getUserById(String id,String password,String verification,String sessionVerification){
        if (sessionVerification.equals(verification)){
            if(id.length() == 10){
                UserMapper mapper = sqlSession.getMapper(UserMapper.class);
                User user = mapper.selectById(Integer.parseInt(id));
                if (user.getPassword().equals(password)){
                    return user;
                }
                else
                    return null;
            }
            else
                return null;
        }
        else
            return null;
    }

    public CanteenAdmin getCanteenAdminById(String id,String password,String verification,String sessionVerification){
        if (sessionVerification.equals(verification)){
            if(id.length() == 6){
                CanteenAdminMapper mapper = sqlSession.getMapper(CanteenAdminMapper.class);
                CanteenAdmin canteenAdmin = mapper.selectById(Integer.parseInt(id));
                if (canteenAdmin.getPassword().equals(password)){
                    return canteenAdmin;
                }
                else
                    return null;
            }
            else
                return null;
        }
        else
            return null;
    }
}
