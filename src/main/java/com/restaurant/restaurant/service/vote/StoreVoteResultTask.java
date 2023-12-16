package com.restaurant.restaurant.service.vote;

import com.alibaba.fastjson.JSON;
import com.restaurant.restaurant.mapper.VoteMapper;
import com.restaurant.restaurant.pojo.entity.Vote;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import jakarta.servlet.ServletContext;
import org.apache.ibatis.session.SqlSession;

import java.util.Map;
import java.util.TimerTask;

public class StoreVoteResultTask extends TimerTask {
    private final Vote vote;
    private final ServletContext context;

    /**
     * 添加一个投票任务，截止时存储投票结果
     * @param vote
     * @param context
     */
    public StoreVoteResultTask(Vote vote, ServletContext context) {
        this.vote = vote;
        this.context = context;
    }

    @Override
    public void run() {
        // 读取context中的对象，将其转为json
        Map<Integer, Map<String, Integer>> votes =
                (Map<Integer, Map<String, Integer>>) context.getAttribute("votes");
        Map<String, Integer> voteResult = votes.get(vote.getVoteId());
        String resultString = JSON.toJSONString(voteResult);
        vote.setResult(resultString);
        votes.remove(vote.getVoteId());
        context.setAttribute("votes", votes);
        // 将结果json传入数据库
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        VoteMapper voteMapper = sqlSession.getMapper(VoteMapper.class);
        voteMapper.updateResult(vote);
        sqlSession.commit();
        sqlSession.close();

    }
}
