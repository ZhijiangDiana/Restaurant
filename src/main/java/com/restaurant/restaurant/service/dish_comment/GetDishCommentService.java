package com.restaurant.restaurant.service.dish_comment;

import com.restaurant.restaurant.mapper.DishCommentMapper;
import com.restaurant.restaurant.pojo.DishCommentShow;
import com.restaurant.restaurant.pojo.entity.DishComment;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import jakarta.servlet.ServletContext;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class GetDishCommentService {
    // 已改try-with-resources
    public List<DishCommentShow> getDishCommentByCanteen(int canteenId, ServletContext context) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        DishCommentMapper dishCommentMapper = sqlSession.getMapper(DishCommentMapper.class);
        Map<Integer, Map<Integer, DishComment>> dishCommentNotif =
                (Map<Integer, Map<Integer, DishComment>>) context.getAttribute("dishCommentNotif");

        List<DishCommentShow> res = null;
        try {
            res = dishCommentMapper.selectByCanteenIdWithFile(canteenId);
            Map<Integer, DishComment> canteenDishCommentMap = dishCommentNotif.get(canteenId);
            canteenDishCommentMap.clear();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return res;
    }

    public List<DishCommentShow> getDishCommentByUser(int userId) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        DishCommentMapper dishCommentMapper = sqlSession.getMapper(DishCommentMapper.class);

        List<DishCommentShow> res = null;
        try {
            res = dishCommentMapper.selectByUserIdWithFile(userId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return res;
    }

    public List<DishCommentShow> getDishCommentByDish(int dishId) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        DishCommentMapper dishCommentMapper = sqlSession.getMapper(DishCommentMapper.class);

        List<DishCommentShow> res = null;
        try {
            res = dishCommentMapper.selectByDishIdWithFile(dishId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return res;
    }
    public DishComment showDishCommentByID(int dishId) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        DishCommentMapper dishCommentMapper = sqlSession.getMapper(DishCommentMapper.class);

        DishComment res = null;
        try {
            res = dishCommentMapper.selectByIdWithFile(dishId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return res;
    }
    public DishComment showCanteenById(int id){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        DishCommentMapper dishCommentMapper = sqlSession.getMapper(DishCommentMapper.class);

        DishComment res = null;
        try {
            res = dishCommentMapper.selectById(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return res;
    }
}
