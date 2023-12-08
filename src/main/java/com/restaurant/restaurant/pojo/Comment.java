package com.restaurant.restaurant.pojo;

import java.util.Date;

public class Comment {
    private Integer id;
    private String title;
    private String content;
    private String img;
    private Date date;

    private Integer likes;


    public Comment(Integer id, String title, String content, String img, Date date, Integer likes) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.img = img;
        this.date = date;
        this.likes = likes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }
}
