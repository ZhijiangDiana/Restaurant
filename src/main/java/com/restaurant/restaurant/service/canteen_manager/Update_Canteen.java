package com.restaurant.restaurant.service.canteen_manager;

import com.restaurant.restaurant.mapper.CanteenAdminMapper;
import com.restaurant.restaurant.mapper.DishMapper;
import com.restaurant.restaurant.pojo.entity.Canteen;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

public class Update_Canteen {
    // 已改try-with-resources
    public boolean Update_Canteen(Canteen canteen) {
        boolean isSuccess;
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        CanteenAdminMapper canteenAdminMapper = sqlSession.getMapper(CanteenAdminMapper.class);

        try (sqlSession) {
            canteenAdminMapper.updateCanteenData(canteen);
            sqlSession.commit();
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            isSuccess = false;
        }
        return isSuccess;
    }
}
