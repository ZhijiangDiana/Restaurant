package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.pojo.entity.Comment;
import com.restaurant.restaurant.pojo.ReplyMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {
    List<Comment> selectAll();

    List<Comment> selectAllByLikes();

    List<Comment> selectAllDesc();

    List<Comment> selectAllAsc();
    Comment selectById(@Param("id") int id);
    Comment selectByIdWithFile(@Param("id") int id);
    void insertComment(Comment comment);

    void updateLikeById(@Param("likes") int likes,@Param("id") int id);

    int updateComment(Comment comment);

    int deleteCommentById(@Param("id") int id);

    List<Comment>selectCommentOrderByLikes();
    List<ReplyMessage> selectCommentIdByUserId(@Param("id") int id);//查询别人回复我的评论，相当于b站的回复@我的评论

    Integer selectCommentPublisher(@Param("commentId") int commentId);
}
