package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.pojo.entity.Canteen;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CanteenMapper {
    List<Canteen> selectAll();
    Canteen selectById(@Param("id") int id);
    void increaseReportById(@Param("id") int id);

    int deleteCanteenById(@Param("id") int id);

    int insertCanteen(Canteen canteen);

    int updateCanteen(Canteen canteen);
}
