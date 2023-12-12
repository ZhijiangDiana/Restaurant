package com.restaurant.restaurant.utils;

public class Constants {
    public static final int LOGIN_INVALID = 0;    // 认证失败
    public static final int USER_VERIFIED = 1;    // 师生用户
    // 食堂管理员
    public static final int RESTAURANT_ADMIN_VERIFIED = 2;    // 食堂管理员
    public static final int ADMIN_VERIFIED = 3;   // 总管理员
    public static final String COMMENTS_DEFAULT = "1";    // 默认评论展示
    public static final String COMMENTS_LIKES = "2";      // 按热度展示评论
    public static final String COMMENTS_TIME_ASC= "3";     // 按时间升序展示评论
    public static final String COMMENTS_TIME_DESC = "4";    // 按时间降序展示评论
    public static final Integer LOGIN_EXP = 1;  // 登录加经验
    public static final Integer CANTEENCOMMENT_EXP = 2; // 食堂评论加经验
    public static final Integer COMMUNITY_EXP = 3;  // 社区评论加经验
    public static final Integer REPLY_EXP = 4;  // 回复加经验
    public static final Integer COMPLAINT_EXP = 5;  // 投诉加经验
    public static final Integer VOTE_EXP = 6;   // 投票加经验
}
