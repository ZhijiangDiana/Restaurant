package com.restaurant.restaurant.pojo.entity;

import com.alibaba.fastjson.JSON;

public class Vote {
    private Integer voteId;
    private Integer canteenId;
    private String result;

    public Integer getVoteId() {
        return voteId;
    }

    public void setVoteId(Integer voteId) {
        this.voteId = voteId;
    }

    public Integer getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(Integer canteenId) {
        this.canteenId = canteenId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "voteId=" + voteId +
                ", canteenId=" + canteenId +
                ", result='" + result + '\'' +
                '}';
    }

    public Vote(Integer voteId, Integer canteenId, String result) {
        this.voteId = voteId;
        this.canteenId = canteenId;
        this.result = result;
    }
}
