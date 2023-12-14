package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.pojo.entity.Report;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportMapper {
    List<Report> selectAll();
    Report selectById(@Param("id") int id);
    void insertReport(Report report);
}
