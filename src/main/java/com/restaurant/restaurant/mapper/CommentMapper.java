package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.pojo.entity.Canteen;
import com.restaurant.restaurant.pojo.entity.Comment;
import com.restaurant.restaurant.pojo.entity.ReplyMessage;
import org.apache.ibatis.annotations.Param;
import com.restaurant.restaurant.pojo.CommentShow;

import java.util.List;

public interface CommentMapper {
    List<Comment> selectAll();

    List<CommentShow> selectAllByLikes();

    List<CommentShow> selectAllDesc();

    List<CommentShow> selectAllAsc();
    Comment selectById(@Param("id") int id);
    Comment selectByIdWithFile(@Param("id") int id);
    void insertComment(Comment comment);

    void updateLikeById(@Param("likes") int likes,@Param("id") int id);

    int updateComment(Comment comment);

    int deleteCommentById(@Param("id") int id);

    List<CommentShow>selectCommentOrderByLikes();
    List<ReplyMessage> selectCommentIdByUserId(@Param("id") int id);//查询别人回复我的评论，相当于b站的回复@我的评论

    List<CommentShow> selectDetailedInfo();
}
