package com.restaurant.restaurant.service.canteen_manager;

import com.restaurant.restaurant.mapper.DishMapper;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

public class Delete_Dish {

    public Delete_Dish(int dish_id) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        DishMapper dishMapper = sqlSession.getMapper(DishMapper.class);

        dishMapper.deleteDishById(dish_id);

        sqlSession.commit();
        sqlSession.close();
    }
}
