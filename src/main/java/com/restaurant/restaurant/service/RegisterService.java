package com.restaurant.restaurant.service;

import com.restaurant.restaurant.mapper.CanteenAdminMapper;
import com.restaurant.restaurant.mapper.UserMapper;
import com.restaurant.restaurant.pojo.entity.CanteenAdmin;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.utils.FrontEndUtils;
import com.restaurant.restaurant.utils.SensitiveCommentFilter;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

public class RegisterService {
    SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    CanteenAdminMapper canteenAdminMapper = sqlSession.getMapper(CanteenAdminMapper.class);

    /**
     * 注册
     *
     * @return 返回结果
     */
    public String registerUser(String id, String name, String password) {
        //userMapper.regi
        User user = userMapper.selectById(Integer.parseInt(id));
        CanteenAdmin canteenAdmin = canteenAdminMapper.selectById(Integer.parseInt(id));
        // 姓名敏感
        if (SensitiveCommentFilter.commentFilter(name)) {
            return FrontEndUtils.objectToBody("姓名涉及敏感信息", "1", null);
        }
        // 不是正确格式
        else if (id.length() != 6 && id.length() != 10) {
            return FrontEndUtils.objectToBody("学号/工号输入有误", "1", null);
        }
        // 格式没问题
        else {
            // 按照id找不到说明没注册过 且符合格式问题 直接添加
            if (user == null && canteenAdmin == null) {
                User newUser = new User(Integer.parseInt(id), name, password, 0, false);
                userMapper.insertUser(newUser);
                sqlSession.commit();
                return FrontEndUtils.objectToBody("注册成功", "0", null);
            } else
                return FrontEndUtils.objectToBody("该账号已注册过", "1", null);
        }
    }
}
