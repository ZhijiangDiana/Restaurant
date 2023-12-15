package com.restaurant.restaurant.service.canteen;

import com.restaurant.restaurant.mapper.AnnouncementMapper;
import com.restaurant.restaurant.mapper.DishMapper;
import com.restaurant.restaurant.pojo.entity.Announcement;
import com.restaurant.restaurant.pojo.entity.Dish;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class GetCanteenDishService {
    public List<Dish> getCanteenDish(int canteenId) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        DishMapper dishMapper = sqlSession.getMapper(DishMapper.class);

        List<Dish> res = dishMapper.selectByCanteenIdWithFile(canteenId);
        sqlSession.close();

        return res;
    }
}
