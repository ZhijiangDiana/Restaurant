package com.restaurant.restaurant.service.canteen_manager;

import com.restaurant.restaurant.mapper.DishMapper;
import com.restaurant.restaurant.pojo.entity.Dish;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

public class UploadDish {
    /*
        插入dish
     */

    public UploadDish(Dish dish) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        DishMapper dishMapper = sqlSession.getMapper(DishMapper.class);
        dishMapper.insertDish(dish);

        sqlSession.commit();
        sqlSession.close();
    }
}
