package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.pojo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> selectAll(); // 写一个selectAll用于测试

    User selectById(@Param("id") int id); // 使用参数要写注解，这样有代码提示
}
