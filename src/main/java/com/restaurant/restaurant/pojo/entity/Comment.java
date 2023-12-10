package com.restaurant.restaurant.pojo.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class Comment implements Serializable {
    private Integer commentId;
    private Integer userId;
    private String title;
    private String body;
    private byte[] image;
    private Date publishTime;
    private Integer likes;

    public Comment() {
    }

    public Comment(Integer userId, String title, String body, byte[] image, Date publishTime, Integer likes) {
        this.userId = userId;
        this.title = title;
        this.body = body;
        this.image = image;
        this.publishTime = publishTime;
        this.likes = likes;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Date getPublicTime() {
        return publishTime;
    }

    public void setPublicTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", image=" + Arrays.toString(image) +
                ", publishTime=" + publishTime +
                ", likes=" + likes +
                '}';
    }
}
