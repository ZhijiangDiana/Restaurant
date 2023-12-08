package com.restaurant.restaurant.pojo;

public class R {
    private String message;
    private String flag;
    private String json;

    public R(String message, String flag, String json) {
        this.message = message;
        this.flag = flag;
        this.json = json;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
