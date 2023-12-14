package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.pojo.entity.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper {
    List<MessageMapper> selectAll();
    MessageMapper selectById(@Param("id") int id);

    //List<Message>
}
