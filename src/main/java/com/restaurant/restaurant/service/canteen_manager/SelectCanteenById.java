package com.restaurant.restaurant.service.canteen_manager;

import com.restaurant.restaurant.mapper.CanteenMapper;
import com.restaurant.restaurant.pojo.entity.Canteen;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

public class SelectCanteenById {
    // 已改try-with-resources
    public Canteen SelectCanteenById(int id) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        CanteenMapper canteenMapper = sqlSession.getMapper(CanteenMapper.class);

        Canteen canteen = null;
        try (sqlSession) {
            canteen = canteenMapper.selectById(id);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        }
        return canteen;
    }
}
