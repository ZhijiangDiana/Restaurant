package com.restaurant.restaurant.pojo;

import lombok.Data;

/**
 * @version v1.0
 * @ClassName: Result
 * @Description: 用来封装http请求的响应数据
 * @Author: 黑马程序员
 */
@Data
public class Result {
    private boolean flag;
    private String message;
}
