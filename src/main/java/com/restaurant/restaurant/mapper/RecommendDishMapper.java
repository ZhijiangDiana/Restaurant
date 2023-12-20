package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.pojo.entity.RecommendDish;

import java.util.List;

public interface RecommendDishMapper {
    List<RecommendDish> selectAll();
}
