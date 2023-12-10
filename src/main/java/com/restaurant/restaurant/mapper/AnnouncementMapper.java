package com.restaurant.restaurant.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnnouncementMapper {
    List<AnnouncementMapper> selectAll();
    AnnouncementMapper selectById(@Param("id") int id);
}
