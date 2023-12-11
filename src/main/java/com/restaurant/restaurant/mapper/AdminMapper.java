package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.pojo.entity.Admin;
import com.restaurant.restaurant.pojo.entity.Comment;
import com.restaurant.restaurant.pojo.entity.DishComment;
import com.restaurant.restaurant.pojo.entity.Reply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    void insertAdmin(Admin admin);

    Admin selectById(@Param("id") int id);

    List<Comment> selectCommentById(@Param("id") int id);

    List<DishComment> selectDishCommentById(@Param("id") int id);

    List<Reply> selectReplyById(@Param("id") int id);
}
