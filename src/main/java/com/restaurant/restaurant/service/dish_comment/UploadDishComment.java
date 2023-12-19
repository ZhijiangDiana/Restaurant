package com.restaurant.restaurant.service.dish_comment;

import com.restaurant.restaurant.mapper.CanteenMapper;
import com.restaurant.restaurant.mapper.DishCommentMapper;
import com.restaurant.restaurant.mapper.DishMapper;
import com.restaurant.restaurant.pojo.entity.Canteen;
import com.restaurant.restaurant.pojo.entity.Dish;
import com.restaurant.restaurant.pojo.entity.DishComment;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.utils.Pair;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import jakarta.servlet.ServletContext;
import org.apache.ibatis.session.SqlSession;

import java.util.Map;

public class UploadDishComment {
    // 已改try-with-resources
    /**
     * 插入评论
     * @param dishComment 需要保证userId, score, dishId, title, body要有
     */
    public boolean commentDish(DishComment dishComment, User user, int userCommentsPerOnline, ServletContext context) {
        int dishId = dishComment.getDishId();
        double userComment = dishComment.getScore();
        boolean isSuccess;

        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        DishMapper dishMapper = sqlSession.getMapper(DishMapper.class);
        DishCommentMapper dishCommentMapper = sqlSession.getMapper(DishCommentMapper.class);
        Map<Integer, Map<Integer, DishComment>> dishCommentNotif =
                (Map<Integer, Map<Integer, DishComment>>) context.getAttribute("dishCommentNotif");

        try {
            dishCommentMapper.insertComment(dishComment);
            Dish dish = dishMapper.selectById(dishId);
            Pair<Double, Double> newRes = calculateDishScore(dish.getTotalScore(), dish.getWeightSum(),
                    userComment, user.getExperience(), userCommentsPerOnline);
            dishMapper.updateDishScore(dishId, newRes.first, newRes.second);

            sqlSession.commit();
            // 添加一条提醒
            Map<Integer, DishComment> canteenDishCommentMap = dishCommentNotif.get(dish.getCanteenId());
            canteenDishCommentMap.put(dishComment.getDishCommentId(), dishComment);
            int i = 1/0;
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

    private Pair<Double, Double> calculateDishScore(double dishScoreN, double weightSumN,
                                                    double userComment, int userExp, int userCommentsPerOnline) {
        double weight = 10*Math.atan(0.002*(double)userExp) * 1/(double)(userCommentsPerOnline+1);
        double weightSumN1 = weightSumN + weight;
        double dishScoreN1 = (dishScoreN*weightSumN + weight*userComment) / weightSumN1;

        return new Pair<>(dishScoreN1, weightSumN1);
    }
}
