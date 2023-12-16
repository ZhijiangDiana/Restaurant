package com.restaurant.restaurant.service.canteen_manager;

import com.restaurant.restaurant.mapper.VoteMapper;
import com.restaurant.restaurant.pojo.entity.Vote;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class SelectVote {
    public List<Vote> SelectVote() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        VoteMapper voteMapper = sqlSession.getMapper(VoteMapper.class);

        List<Vote> voteList = voteMapper.selectAll();

        sqlSession.commit();
        sqlSession.close();
        return voteList;
    }
}
