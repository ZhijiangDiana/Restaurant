package com.restaurant.restaurant.pojo;

import java.io.Serializable;

public class DishCommentReplyShow implements Serializable {
    private Integer dishCommentReplyId;
    private String username;
    private String title;
    private String body;

    public DishCommentReplyShow() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Integer getDishCommentReplyId() {
        return dishCommentReplyId;
    }

    public void setDishCommentReplyId(Integer dishCommentReplyId) {
        this.dishCommentReplyId = dishCommentReplyId;
    }

    @Override
    public String toString() {
        return "DishCommentReply{" +
                "username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
