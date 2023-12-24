package com.restaurant.restaurant.pojo;

import java.util.Arrays;
import java.util.Date;

public class CommentShow {
    private Integer commentId;
    private String username;

    private String title;
    private String body;
    private byte[] image;
    private Date publishTime;
    private Integer likes;

    public CommentShow(Integer commentId, String username, String title, String body, byte[] image, Date publishTime, Integer likes) {
        this.commentId = commentId;
        this.username = username;
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

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
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

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "CommentShow{" +
                "commentId=" + commentId +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", image=" + Arrays.toString(image) +
                ", publishTime=" + publishTime +
                ", likes=" + likes +
                '}';
    }
}
