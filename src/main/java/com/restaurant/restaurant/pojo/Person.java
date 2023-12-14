package com.restaurant.restaurant.pojo;

import lombok.Data;

/**
 * @version v1.0
 * @ClassName: User
 * @Description: 接收登录请求的数据
 * @Author: 黑马程序员
 */
@Data
public class Person {

    private String userId;
    private String username;
    private String password;
}
