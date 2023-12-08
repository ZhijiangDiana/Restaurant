package com.restaurant.restaurant.pojo.entity;

import java.io.Serializable;
import java.util.Arrays;

public class Comment implements Serializable {
    private Integer commentId;
    private Integer userId;
    private Integer dishId;
    private String title;
    private String body;
    private byte[] image;
    private Integer likes;

    public Comment() {
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

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
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

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", userId=" + userId +
                ", dishId=" + dishId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", image=" + Arrays.toString(image) +
                ", likes=" + likes +
                '}';
    }
}
