package com.restaurant.restaurant.utils;

/**
 * 过滤器，过滤敏感评论并进行一系列操作(封锁回去评价权利或者冻结账号等)
 */
public class SensitiveCommentFilter {
    /**
     * 过滤言论
     * @param comment
     * @return true代表有问题 false没问题
     */
    public static Boolean commentFilter(String comment){
        if(1==1){
            return false;
        }
        return true;
    }
}
