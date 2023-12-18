package com.restaurant.restaurant.service.vote;

import com.restaurant.restaurant.mapper.VoteMapper;
import com.restaurant.restaurant.pojo.entity.Vote;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

public class UploadVoteService {
    // 已改try-with-resources
    /**
     * 发起投票
     * @param vote 要填入canteenId, startTime, endTime
     */
    public Integer uploadVote(Vote vote) {
        boolean isSuccess;
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        VoteMapper voteMapper = sqlSession.getMapper(VoteMapper.class);

        try (sqlSession) {
            voteMapper.insertVote(vote);
            sqlSession.commit();
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            isSuccess = false;
        }
        if (isSuccess)
            return vote.getVoteId();
        else
            return null;
    }
}
