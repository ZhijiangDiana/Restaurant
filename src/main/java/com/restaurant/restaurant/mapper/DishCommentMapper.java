package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.pojo.DishCommentShow;
import com.restaurant.restaurant.pojo.entity.DishComment;
import com.restaurant.restaurant.pojo.entity.DishCommentReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DishCommentMapper {
    List<DishComment> selectAll();
    List<DishComment> selectAllWithFile();
    DishComment selectById(@Param("id") int id);//
    void insertComment(DishComment comment);//添加评论
    List<DishCommentShow> selectByCanteenIdWithFile(@Param("canteenId") int canteenId);
    List<DishCommentShow> selectByUserIdWithFile(@Param("userId") int userId);
    List<DishCommentShow> selectByDishIdWithFile(@Param("dishId") int dishId);

    int deletedishCommentById(@Param("id") int id);

    int updateDishComment(DishComment dishComment);
}
