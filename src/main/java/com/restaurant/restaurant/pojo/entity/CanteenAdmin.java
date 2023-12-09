package com.restaurant.restaurant.pojo.entity;

public class CanteenAdmin {
   private Integer canteenId;
   private Integer userId;
   private String         username;
   private String password;

    @Override
    public String toString() {
        return "CanteenAdmin{" +
                "canteenId=" + canteenId +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Integer getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(Integer canteenId) {
        this.canteenId = canteenId;
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

    public CanteenAdmin(Integer canteenId, Integer userId, String username, String password) {
        this.canteenId = canteenId;
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public CanteenAdmin() {
    }
}
