package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.pojo.entity.Announcement;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnnouncementMapper {
    List<Announcement> selectAll();
    List<Announcement> selectByCanteenId(@Param("canteenId") int canteenId);
}
