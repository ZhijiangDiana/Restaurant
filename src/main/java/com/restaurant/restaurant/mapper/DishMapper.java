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
    List<Dish> selectBySeriesWithFile(@Param("series") int series);
    List<Dish> selectByPrice(@Param("minPrice") int minPrice,
                             @Param("maxPrice") int maxPrice); // 查找价格介于两者之间的菜品
    List<Dish> selectByPriceWithFile(@Param("minPrice") int minPrice,
                             @Param("maxPrice") int maxPrice);
    List<Dish> selectByCanteen(@Param("canteen") String canteen);//查找这个菜所属的餐厅
    List<Dish> selectByCanteenWithFile(@Param("canteen") String canteen);
    List<Dish> selectByScore(@Param("score") double score); // 查找比score高的菜品
    List<Dish> selectByScoreWithFile(@Param("score") double score);
    void insertDish(Dish dish); // 插入dish记录
    void updateDishScore(@Param("dishId") int dishId,
                         @Param("dishNewScore") double dishNewScore,
                         @Param("newWeightSum") double newWeightSum); // 修改dish评分
    List<Dish> selectByCanteenId(@Param("canteenId") int canteenId);
    List<Dish> selectByCanteenIdWithFile(@Param("canteenId") int canteenId);
    void deleteDishById(@Param("dish_id") int id);//根据id删除菜品
    void updateDish(Dish dish); //更新菜品
}
