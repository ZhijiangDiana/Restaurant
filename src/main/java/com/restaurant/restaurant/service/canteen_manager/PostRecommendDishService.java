package com.restaurant.restaurant.service.canteen_manager;

import com.restaurant.restaurant.mapper.RecommendDishMapper;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PostRecommendDishService {
    SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
    RecommendDishMapper recommendDishMapper = sqlSession.getMapper(RecommendDishMapper.class);



    public boolean publishRecommendations(int canteenId, List<Integer> dishIds) {
        try {
            recommendDishMapper.insertRecommendations(canteenId, dishIds);
            sqlSession.commit();
            sqlSession.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
