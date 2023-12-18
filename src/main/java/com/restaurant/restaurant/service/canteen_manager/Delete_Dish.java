package com.restaurant.restaurant.service.canteen_manager;

import com.restaurant.restaurant.mapper.DishMapper;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

public class Delete_Dish {
    // 已改try-with-resources
    public boolean deleteDish(int dish_id) {
        boolean isSuccess;
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        DishMapper dishMapper = sqlSession.getMapper(DishMapper.class);

        try {
            dishMapper.deleteDishById(dish_id);
            sqlSession.commit();
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            isSuccess = false;
        }
        return isSuccess;
    }
}
