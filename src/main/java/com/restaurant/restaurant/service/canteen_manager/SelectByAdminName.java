package com.restaurant.restaurant.service.canteen_manager;

import com.restaurant.restaurant.mapper.CanteenAdminMapper;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

/*
    根据当前用户id查看管理的食堂
 */
public class SelectByAdminName {
    public int SelectByAdminName(String name) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        CanteenAdminMapper canteenAdminMapper = sqlSession.getMapper(CanteenAdminMapper.class);

        int canteen_id = canteenAdminMapper.selectByName(name);

        sqlSession.commit();
        sqlSession.close();

        return canteen_id;
    }
}
