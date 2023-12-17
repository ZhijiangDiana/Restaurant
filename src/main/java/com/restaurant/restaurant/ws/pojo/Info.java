package com.restaurant.restaurant.ws.pojo;

import lombok.Data;

/**
 * @version v1.0
 * @ClassName: Message
 * @Description: 用于封装浏览器发送给服务端的消息数据
 * @Author: 黑马程序员
 */
@Data
public class Info {
    private String fromId;
    private String toName;
    private String toId;
    private String message;
}
