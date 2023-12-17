package com.restaurant.restaurant.service.vote;

import com.alibaba.fastjson.JSON;
import com.restaurant.restaurant.mapper.VoteMapper;
import com.restaurant.restaurant.pojo.RunningVote;
import com.restaurant.restaurant.pojo.entity.Vote;
import com.restaurant.restaurant.utils.Constants;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import jakarta.servlet.ServletContext;
import org.apache.ibatis.session.SqlSession;

import java.util.Map;
import java.util.Set;
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
        Map<Integer, RunningVote> votes =
                (Map<Integer, RunningVote>) context.getAttribute("votes");
        RunningVote runningVote = votes.get(vote.getVoteId());
        // 结束时不允许读取
        synchronized (Constants.voteLock) {
            // 结束时不允许写入
            synchronized (runningVote.getVoteLock()) {
                // 读取context中的对象，将其转为json
                Map<String, Integer> voteResult = runningVote.getVoteResult();
                Set<Integer> voteUsers = runningVote.getVoteUsers();
                String resultString = JSON.toJSONString(voteResult);
                vote.setResult(resultString);
                votes.remove(vote.getVoteId());
                voteUsers.remove(vote.getVoteId());
                context.setAttribute("votes", votes);
                context.setAttribute("voteUsers", voteUsers);
                // 将结果json传入数据库
                SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
                VoteMapper voteMapper = sqlSession.getMapper(VoteMapper.class);
                voteMapper.updateResult(vote);
                sqlSession.commit();
                sqlSession.close();
            }
        }
    }
}
