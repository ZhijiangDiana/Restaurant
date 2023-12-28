package com.restaurant.restaurant.pojo;

import java.io.Serializable;

public class ReportReplyShow implements Serializable {
    private String username;
    private String title;
    private String body;

    public ReportReplyShow() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
