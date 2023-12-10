package com.restaurant.restaurant.service;

import com.restaurant.restaurant.mapper.CommentMapper;
import com.restaurant.restaurant.pojo.entity.Comment;
import com.restaurant.restaurant.utils.Constants;
import com.restaurant.restaurant.utils.FrontEndUtils;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

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

    public void insertComment(Comment comment){
        mapper.insertComment(comment);
        sqlSession.commit();
    }
}
