package com.restaurant.restaurant.service.vote;

import com.restaurant.restaurant.mapper.VoteMapper;
import com.restaurant.restaurant.pojo.entity.Vote;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

public class UploadVoteService {
    /**
     * 发起投票
     * @param vote 要填入canteenId, startTime, endTime
     */
    public int uploadVote(Vote vote) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        VoteMapper voteMapper = sqlSession.getMapper(VoteMapper.class);

        voteMapper.insertVote(vote);
        sqlSession.commit();
        sqlSession.close();

        return vote.getVoteId();
    }
}
