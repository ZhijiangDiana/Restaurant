package com.restaurant.restaurant.service.canteen_manager;

import com.restaurant.restaurant.mapper.CommentMapper;
import com.restaurant.restaurant.pojo.entity.Comment;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class SelectComment {
    public List<Comment> SelectComment() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);

        List<Comment> commentList = commentMapper.selectAll();
        sqlSession.commit();
        sqlSession.close();
        return commentList;
    }
}
