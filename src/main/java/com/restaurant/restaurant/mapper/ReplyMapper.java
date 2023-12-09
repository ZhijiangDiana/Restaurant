package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.pojo.entity.Reply;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

public interface ReplyMapper {
    List<Reply> selectByCommentId(@Param("CommentId") int CommentId);//根据评论的id进行回复
}
