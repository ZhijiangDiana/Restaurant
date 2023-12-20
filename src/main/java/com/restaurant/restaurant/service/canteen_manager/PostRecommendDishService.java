package com.restaurant.restaurant.service.canteen_manager;

import com.restaurant.restaurant.mapper.RecommendDishMapper;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

public class PostRecommendDishService {
    public String selectByCanteenId(int canteenId){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        RecommendDishMapper recommendDishMapper = sqlSession.getMapper(RecommendDishMapper.class);

        String body = recommendDishMapper.selectByCanteenId(canteenId);

        sqlSession.commit();
        sqlSession.close();

        return body;

    }
}
