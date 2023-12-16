package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.pojo.entity.Vote;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VoteMapper {
    List<Vote> selectAll();
    Vote selectById(@Param("id") int id);
    List<Vote> selectFinishedByCanteenId(@Param("canteenId") int id);
    List<Vote> selectRunning();
    void insertVote(Vote vote);
    void updateResult(Vote vote);
}
