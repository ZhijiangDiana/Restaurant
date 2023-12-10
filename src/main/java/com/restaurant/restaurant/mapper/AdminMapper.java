package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.pojo.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    List<AdminMapper> selectAll();
    AdminMapper selectById(@Param("id") int id);
}
