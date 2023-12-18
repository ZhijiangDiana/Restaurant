package com.restaurant.restaurant.service.vote;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.mapper.VoteMapper;
import com.restaurant.restaurant.pojo.RunningVote;
import com.restaurant.restaurant.pojo.entity.Vote;
import com.restaurant.restaurant.utils.Constants;
import com.restaurant.restaurant.utils.SqlSessionFactoryUtils;
import jakarta.servlet.ServletContext;
import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class GetVoteService {
    // 已改try-with-resources
    public static final int CAN_VOTE = 0;
    public static final int HAS_VOTED = 1;
    public static final int VOTE_FINISHED = 2;
    /**
     * 食堂管理员调用，获得本食堂id下所有已完成的投票
     * @param canteenId 食堂id
     * @return
     */
    public List<Vote> getCanteenFinishedVotes(int canteenId) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        VoteMapper voteMapper = sqlSession.getMapper(VoteMapper.class);

        List<Vote> voteList = null;
        try (sqlSession) {
            voteList = voteMapper.selectFinishedByCanteenId(canteenId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return voteList;
    }

    public List<Vote> getRunningVotes(ServletContext context) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        VoteMapper voteMapper = sqlSession.getMapper(VoteMapper.class);

        List<Vote> voteList = null;
        try (sqlSession) {
            // 获取所有投票时不允许结束投票
            synchronized (Constants.voteLock) {
                voteList = voteMapper.selectRunning();
                Map<Integer, RunningVote> votes =
                        (Map<Integer, RunningVote>) context.getAttribute("votes");
                for (Map.Entry<Integer, RunningVote> entry : votes.entrySet()) {
                    RunningVote runningVote = entry.getValue();
                    for (Vote vote : voteList) {
                        // 读取投票时不允许修改
                        synchronized (runningVote.getVoteLock()) {
                            String voteResult = JSON.toJSONString(runningVote.getVoteResult());
                            if (vote.getResult() == null) // 保证一致性
                                vote.setResult(voteResult);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return voteList;
    }

    public int getCanVote(ServletContext context, int voteId, int userId) {
        Map<Integer, RunningVote> votes =
                (Map<Integer, RunningVote>) context.getAttribute("votes");
        RunningVote runningVote = votes.get(voteId);
        // 读取投票时不允许修改
        synchronized (runningVote.getVoteLock()) {
            Set<Integer> thisVoteUser = runningVote.getVoteUsers();
            if (!votes.containsKey(voteId))
                return VOTE_FINISHED;
            if (thisVoteUser.contains(userId))
                return HAS_VOTED;
            else
                return CAN_VOTE;
        }
    }
}
