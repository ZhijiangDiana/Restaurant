package com.restaurant.restaurant.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CanteenMapper {
    List<CanteenMapper> selectAll();
    CanteenMapper selectById(@Param("id") int id);
}
