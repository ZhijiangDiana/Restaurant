package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.pojo.entity.RecommendDish;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecommendDishMapper {
    String selectByCanteenId(@Param("canteenId") int canteenId);
}
