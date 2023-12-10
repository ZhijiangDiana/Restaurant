package com.restaurant.restaurant.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportMapper {
    List<ReportMapper> selectAll();
    ReportMapper selectById(@Param("id") int id);
}
