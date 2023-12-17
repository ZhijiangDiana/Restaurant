package com.restaurant.restaurant.pojo;

import java.util.Map;
import java.util.Set;

public class RunningVote {
    private Map<String, Integer> voteResult;
    private Set<Integer> voteUsers;
    private Object voteLock;

    public RunningVote(Map<String, Integer> voteResult, Set<Integer> voteUsers) {
        this.voteResult = voteResult;
        this.voteUsers = voteUsers;
        this.voteLock = new Object();
    }

    public Map<String, Integer> getVoteResult() {
        return voteResult;
    }

    public void setVoteResult(Map<String, Integer> voteResult) {
        this.voteResult = voteResult;
    }

    public Set<Integer> getVoteUsers() {
        return voteUsers;
    }

    public void setVoteUsers(Set<Integer> voteUsers) {
        this.voteUsers = voteUsers;
    }

    public Object getVoteLock() {
        return voteLock;
    }

    public void setVoteLock(Object voteLock) {
        this.voteLock = voteLock;
    }
}
