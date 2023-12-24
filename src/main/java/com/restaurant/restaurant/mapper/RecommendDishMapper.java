package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.pojo.entity.RecommendDish;
import com.restaurant.restaurant.pojo.entity.Dish;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecommendDishMapper {
    List<RecommendDish> selectAll();
    int insertRecommendations(@Param("canteenId") int canteenId, @Param("title") String title, @Param("body") String body);
    Dish selectById(@Param("id") int id);

    List<String> selectBody();
}
