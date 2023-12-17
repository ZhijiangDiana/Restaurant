package com.restaurant.restaurant.pojo.entity;

import java.sql.Time;
import java.util.Arrays;
import java.util.Date;

public class Canteen {
    private Integer canteenId;
    private String name;
    private String location;
    private Time startTime;
    private Time endTime;
    private String description;
    private byte[] image;
    private Integer reportCount;

    public Canteen(Integer canteenId, String name, String location, Time startTime, Time endTime, String description, byte[] image, Integer reportCount) {
        this.canteenId = canteenId;
        this.name = name;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.image = image;
        this.reportCount = reportCount;
    }

    public Canteen(String name, String location, Time startTime, Time endTime, String description, byte[] image) {
        this.name = name;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.image = image;
    }

    public Integer getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(Integer canteenId) {
        this.canteenId = canteenId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Integer getReportCount() {
        return reportCount;
    }

    public void setReportCount(Integer reportCount) {
        this.reportCount = reportCount;
    }

    @Override
    public String toString() {
        return "Canteen{" +
                "canteenId=" + canteenId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", description='" + description + '\'' +
                ", image=" + Arrays.toString(image) +
                ", reportCount=" + reportCount +
                '}';
    }
}
