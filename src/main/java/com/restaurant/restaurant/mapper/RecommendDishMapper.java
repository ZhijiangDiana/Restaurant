package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.pojo.entity.RecommendDish;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecommendDishMapper {
    void insertRecommendations(@Param("canteenId") int canteenId, @Param("dishIds") List<Integer> dishIds);
}
