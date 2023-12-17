package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.pojo.entity.DishCommentReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DishCommentReplyMapper {
    List<DishCommentReply> selectByDishCommentId(@Param("dishCommentId") int dishCommentId);
    void insertDishCommentReply(DishCommentReply dishCommentReply);
}
