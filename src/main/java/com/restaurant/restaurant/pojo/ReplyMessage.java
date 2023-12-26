package com.restaurant.restaurant.pojo;

public class ReplyMessage {
    private String name;

    private String title;
    private String body;


    public ReplyMessage(String name, String title, String body) {
        this.name = name;
        this.title = title;
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "ReplyMessage{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
