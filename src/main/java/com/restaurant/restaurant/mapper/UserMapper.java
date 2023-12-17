package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.pojo.entity.Comment;
import com.restaurant.restaurant.pojo.entity.DishComment;
import com.restaurant.restaurant.pojo.entity.Reply;
import com.restaurant.restaurant.pojo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> selectAll(); // 写一个selectAll用于测试

    User selectById(@Param("id") int id); // 使用参数要写注解，这样有代码提示

    List<Comment> selectCommentById(@Param("id") int id);

    List<DishComment> selectDishCommentById(@Param("id") int id);

    List<Reply> selectReplyById(@Param("id") int id);

    void insertUser(User user);

    void updateExpById(@Param("experience") int experience,@Param("id") int id);

    void updateIllegalityById(@Param("illegality") int illegality, @Param("id") int id);

    void increaseIllegalityById(@Param("illegality") int illegality,
                                @Param("id") int id);

    int updateUser(User user);

    int deleteUserById(@Param("id") int id);
}
