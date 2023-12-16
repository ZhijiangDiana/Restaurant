package com.restaurant.restaurant.service.canteen_manager;

import com.restaurant.restaurant.mapper.VoteMapper;
import com.restaurant.restaurant.pojo.entity.Vote;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

public class UpdateVote {
    public UpdateVote(Vote vote) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        VoteMapper voteMapper = sqlSession.getMapper(VoteMapper.class);

        voteMapper.updateVote(vote);
        sqlSession.commit();
        sqlSession.close();
    }
}
