package com.restaurant.restaurant.service.canteen;

import com.restaurant.restaurant.mapper.CanteenMapper;
import com.restaurant.restaurant.mapper.DishMapper;
import com.restaurant.restaurant.pojo.entity.Canteen;
import com.restaurant.restaurant.pojo.entity.Dish;
import com.restaurant.restaurant.utils.Pair;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CalculateCanteenRank {
    public List<Pair<Double, Canteen>> calculateRank() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        CanteenMapper canteenMapper = sqlSession.getMapper(CanteenMapper.class);
        DishMapper dishMapper = sqlSession.getMapper(DishMapper.class);

        // 查表并计算数据
        List<Canteen> canteenList = canteenMapper.selectAll();
        List<Pair<Double, Canteen>> res = new ArrayList<>(canteenList.size()+11);
        for (Canteen canteen : canteenList) {
            int canteenId = canteen.getCanteenId();
            List<Dish> canteenDishes = dishMapper.selectByCanteenId(canteenId);
            Double score = calculateCanteenScore(canteenDishes, canteen);
            res.add(new Pair<>(score, canteen));
        }

        // 处理排名
        Collections.sort(res, new Comparator<Pair<Double, Canteen>>() {
            @Override
            public int compare(Pair<Double, Canteen> o1, Pair<Double, Canteen> o2) {
                return o2.first.compareTo(o1.first);
            }
        });
        return res;
    }

    private Double calculateCanteenScore(List<Dish> canteenDishes, Canteen canteen) {
        double scoreSum = 0;
        double weightSum = 0;
        double res = 0;
        for (Dish dish : canteenDishes) {
            scoreSum += dish.getTotalScore() * dish.getWeightSum();
            weightSum += dish.getWeightSum();
        }
        if (weightSum == 0)
            res = 0;
        else
            res = scoreSum / weightSum;
        res = 100 * (res / (canteen.getReportCount()+10));
        return res;
    }
}
