package com.restaurant.restaurant.service.vote;

import com.restaurant.restaurant.mapper.CommentMapper;
import com.restaurant.restaurant.mapper.DishMapper;
import com.restaurant.restaurant.mapper.RecommendDishMapper;
import com.restaurant.restaurant.mapper.VoteMapper;
import com.restaurant.restaurant.pojo.CommentShow;
import com.restaurant.restaurant.pojo.entity.Comment;
import com.restaurant.restaurant.pojo.entity.Dish;
import com.restaurant.restaurant.pojo.entity.RecommendDish;
import com.restaurant.restaurant.pojo.entity.Vote;
import com.restaurant.restaurant.utils.FrontEndUtils;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class CombinedInfoService {
    public String showLatestVote(){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try (sqlSession){
            VoteMapper voteMapper = sqlSession.getMapper(VoteMapper.class);
            List<Vote> voteList = voteMapper.selectAll();
            // 拿到最新发布的
            Vote vote = voteList.get(voteList.size() - 1);
            return FrontEndUtils.objectToBody(null,"0",vote);
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.close();
            return FrontEndUtils.objectToBody("系统繁忙","1",null);
        }
    }

    public String showDiscountDish(){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try (sqlSession){
            DishMapper dishMapper = sqlSession.getMapper(DishMapper.class);
            List<Dish> dishList = dishMapper.selectAllDiscountDishWithFile();
            return FrontEndUtils.objectToBody(null,"0", dishList);
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.close();
            return FrontEndUtils.objectToBody("系统繁忙","1",null);
        }
    }

    public String showHotComment(){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try (sqlSession){
            CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
            List<CommentShow> commentList = commentMapper.selectAllByLikes();
            return FrontEndUtils.objectToBody(null,"0",commentList);
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.close();
            return FrontEndUtils.objectToBody("系统繁忙","1",null);
        }
    }

    public String showRecommendDish(){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try (sqlSession){
            RecommendDishMapper recommendDishMapper = sqlSession.getMapper(RecommendDishMapper.class);
            List<RecommendDish> recommendDishList = recommendDishMapper.selectAll();
            return FrontEndUtils.objectToBody(null,"0",recommendDishList);
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.close();
            return FrontEndUtils.objectToBody("系统繁忙","1",null);
        }
    }

    public String showHotDish(){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        try (sqlSession){
            DishMapper dishMapper = sqlSession.getMapper(DishMapper.class);
            List<Dish> dishList = dishMapper.selectDishOrderByScore();
            return FrontEndUtils.objectToBody(null,"0",dishList);
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.close();
            return FrontEndUtils.objectToBody("系统繁忙","1",null);
        }
    }
}
