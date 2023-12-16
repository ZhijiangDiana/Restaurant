# 需求e：食堂检索
***

## /GetCanteen 获取食堂列表
### 概述
    获得所有食堂信息列表
### 接口
    无有效传入
    传出：
        canteen结构体数组，里面包含了canteen表所有数据
        [
            {
                "canteenId": 1,
                "description": "下北泽高级会员制餐厅，由淳平厨师长为各位美食家提供符合身份的美食（确信）",
                "endTime": 40748000,
                "location": "军工路114514号",
                "name": "下北泽高级会员制餐厅",
                "reportCount": 0,
                "startTime": 13514000
                "image": "xxxxxxxxxx"
            },
            {
                "canteenId": 2,
                "description": "上理会员制五食堂，和思餐厅的菜品价格相比有过之而无不及。。。。。。",
                "endTime": 46800000,
                "location": "军工路516号",
                "name": "上理会员制五食堂",
                "reportCount": 1,
                "startTime": 0
                "image": "xxxxxxxxxx"
            }, 
            ...
        ]
***

## /GetCanteenAnnouncement 获取食堂公告列表
### 概述
    传入食堂canteenId，获得其所有公告
### 接口
    传入：
        canteenId(必需) int型，食堂id
        {
            "canteenId": 2
        }
    传出：
        announcement结构体数组，包含了announcement表所有字段
        [
            {
                "announcementID": 1,
                "body": "我燃烧你的梦，mapper跑不了一点，sql一句三个错",
                "canteenId": 2,
                "title": "57你数据库写的什么玩意"
            },
            {
                "announcementID": 2,
                "body": "不是我",
                "canteenId": 2,
                "title": "我爸爸"
            },
            ...
        ]
***

## /GetCanteenDish 获取食堂所有菜品
### 概述
    传入食堂canteenId，获得其所有菜品
### 接口
    传入：
        canteenId(必需) int型，食堂id
        {
            "canteenId": 2
        }
    传出：
        dish结构体数组，包含了dish表所有字段
        [
            {
                "canteenId": 2,
                "dishId": 5,
                "dishName": "酸菜鱼",
                "price": 18,
                "priceSale": 0,
                "series": 1,
                "totalScore": 0.0,
                "weightSum": 0.0
            },
            {
                "canteenId": 2,
                "dishId": 6,
                "dishName": "叉烧饭",
                "price": 20,
                "priceSale": 0,
                "series": 2,
                "totalScore": 5.0,
                "weightSum": 4.215636042683947
            },
            ...
        ]
***

## /GetCanteenInfo 获取食堂具体信息
### 概述
    传入食堂canteenId，获得其所有信息
### 接口
    传入：
        canteenId(必需) int型，食堂id
        {
            "canteenId": 2
        }
    传出：
        dish结构体数组，包含了dish表所有字段
        {
            "canteenId": 3,
            "description": "上理北校民族风味餐厅，健康的食物不放盐",
            "endTime": 46800000,
            "image": "xxxxxxx"
            "location": "军工路516号",
            "name": "上理北校民族风味餐厅",
            "reportCount": 0,
            "startTime": 0
        }
***
