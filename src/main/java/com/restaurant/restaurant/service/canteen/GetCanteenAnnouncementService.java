package com.restaurant.restaurant.service.canteen;

import com.restaurant.restaurant.mapper.AnnouncementMapper;
import com.restaurant.restaurant.pojo.entity.Announcement;
import com.restaurant.restaurant.pojo.entity.Canteen;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class GetCanteenAnnouncementService {
    public List<Announcement> getCanteenAnnouncement(int canteenId) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        AnnouncementMapper announcementMapper = sqlSession.getMapper(AnnouncementMapper.class);

        List<Announcement> res = announcementMapper.selectByCanteenId(canteenId);
        sqlSession.close();

        return res;
    }
}
