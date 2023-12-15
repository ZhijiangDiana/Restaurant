package com.restaurant.restaurant.pojo.entity;


public class Vote {
    private Integer voteId;
    private Integer canteenId;
    private String result;

    public Vote() {
    }

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
}
