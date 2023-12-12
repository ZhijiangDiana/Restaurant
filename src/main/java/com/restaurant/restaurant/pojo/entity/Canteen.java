package com.restaurant.restaurant.pojo.entity;

import java.util.Date;

public class Canteen {
    private Integer canteenId;
    private String         name;
    private String location;
    private Date startTime;
    private Date         endTime;
    private String description;
    private Integer report_count;

    @Override
    public String toString() {
        return "Canteen{" +
                "canteenId=" + canteenId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", description='" + description + '\'' +
                '}';
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getReport_count() {
        return report_count;
    }

    public void setReport_count(Integer report_count) {
        this.report_count = report_count;
    }

    public Canteen(Integer canteenId, String name, String location, Date startTime, Date endTime, String description, Integer report_count) {
        this.canteenId = canteenId;
        this.name = name;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.report_count = report_count;
    }

    public Canteen() {
    }
}
