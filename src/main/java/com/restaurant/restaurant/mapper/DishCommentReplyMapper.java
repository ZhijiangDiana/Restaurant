package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.pojo.DishCommentReplyShow;
import com.restaurant.restaurant.pojo.entity.DishCommentReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DishCommentReplyMapper {
    List<DishCommentReplyShow> selectByDishCommentId(@Param("dishCommentId") int dishCommentId);
    void insertDishCommentReply(DishCommentReply dishCommentReply);
}
