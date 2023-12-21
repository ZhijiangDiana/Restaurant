package com.restaurant.restaurant.pojo.entity;

public class CanteenAdmin {
    private Integer canteenAdminId;
    private Integer canteenId;
    private String username;
    private String password;

    public CanteenAdmin(Integer canteenAdminId, Integer canteenId, String username, String password) {
        this.canteenAdminId = canteenAdminId;
        this.canteenId = canteenId;
        this.username = username;
        this.password = password;
    }

    public CanteenAdmin() {
    }

    public Integer getCanteenAdminId() {
        return canteenAdminId;
    }

    public void setCanteenAdminId(Integer canteenAdminId) {
        this.canteenAdminId = canteenAdminId;
    }

    public Integer getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(Integer canteenId) {
        this.canteenId = canteenId;
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

    @Override
    public String toString() {
        return "CanteenAdmin{" +
                "canteenAdminId=" + canteenAdminId +
                ", canteenId=" + canteenId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
