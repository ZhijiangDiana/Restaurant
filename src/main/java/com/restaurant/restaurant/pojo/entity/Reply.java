package com.restaurant.restaurant.pojo.entity;

public class Reply {
    private Integer replyId;
    private Integer commentId;
    private Integer userId;
    private Integer canteenAdminId;
    private String body;

    public Reply() {
    }

    @Override
    public String toString() {
        return "Reply{" +
                "replyId=" + replyId +
                ", commentId=" + commentId +
                ", userId=" + userId +
                ", canteenAdminId=" + canteenAdminId +
                ", body='" + body + '\'' +
                '}';
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCanteenAdminId() {
        return canteenAdminId;
    }

    public void setCanteenAdminId(Integer canteenAdminId) {
        this.canteenAdminId = canteenAdminId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Reply(Integer replyId, Integer commentId, Integer userId, Integer canteenAdminId, String body) {
        this.replyId = replyId;
        this.commentId = commentId;
        this.userId = userId;
        this.canteenAdminId = canteenAdminId;
        this.body = body;
    }
}
