package com.restaurant.restaurant.service.canteen_manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.restaurant.restaurant.mapper.RecommendDishMapper;
import com.restaurant.restaurant.pojo.entity.Dish;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.*;

public class GetRecommendDishService {
    public List<Dish> GetDish() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        RecommendDishMapper recommendDishMapper = sqlSession.getMapper(RecommendDishMapper.class);
        Set<Integer> recoDishId = new HashSet<>();
        List<Dish> recoDish = new ArrayList<>();
        try {
            List<String> strings = recommendDishMapper.selectBody();
            for(String string : strings){
                if (string == null || string.isEmpty()) {
                    continue;
                }
                JSONArray res = JSON.parseArray(string);
                List<Integer> resList = res.toJavaList(Integer.class);
                recoDishId.addAll(resList);
            }

            // 从dishes中随机选择四项（互不相同）
            List<Integer> recoDishIdList = new ArrayList<>(recoDishId);
            Random random = new Random();
            Set<Integer> selectedDishesId = new HashSet<>();
            while (selectedDishesId.size() < 4) {
                int randomIndex = random.nextInt(recoDishIdList.size());
                selectedDishesId.add(recoDishIdList.get(randomIndex));
            }

            // 用序号查dish对象
            for (Integer i : selectedDishesId) {
                Dish dish = recommendDishMapper.selectById(i);
                if (dish != null) {
                    recoDish.add(dish);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }
        return recoDish;
    }
}
