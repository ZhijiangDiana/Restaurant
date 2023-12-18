package com.restaurant.restaurant.service.canteen_manager;

import com.restaurant.restaurant.mapper.DishMapper;
import com.restaurant.restaurant.pojo.entity.Dish;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

public class UploadDishService {
    /*
        插入dish
     */
    // 已改try-with-resources
    public boolean uploadDish(Dish dish) {
        boolean isSuccess;
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        DishMapper dishMapper = sqlSession.getMapper(DishMapper.class);

        try (sqlSession) {
            dishMapper.insertDish(dish);
            sqlSession.commit();
            isSuccess = true;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
            isSuccess = false;
        }
        return isSuccess;
    }
}
