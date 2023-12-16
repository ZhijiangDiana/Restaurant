package com.restaurant.restaurant.service.vote;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.mapper.VoteMapper;
import com.restaurant.restaurant.pojo.entity.Vote;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import jakarta.servlet.ServletContext;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetVoteService {
    public List<Vote> getCanteenFinishedVotes(int canteenId) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        VoteMapper voteMapper = sqlSession.getMapper(VoteMapper.class);

        List<Vote> voteList = voteMapper.selectFinishedByCanteenId(canteenId);

        sqlSession.close();
        return voteList;
    }

    public List<Vote> getRunningVotes(ServletContext context) {
        Map<Integer, Map<String, Integer>> votes =
                new HashMap<>((Map<Integer, Map<String, Integer>>) context.getAttribute("votes"));
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        VoteMapper voteMapper = sqlSession.getMapper(VoteMapper.class);

        List<Vote> voteList = voteMapper.selectRunning();
        for (Vote vote : voteList) {
            Map<String, Integer> oneVote = votes.get(vote.getVoteId());
            String voteResult = JSON.toJSONString(oneVote);
            if (vote.getResult() == null) // 保证一致性
                vote.setResult(voteResult);
        }

        sqlSession.close();
        return voteList;
    }
}
