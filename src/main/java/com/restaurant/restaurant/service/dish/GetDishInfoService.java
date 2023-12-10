package com.restaurant.restaurant.service.dish;

import com.restaurant.restaurant.mapper.DishMapper;
import com.restaurant.restaurant.pojo.entity.Dish;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

public class GetDishInfoService {
    public Dish getDishInfo(int dishId) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        DishMapper dishMapper = sqlSession.getMapper(DishMapper.class);

        Dish res = dishMapper.selectByIdWithFile(dishId);
        sqlSession.close();

        return res;
    }
}
