package com.restaurant.restaurant.pojo;

import com.restaurant.restaurant.pojo.entity.*;

import java.util.List;

/**
 * 展示该用户发布过的所有信息
 */
public class ShowAllPublishedInfo {
    private User user;
    private CanteenAdmin canteenAdmin;
    private List<Comment> commentList;
    private List<Reply> replyList;
    private List<DishComment> dishCommentList;

    public ShowAllPublishedInfo(User user, List<Comment> commentList, List<Reply> replyList, List<DishComment> dishCommentList) {
        this.user = user;
        this.commentList = commentList;
        this.replyList = replyList;
        this.dishCommentList = dishCommentList;
    }

    public ShowAllPublishedInfo(CanteenAdmin canteenAdmin, List<Reply> replyList) {
        this.canteenAdmin = canteenAdmin;
        this.replyList = replyList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CanteenAdmin getCanteenAdmin() {
        return canteenAdmin;
    }

    public void setCanteenAdmin(CanteenAdmin canteenAdmin) {
        this.canteenAdmin = canteenAdmin;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }

    public List<DishComment> getDishCommentList() {
        return dishCommentList;
    }

    public void setDishCommentList(List<DishComment> dishCommentList) {
        this.dishCommentList = dishCommentList;
    }
}
