package com.restaurant.restaurant.service.canteen;

import com.restaurant.restaurant.mapper.AnnouncementMapper;
import com.restaurant.restaurant.mapper.DishMapper;
import com.restaurant.restaurant.pojo.entity.Announcement;
import com.restaurant.restaurant.pojo.entity.Dish;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class GetCanteenDishService {
    // 已改try-with-resources
    public List<Dish> getCanteenDish(int canteenId) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        DishMapper dishMapper = sqlSession.getMapper(DishMapper.class);

        List<Dish> res = null;
        try (sqlSession) {
            res = dishMapper.selectByCanteenIdWithFile(canteenId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
}
