package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.pojo.entity.Vote;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VoteMapper {
    List<Vote> selectAll();
    VoteMapper selectById(@Param("id") int id);

    void updateVote(Vote vote);
}
