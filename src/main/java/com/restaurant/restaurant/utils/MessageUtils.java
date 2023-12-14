package com.restaurant.restaurant.utils;

import com.alibaba.fastjson.JSON;
import com.restaurant.restaurant.ws.pojo.ResultMessage;

/**
 * @version v1.0
 * @ClassName: MessageUtils
 * @Description: 封装json格式消息的工具类
 * @Author: 黑马程序员
 */
public class MessageUtils {

    public static String getMessage(boolean isSystemMessage,String fromName, Object message) {

        ResultMessage result = new ResultMessage();
        result.setSystem(isSystemMessage);
        result.setMessage(message);
        if(fromName != null) {
            result.setFromName(fromName);
        }
        return JSON.toJSONString(result);
    }
}
