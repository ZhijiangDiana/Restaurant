package com.restaurant.restaurant.service.vote;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.RunningVote;
import jakarta.servlet.ServletContext;

import java.util.List;
import java.util.Map;

public class UserVoteService {
    public boolean userVote(ServletContext context, int voteId, int userId, JSONArray choices) {
        Map<Integer, RunningVote> votes = (Map<Integer, RunningVote>) context.getAttribute("votes");
        RunningVote runningVote = votes.get(voteId);
        if (runningVote == null)
            return false;
        synchronized (runningVote.getVoteLock()) {
            Map<String, Integer> voteResult = runningVote.getVoteResult();
            runningVote.getVoteUsers().add(userId);
            List<String> choiceList = choices.toJavaList(String.class);
            for (String choice : choiceList)
                if (voteResult.containsKey(choice))
                    voteResult.put(choice, voteResult.get(choice) + 1);
        }
        return true;
    }
}
