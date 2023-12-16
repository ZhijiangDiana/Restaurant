package com.restaurant.restaurant.service.canteen_manager;

import com.restaurant.restaurant.mapper.CanteenAdminMapper;
import com.restaurant.restaurant.mapper.DishMapper;
import com.restaurant.restaurant.pojo.entity.Canteen;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

public class Update_Canteen {
    public Update_Canteen(Canteen canteen) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        CanteenAdminMapper canteenAdminMapper = sqlSession.getMapper(CanteenAdminMapper.class);

        canteenAdminMapper.updateCanteenData(canteen);

        sqlSession.commit();
        sqlSession.close();
    }
}
