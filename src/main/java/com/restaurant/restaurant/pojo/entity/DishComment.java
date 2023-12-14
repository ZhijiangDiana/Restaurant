package com.restaurant.restaurant.pojo.entity;

import java.io.Serializable;

public class DishComment implements Serializable {
    private Integer dishCommentId;
    private Integer userId;
    private Integer dishId;
    private Double score;
    private String tittle;
    private String body;
    private byte[] image;

    public DishComment() {
    }

    public Integer getDishCommentId() {
        return dishCommentId;
    }

    public void setDishCommentId(Integer dishCommentId) {
        this.dishCommentId = dishCommentId;
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

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
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

    @Override
    public String toString() {
        return "DishComment{" +
                "dishCommentId=" + dishCommentId +
                ", userId=" + userId +
                ", dishId=" + dishId +
                ", score=" + score +
                ", tittle='" + tittle + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
