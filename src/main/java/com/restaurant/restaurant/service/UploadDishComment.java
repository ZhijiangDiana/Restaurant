package com.restaurant.restaurant.service;

import com.restaurant.restaurant.mapper.DishCommentMapper;
import com.restaurant.restaurant.pojo.entity.DishComment;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

public class UploadDishComment {
    /**
     * 插入评论
     * @param dishComment 需要保证userId, score, dishId, title, body要有
     */
    public void commentDish(DishComment dishComment) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        DishCommentMapper dishCommentMapper = sqlSession.getMapper(DishCommentMapper.class);
        dishCommentMapper.insertComment(dishComment);

        sqlSession.commit();

        sqlSession.close();
    }
}
