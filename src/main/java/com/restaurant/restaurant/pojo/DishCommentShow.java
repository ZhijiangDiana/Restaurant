package com.restaurant.restaurant.pojo;

import java.io.Serializable;

public class DishCommentShow implements Serializable {
    private Integer dishCommentId;
    private String username;
    private String dishName;
    private Double score;
    private String tittle;
    private String body;
    private byte[] image;

    public DishCommentShow() {
    }

    public Integer getDishCommentId() {
        return dishCommentId;
    }

    public void setDishCommentId(Integer dishCommentId) {
        this.dishCommentId = dishCommentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
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
}
