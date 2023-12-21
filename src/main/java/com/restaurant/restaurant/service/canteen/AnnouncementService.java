package com.restaurant.restaurant.service.canteen;

import com.restaurant.restaurant.mapper.AnnouncementMapper;
import com.restaurant.restaurant.pojo.entity.Announcement;
import com.restaurant.restaurant.pojo.entity.Canteen;
import com.restaurant.restaurant.utils.FrontEndUtils;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AnnouncementService {

    public String InsertAnnouncement(Integer canteenId,String title,String body ){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        AnnouncementMapper mapper = sqlSession.getMapper(AnnouncementMapper.class);

        try (sqlSession) {
            if (title == null || title.isEmpty()){
                return FrontEndUtils.objectToBody("公告标题不能为空","1",null);
            } else if (body == null || body.isEmpty()) {
                return FrontEndUtils.objectToBody("公告内容不能为空","1",null);
            }else {
                Announcement announcement = new Announcement();
                announcement.setCanteenId(canteenId);
                announcement.setTitle(title);
                announcement.setBody(body);
                int isSuccess = mapper.insertAnnouncenment(announcement);

                sqlSession.commit();

                List<Announcement> announcementsList = mapper.selectAll();

                if (isSuccess == 1){
                    return FrontEndUtils.objectToBody("发布成功","0",announcementsList);
                }
                else {
                    return FrontEndUtils.objectToBody("发布失败","1",null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            return  FrontEndUtils.objectToBody("发布失败失败","1",null);
        }

    }
}
