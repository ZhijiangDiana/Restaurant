package com.restaurant.restaurant.pojo;

/**
 * 用来返回reply数据的 由于字段的直接返回名字字段或者工号字段都不行所以需要这个辅助类，具体解释见代码说明
 */
public class ReplyShow {
    private Integer replyId;
    private Integer commentId;
    private String sender;
    private String body;

    public ReplyShow(Integer replyId, Integer commentId, String sender, String body) {
        this.replyId = replyId;
        this.commentId = commentId;
        this.sender = sender;
        this.body = body;
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

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
