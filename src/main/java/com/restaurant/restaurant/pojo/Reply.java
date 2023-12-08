package com.restaurant.restaurant.pojo;

public class Reply {
    private String sender_name;
    private String receiver_name;
    private String content;

    public Reply(String sender_name, String receiver_name, String content) {
        this.sender_name = sender_name;
        this.receiver_name = receiver_name;
        this.content = content;
    }

    public String getSender_name() {
        return sender_name;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
