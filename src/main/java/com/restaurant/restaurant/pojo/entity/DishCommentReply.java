package com.restaurant.restaurant.pojo.entity;

public class DishCommentReply {
    private Integer dishCommentReplyId;
    private Integer dishCommentId;
    private Integer canteenAdminId;
    private String title;
    private String body;

    public DishCommentReply() {
    }

    public Integer getDishCommentReplyId() {
        return dishCommentReplyId;
    }

    public void setDishCommentReplyId(Integer dishCommentReplyId) {
        this.dishCommentReplyId = dishCommentReplyId;
    }

    public Integer getDishCommentId() {
        return dishCommentId;
    }

    public void setDishCommentId(Integer dishCommentId) {
        this.dishCommentId = dishCommentId;
    }

    public Integer getCanteenAdminId() {
        return canteenAdminId;
    }

    public void setCanteenAdminId(Integer canteenAdminId) {
        this.canteenAdminId = canteenAdminId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "DishCommentReply{" +
                "dishCommentReplyId=" + dishCommentReplyId +
                ", dishCommentId=" + dishCommentId +
                ", canteenAdminId=" + canteenAdminId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
