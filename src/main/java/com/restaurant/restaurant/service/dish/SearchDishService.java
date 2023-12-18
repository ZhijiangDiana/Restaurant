package com.restaurant.restaurant.service.dish;

import com.restaurant.restaurant.mapper.DishMapper;
import com.restaurant.restaurant.pojo.entity.Dish;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class SearchDishService {
    // 已改try-with-resources
    public List<Dish> searchDishBySeries(int series) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        DishMapper dishMapper = sqlSession.getMapper(DishMapper.class);

        List<Dish> res = null;
        try (sqlSession) {
            res = dishMapper.selectBySeriesWithFile(series);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public List<Dish> searchDishByPrice(int minPrice, int maxPrice) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        DishMapper dishMapper = sqlSession.getMapper(DishMapper.class);

        List<Dish> res = null;
        try (sqlSession) {
            res = dishMapper.selectByPriceWithFile(minPrice, maxPrice);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    public List<Dish> searchDishByCanteen(String canteenName) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        DishMapper dishMapper = sqlSession.getMapper(DishMapper.class);

        List<Dish> res = null;
        try (sqlSession) {
            res = dishMapper.selectByCanteenWithFile(canteenName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    public List<Dish> searchDishByScore(double score) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        DishMapper dishMapper = sqlSession.getMapper(DishMapper.class);

        List<Dish> res = null;
        try (sqlSession) {
            res = dishMapper.selectByScoreWithFile(score);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
}
