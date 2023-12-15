package com.restaurant.restaurant.service.canteen;

import com.restaurant.restaurant.mapper.CanteenMapper;
import com.restaurant.restaurant.pojo.entity.Canteen;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class GetCanteenService {
    public List<Canteen> getAllCanteen() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        CanteenMapper canteenMapper = sqlSession.getMapper(CanteenMapper.class);

        List<Canteen> res = canteenMapper.selectAllWithFile();
        sqlSession.close();

        return res;
    }

    public Canteen getCanteenInfo(int canteenId) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        CanteenMapper canteenMapper = sqlSession.getMapper(CanteenMapper.class);

        Canteen res = canteenMapper.selectByIdWithFile(canteenId);
        sqlSession.close();

        return res;
    }
}
