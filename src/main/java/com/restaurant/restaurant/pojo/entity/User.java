package com.restaurant.restaurant.pojo.entity;

import java.io.Serializable;

public class User implements Serializable {
    private Integer userId;
    private String username;
    private String password;
    private Integer experience;
    private Boolean isForbidden;

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Boolean getForbidden() {
        return isForbidden;
    }

    public void setForbidden(Boolean forbidden) {
        isForbidden = forbidden;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", experience=" + experience +
                ", isForbidden=" + isForbidden +
                '}';
    }
}
