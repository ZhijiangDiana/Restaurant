package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.pojo.entity.DishComment;
import com.restaurant.restaurant.pojo.entity.Reply;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

public interface ReplyMapper {
    List<Reply> selectByCommentId(@Param("CommentId") int CommentId);//根据评论的id进行回复

    void insertUserReply(Reply reply);

    void insertAdminReply(Reply reply);

    int updateReply(Reply reply);

    int deleteReplyById(@Param("id") int id);
}
