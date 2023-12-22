package com.restaurant.restaurant.pojo.entity;

public class ReplyMessage {
    private String username;
    private String body;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ReplyMessage(String username, String body) {
        this.username = username;
        this.body = body;
    }

    public ReplyMessage() {
    }
}
