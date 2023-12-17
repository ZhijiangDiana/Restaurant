package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.pojo.entity.Comment;
import com.restaurant.restaurant.pojo.entity.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper {
    List<Message> selectAll();
    List<Message> selectById(@Param("id") int id);

    void insertMessage(Message message);

    //List<Message>
}
