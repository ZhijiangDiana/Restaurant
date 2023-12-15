package com.restaurant.restaurant.service;

import jakarta.servlet.ServletContext;

import java.util.Date;
import java.util.Queue;

public class FrequentCommentFilter {
    private FrequentCommentFilter() {}

    private static final int MAX_COMMENT = 10;
    private static final int RECORD_INTERVAL_S = 60;
    /**
     * 刷屏狗叫检测，传入session中的发送消息队列，返回message
     * @param queue
     * @return
     */
    public static String returnMessage(Queue<Date> queue) {
        if (frequencyCommentFilter(queue))
            return "狗叫那么快干什么";
        else
            return null;
    }

    /**
     * 刷屏狗叫检测，传入session中的发送消息队列，返回是否刷屏
     * 评判标准：
     * @param queue
     * @return
     */
    public static boolean frequencyCommentFilter(Queue<Date> queue) {
        Date currentTime = new Date(System.currentTimeMillis());
        queue.offer(currentTime);
        if (queue.size() <= MAX_COMMENT) {
            return false;
        } else {
            Date earliestTime = queue.poll();
            long interval = currentTime.getTime() - earliestTime.getTime();
            return interval / 1000 <= RECORD_INTERVAL_S; // 60秒内发送了10条消息，就判定为刷屏
        }
    }
}
