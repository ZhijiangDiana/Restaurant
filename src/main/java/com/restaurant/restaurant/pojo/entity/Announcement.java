package com.restaurant.restaurant.pojo.entity;

public class Announcement {
    private Integer announcementId;
    private Integer canteenId;
    private String title;
    private String body;

    public Announcement() {
    }

    public Announcement(Integer announcementId, Integer canteenId, String title, String body) {
        this.announcementId = announcementId;
        this.canteenId = canteenId;
        this.title = title;
        this.body = body;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "announcementID=" + announcementId +
                ", canteenId=" + canteenId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    public Integer getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(Integer announcementId) {
        this.announcementId = announcementId;
    }

    public Integer getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(Integer canteenId) {
        this.canteenId = canteenId;
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
}
