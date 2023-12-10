package com.restaurant.restaurant.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CanteenAdminMapper {
    List<CanteenAdminMapper> selectAll();
    CanteenAdminMapper selectById(@Param("id") int id);
}
