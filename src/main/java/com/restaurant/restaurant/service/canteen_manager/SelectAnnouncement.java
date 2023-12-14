package com.restaurant.restaurant.service.canteen_manager;

import com.restaurant.restaurant.mapper.AnnouncementMapper;
import com.restaurant.restaurant.mapper.DishMapper;
import com.restaurant.restaurant.pojo.entity.Announcement;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class SelectAnnouncement {
    public List<Announcement> SelectAnnouncement() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        AnnouncementMapper announcementMapper = sqlSession.getMapper(AnnouncementMapper.class);

        List<Announcement> announcementList = new ArrayList<>();
        announcementList = announcementMapper.getAnnouncement();

        return announcementList;
    }
}
