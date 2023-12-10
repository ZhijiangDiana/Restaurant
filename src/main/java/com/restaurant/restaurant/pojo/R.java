package com.restaurant.restaurant.pojo;

public class R {
    private String message;
    private String flag;
    private Object object;

    public R(String message, String flag, Object object) {
        this.message = message;
        this.flag = flag;
        this.object = object;
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

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
