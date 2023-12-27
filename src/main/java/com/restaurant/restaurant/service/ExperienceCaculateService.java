package com.restaurant.restaurant.service;

import com.restaurant.restaurant.mapper.UserMapper;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.utils.Constants;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

public class ExperienceCaculateService {
    /**
     * 计算各种行为加的经验
     * @param action
     * @return
     */
    public Integer caculateExperience(Integer id, int action){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        try (sqlSession) {
            User user = userMapper.selectById(id);
            Integer experience = user.getExperience();
            if(Constants.LOGIN_EXP == action)
                experience += 10;
            else if (Constants.CANTEENCOMMENT_EXP == action)
                experience += 20;
            else if (Constants.COMMUNITY_EXP == action)
                experience += 20;
            else if (Constants.REPLY_EXP == action)
                experience += 15;
            else if (Constants.COMPLAINT_EXP == action)
                experience += 30;
            else
                experience += 30;
            userMapper.updateExpById(experience,id);
            sqlSession.commit();
            return experience;
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            return null;
        }
    }
}
