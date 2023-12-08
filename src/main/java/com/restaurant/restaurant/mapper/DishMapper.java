package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.pojo.entity.Dish;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 要求：不指名需要文件时，不能使用SELECT *，必须把文件排除在外
 */
public interface DishMapper {
    List<Dish> selectAll();
    Dish selectById(@Param("id") int id); // 按id查询不包含文件的记录
    Dish selectByIdWithFile(@Param("id") int id); // 按id查询包含文件的记录
    List<Dish> selectBySeries(@Param("series") int series);
    List<Dish> selectByPrice(@Param("minPrice") int minPrice,
                             @Param("maxPrice") int maxPrice);
    List<Dish> selectByCanteen(@Param("canteen") String canteen);
    List<Dish> selectByScore(@Param("score") double score);
}
