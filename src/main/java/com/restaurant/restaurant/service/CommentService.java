package com.restaurant.restaurant.service;

import com.restaurant.restaurant.mapper.CommentMapper;
import com.restaurant.restaurant.pojo.entity.Comment;
import com.restaurant.restaurant.pojo.entity.User;
import com.restaurant.restaurant.utils.Constants;
import com.restaurant.restaurant.utils.FrontEndUtils;
import com.restaurant.restaurant.utils.SensitiveCommentFilter;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * 评论展示业务逻辑,分别对应默认、按热度、升序以及降序。
 */
public class CommentService {
    SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
    CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);
    public String getCommentList(String type) {
        sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        mapper = sqlSession.getMapper(CommentMapper.class);
        List<Comment> comments = null;
        // 默认情况
        if (Constants.COMMENTS_DEFAULT.equals(type))
            comments = mapper.selectAll();

        // 按照热度降序
        else if (Constants.COMMENTS_LIKES.equals(type))
            comments = mapper.selectAllByLikes();

        // 按照时间降序
        else if (Constants.COMMENTS_TIME_DESC.equals(type))
            comments = mapper.selectAllDesc();

        // 按照时间升序
        else if (Constants.COMMENTS_TIME_ASC.equals(type))
            comments = mapper.selectAllAsc();

        if (comments == null)
            return FrontEndUtils.objectToBody("暂无评论","1",null);
        else
            return FrontEndUtils.objectToBody(null,"0",comments);
    }

    public String insertComment(User user, String title, String body, String img){
        /**
         * 要改 userid从session里拿
         */
        user = new User();
        user.setForbidden(false);
        user.setUserId(2);
        if(user.getForbidden() == true){
            return FrontEndUtils.objectToBody("由于过往潜在不文明行为被禁止评论","1",null);
        }
        else {
            if (title == null || body == null || title == "" || body == ""){
                return FrontEndUtils.objectToBody("内容为空,请补充完整","1",null);
            }
            else {
                if (SensitiveCommentFilter.commentFilter(body) == true){
                    return FrontEndUtils.objectToBody("涉及敏感发言，请谨言慎行","1",null);
                }
                else {
                    Integer userId = user.getUserId();
                    if (img == null){
                        Comment comment = new Comment(userId,title,body,null,new Date(),0);
                        mapper.insertComment(comment);
                        sqlSession.commit();
                        return FrontEndUtils.objectToBody("发布成功","0",comment);
                    }
                    img = img.substring(img.indexOf(",") + 1);
                    byte[] imageBytes = Base64.getDecoder().decode(img);
                    Comment comment = new Comment(userId,title,body,imageBytes,new Date(),0);
                    mapper.insertComment(comment);
                    sqlSession.commit();
                    return FrontEndUtils.objectToBody("发布成功","0",null);
                }
            }
        }
    }
}
