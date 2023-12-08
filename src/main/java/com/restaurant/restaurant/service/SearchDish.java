package com.restaurant.restaurant.service;

import com.restaurant.restaurant.mapper.DishMapper;
import com.restaurant.restaurant.pojo.entity.Dish;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class SearchDish {
    // TODO: 2023/12/9 没写Controller层
    public List<Dish> searchDishBySeries(int series) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        DishMapper dishMapper = sqlSession.getMapper(DishMapper.class);

        List<Dish> res = dishMapper.selectBySeries(series);
        sqlSession.close();

        return res;
    }

    public List<Dish> searchDishByPrice(int minPrice, int maxPrice) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        DishMapper dishMapper = sqlSession.getMapper(DishMapper.class);

        List<Dish> res = dishMapper.selectByPrice(minPrice, maxPrice);
        sqlSession.close();

        return res;
    }

    public List<Dish> searchDishByCanteen(String canteenName) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        DishMapper dishMapper = sqlSession.getMapper(DishMapper.class);

        List<Dish> res = dishMapper.selectByCanteen(canteenName);
        sqlSession.close();

        return res;
    }

    public List<Dish> searchDishByScore(double score) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        DishMapper dishMapper = sqlSession.getMapper(DishMapper.class);

        List<Dish> res = dishMapper.selectByScore(score);
        sqlSession.close();

        return res;
    }
}
