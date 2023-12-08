package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.pojo.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {
    List<Comment> selectAll();
    Comment selectById(@Param("id") int id);
    void insertComment(Comment comment);
}
