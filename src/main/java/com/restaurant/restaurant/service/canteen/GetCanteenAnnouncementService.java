package com.restaurant.restaurant.service.canteen;

import com.restaurant.restaurant.mapper.AnnouncementMapper;
import com.restaurant.restaurant.pojo.entity.Announcement;
import com.restaurant.restaurant.pojo.entity.Canteen;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class GetCanteenAnnouncementService {
    // 已改try-with-resources
    public List<Announcement> getCanteenAnnouncement(int canteenId) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        AnnouncementMapper announcementMapper = sqlSession.getMapper(AnnouncementMapper.class);

        List<Announcement> res = null;
        try (sqlSession) {
            res = announcementMapper.selectByCanteenId(canteenId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
}
