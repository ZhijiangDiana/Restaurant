package com.restaurant.restaurant.service.canteen_manager;

import com.restaurant.restaurant.mapper.RecommendDishMapper;
import com.restaurant.restaurant.utils.FrontEndUtils;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PostRecommendDishService {
    public String publishRecommendations(int canteenId, String title, String body) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        RecommendDishMapper recommendDishMapper = sqlSession.getMapper(RecommendDishMapper.class);
        try {
            int isSuccess = recommendDishMapper.insertRecommendations(canteenId, title, body);
            sqlSession.commit();
            if(isSuccess == 1){
                return "插入成功";
            }else
                return "插入失败";
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            sqlSession.close();
            return FrontEndUtils.objectToBody("系统繁忙", "1", null);
        }
    }
}
