package com.restaurant.restaurant.service.dish_comment;

import com.restaurant.restaurant.mapper.DishCommentReplyMapper;
import com.restaurant.restaurant.pojo.entity.DishCommentReply;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class GetDishCommentReplyService {
    // 已改try-with-resources
    public List<DishCommentReply> getDishCommentReplyById(int dishCommentId) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        DishCommentReplyMapper dishCommentReplyMapper = sqlSession.getMapper(DishCommentReplyMapper.class);

        List<DishCommentReply> res = null;
        try {
            res = dishCommentReplyMapper.selectByDishCommentId(dishCommentId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return res;
    }
}
