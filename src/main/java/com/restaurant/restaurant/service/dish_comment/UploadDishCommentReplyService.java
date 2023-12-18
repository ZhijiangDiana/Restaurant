package com.restaurant.restaurant.service.dish_comment;

import com.restaurant.restaurant.mapper.DishCommentReplyMapper;
import com.restaurant.restaurant.pojo.entity.DishCommentReply;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

public class UploadDishCommentReplyService {
    // 已改try-with-resources
    public boolean replyComment(DishCommentReply dishCommentReply) {
        boolean isSuccess;
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        DishCommentReplyMapper dishCommentReplyMapper = sqlSession.getMapper(DishCommentReplyMapper.class);

        try {
            dishCommentReplyMapper.insertDishCommentReply(dishCommentReply);
            sqlSession.commit();
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            isSuccess = false;
        } finally {
            sqlSession.close();
        }
        return isSuccess;
    }
}
