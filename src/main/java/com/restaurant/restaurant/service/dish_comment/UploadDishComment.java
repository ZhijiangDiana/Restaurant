package com.restaurant.restaurant.service.dish_comment;

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
        boolean isSuccess;
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        DishCommentMapper dishCommentMapper = sqlSession.getMapper(DishCommentMapper.class);
        try {
            dishCommentMapper.insertComment(dishComment);
            sqlSession.commit();
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            isSuccess = false;
        } finally {
            sqlSession.close();
        }
    }
}
