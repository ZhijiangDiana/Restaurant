package com.restaurant.restaurant.pojo.entity;

import java.util.Date;

public class Message {
    private Integer messageId;
    private Integer senderUserId;
    private Integer receiverUserId;
    private Date sendTime;
    private String body;

    public Message(Integer senderUserId, Integer receiverUserId, Date sendTime, String body) {
        this.senderUserId = senderUserId;
        this.receiverUserId = receiverUserId;
        this.sendTime = sendTime;
        this.body = body;
    }

    public Message() {
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(Integer senderUserId) {
        this.senderUserId = senderUserId;
    }

    public Integer getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(Integer receiverUserId) {
        this.receiverUserId = receiverUserId;
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

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", senderUserId=" + senderUserId +
                ", receiverUserId=" + receiverUserId +
                ", sendTime=" + sendTime +
                ", body='" + body + '\'' +
                '}';
    }
}
