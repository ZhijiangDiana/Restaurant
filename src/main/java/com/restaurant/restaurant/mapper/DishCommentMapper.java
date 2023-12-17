package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.pojo.entity.DishComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DishCommentMapper {
    List<DishComment> selectAll();
    DishComment selectById(@Param("id") int id);//
    void insertComment(DishComment comment);//添加评论

    int deletedishCommentById(@Param("id") int id);
}
