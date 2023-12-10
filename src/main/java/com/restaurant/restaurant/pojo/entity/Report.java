package com.restaurant.restaurant.pojo.entity;

public class Report {
    private Integer reportId;
    private Integer userId;
    private Integer canteenId;
    private String title;
    private String body;

    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", userId=" + userId +
                ", canteenId=" + canteenId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Report(Integer reportId, Integer userId, Integer canteenId, String title, String body) {
        this.reportId = reportId;
        this.userId = userId;
        this.canteenId = canteenId;
        this.title = title;
        this.body = body;
    }

    public Report() {
    }
}
