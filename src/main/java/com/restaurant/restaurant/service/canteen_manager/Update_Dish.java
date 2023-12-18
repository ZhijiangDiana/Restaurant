package com.restaurant.restaurant.service.canteen_manager;

import com.restaurant.restaurant.mapper.DishMapper;
import com.restaurant.restaurant.pojo.entity.Dish;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

public class Update_Dish {
    // 已改try-with-resources
    public boolean updateDish(Dish dish) {
        boolean isSuccess;
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        DishMapper dishMapper = sqlSession.getMapper(DishMapper.class);

        try (sqlSession) {
            dishMapper.updateDish(dish);
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
