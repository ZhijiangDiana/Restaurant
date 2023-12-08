package com.restaurant.restaurant.service;

import com.restaurant.restaurant.pojo.LevelInfo;
import com.restaurant.restaurant.utils.Constants;
import com.restaurant.restaurant.utils.ExpLevelCaculate;

public class LoginService {
    // Sqlsession sqlsession = MybatisUtils.getSqlsession();
    // UserMapper usermapper = sqlsession.getMapper(UserMapper.class);
    // restAdminMapper usermapper = sqlsession.getMapper(UserMapper.class);
    // AdminMapper usermapper = sqlsession.getMapper(UserMapper.class);
    public int loginCheck(String id,String password,String verification,String sessionVerification){
        if(!sessionVerification.equals(verification))
            return Constants.LOGIN_INVALID;
        // UserMapper usermapper = sqlsession.getMapper(UserMapper.class);
        // User user = usermapper.selectById(id)  对应三个方法分别查师生、餐厅管理员和总管理员
        //if(password.equals(user.getPassword()))
            //return Constants.USER_VERIFIED;
        //if(password.equals(restAdmin.getPassword()))
        //return Constants.RESTAURANT_ADMIN_VERIFIED;
        //if(password.equals(Admin.getPassword()))
        //return Constants.ADMIN_VERIFIED;
        return Constants.USER_VERIFIED;
    }

    public LevelInfo getExp(String id,String password,int isVerified){
        int exp = 1;
        if (isVerified == Constants.USER_VERIFIED){
            // exp = usermapper.getExp(String id,String password);
        }
        else if (isVerified == Constants.RESTAURANT_ADMIN_VERIFIED){
            // exp = restAdminmapper.getExp(String id,String password);
        }
        else {
            // exp = adminMapper.getExp(String id,String password);
        }
        // exp算法
        return ExpLevelCaculate.caculateLevel(exp);
    }
}
