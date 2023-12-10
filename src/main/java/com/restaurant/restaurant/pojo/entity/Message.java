package com.restaurant.restaurant.pojo.entity;

import java.util.Date;

public class Message {
    private Integer messageId;
    private Integer sendUserId;
    private Integer receiveUseId;
    private Date sendTime;
    private String body;

    public Message(Integer messageId, Integer sendUserId, Integer receiveUseId, Date sendTime, String body) {
        this.messageId = messageId;
        this.sendUserId = sendUserId;
        this.receiveUseId = receiveUseId;
        this.sendTime = sendTime;
        this.body = body;
    }

    public Message() {
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", sendUserId=" + sendUserId +
                ", receiveUseId=" + receiveUseId +
                ", sendTime=" + sendTime +
                ", body='" + body + '\'' +
                '}';
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(Integer sendUserId) {
        this.sendUserId = sendUserId;
    }

    public Integer getReceiveUseId() {
        return receiveUseId;
    }

    public void setReceiveUseId(Integer receiveUseId) {
        this.receiveUseId = receiveUseId;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
