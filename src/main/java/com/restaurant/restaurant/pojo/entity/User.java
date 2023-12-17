package com.restaurant.restaurant.pojo.entity;

import java.io.Serializable;

public class User implements Serializable {
    private Integer userId;
    private String username;
    private String password;
    private Integer experience;
    private Boolean isForbidden;
    private Integer illegality;

    public User() {
    }

    public User(Integer userId, String username, String password, Integer experience, Boolean isForbidden, Integer illegality) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.experience = experience;
        this.isForbidden = isForbidden;
        this.illegality = illegality;
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


    public Integer getIllegality() {
        return illegality;
    }

    public void setIllegality(Integer illegality) {
        this.illegality = illegality;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", experience=" + experience +
                ", isForbidden=" + isForbidden +
                ", illegality=" + illegality +
                '}';
    }


}
