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
                             @Param("maxPrice") int maxPrice); // 查找价格介于两者之间的菜品
    List<Dish> selectByCanteen(@Param("canteen") String canteen);
    List<Dish> selectByScore(@Param("score") double score); // 查找比score高的菜品
    void insertDish(Dish dish); // 插入dish记录
    void updateDishScore(@Param("dishId") int dishId,
                         @Param("dishNewScore") double dishNewScore,
                         @Param("newWeightSum") double newWeightSum); // 修改dish评分

}