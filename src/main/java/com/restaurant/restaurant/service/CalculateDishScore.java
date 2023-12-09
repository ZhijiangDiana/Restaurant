package com.restaurant.restaurant.service;

import com.restaurant.restaurant.mapper.DishMapper;
import com.restaurant.restaurant.pojo.entity.Dish;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.utils.Pair;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

public class CalculateDishScore {
    // TODO: 2023/12/9 没写Controller
    public void updateDishScore(int dishId, double userComment,
                                User user, int userCommentsPerOnline) throws NullPointerException{
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        DishMapper dishMapper = sqlSession.getMapper(DishMapper.class);

        Dish dish = dishMapper.selectById(dishId);
        if (dish == null)
            throw new NullPointerException("未找到菜品！");
        Pair<Double, Double> newRes = calculateDishScore(dish.getTotalScore(), dish.getWeightSum(),
                userComment, user.getExperience(), userCommentsPerOnline);
        dishMapper.updateDishScore(dishId, newRes.first, newRes.second);
        sqlSession.commit();

        sqlSession.close();
    }

    public Pair<Double, Double> calculateDishScore(double dishScoreN, double weightSumN,
                                    double userComment, int userExp, int userCommentsPerOnline) {
        double weight = 10*Math.atan(0.002*(double)userExp) * 1/(double)(userCommentsPerOnline+1);
        double weightSumN1 = weightSumN + weight;
        double dishScoreN1 = (dishScoreN*weightSumN + weight*userComment) / weightSumN1;

        return new Pair<>(dishScoreN1, weightSumN1);
    }
}
