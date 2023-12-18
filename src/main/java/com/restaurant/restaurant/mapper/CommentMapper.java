package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.pojo.entity.Canteen;
import com.restaurant.restaurant.pojo.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {
    List<Comment> selectAll();

    List<Comment> selectAllByLikes();

    List<Comment> selectAllDesc();

    List<Comment> selectAllAsc();
    Comment selectById(@Param("id") int id);
    void insertComment(Comment comment);

    void updateLikeById(@Param("likes") int likes,@Param("id") int id);

    int updateComment(Comment comment);

    int deleteCommentById(@Param("id") int id);
}
