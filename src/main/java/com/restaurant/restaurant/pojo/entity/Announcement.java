package com.restaurant.restaurant.pojo.entity;

public class Announcement {
    private Integer announcementID;
    private Integer canteenId;
    private String title;
    private String body;

    public Announcement() {
    }

    public Announcement(Integer announcementID, Integer canteenId, String title, String body) {
        this.announcementID = announcementID;
        this.canteenId = canteenId;
        this.title = title;
        this.body = body;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "announcementID=" + announcementID +
                ", canteenId=" + canteenId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    public Integer getAnnouncementID() {
        return announcementID;
    }

    public void setAnnouncementID(Integer announcementID) {
        this.announcementID = announcementID;
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
