package com.restaurant.restaurant.service.dish_comment;

import com.restaurant.restaurant.mapper.DishCommentMapper;
import com.restaurant.restaurant.mapper.DishCommentReplyMapper;
import com.restaurant.restaurant.pojo.entity.DishComment;
import com.restaurant.restaurant.pojo.entity.DishCommentReply;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class GetDishCommentService {
    // 已改try-with-resources
    public List<DishComment> getDishCommentByCanteen(int canteenId) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        DishCommentMapper dishCommentMapper = sqlSession.getMapper(DishCommentMapper.class);

        List<DishComment> res = null;
        try {
            res = dishCommentMapper.selectByCanteenIdWithFile(canteenId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return res;
    }

    public List<DishComment> getDishCommentByUser(int userId) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        DishCommentMapper dishCommentMapper = sqlSession.getMapper(DishCommentMapper.class);

        List<DishComment> res = null;
        try {
            res = dishCommentMapper.selectByUserIdWithFile(userId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return res;
    }

    public List<DishComment> getDishCommentByDish(int dishId) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        DishCommentMapper dishCommentMapper = sqlSession.getMapper(DishCommentMapper.class);

        List<DishComment> res = null;
        try {
            res = dishCommentMapper.selectByDishIdWithFile(dishId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return res;
    }
}
