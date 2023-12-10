package com.restaurant.restaurant.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VoteMapper {
    List<VoteMapper> selectAll();
    VoteMapper selectById(@Param("id") int id);
}
