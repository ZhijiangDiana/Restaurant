package com.restaurant.restaurant.pojo.entity;

public class ReportReply {
    private Integer reportReplyId;
    private Integer reportId;
    private Integer canteenAdminId;
    private String body;

    public ReportReply() {
    }

    public Integer getReportReplyId() {
        return reportReplyId;
    }

    public void setReportReplyId(Integer reportReplyId) {
        this.reportReplyId = reportReplyId;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Integer getCanteenAdminId() {
        return canteenAdminId;
    }

    public void setCanteenAdminId(Integer canteenAdminId) {
        this.canteenAdminId = canteenAdminId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ReportReply{" +
                "reportReplyId=" + reportReplyId +
                ", reportId=" + reportId +
                ", canteenAdminId=" + canteenAdminId +
                ", body='" + body + '\'' +
                '}';
    }
}
