# 需求i：餐厅排名系统
***

## /GetCanteenRank 获取餐厅排名
### 概述
    根据餐厅菜品的评分和食堂的投诉数量，获取餐厅综合排名
### 接口
    无有效传入
    传出：
        JSON数组，按排名降序输出餐厅分数以及餐厅，每一项是一个pair
        first(必有) double型，餐厅的分数
        second(必有) canteen型，餐厅结构体
        [
            {
                "first": 45.45454545454545,
                "second": {
                    "canteenId": 2,
                    "description": "上理会员制五食堂，和思餐厅的菜品价格相比有过之而无不及。。。。。。",
                    "endTime": 46800000,
                    "location": "军工路516号",
                    "name": "上理会员制五食堂",
                    "reportCount": 1,
                    "startTime": 0
                }
            },
            {
                "first": 41.84949346774755,
                "second": {
                    "canteenId": 1,
                    "description": "下北泽高级会员制餐厅，由淳平厨师长为各位美食家提供符合身份的美食（确信）",
                    "endTime": 40748000,
                    "location": "军工路114514号",
                    "name": "下北泽高级会员制餐厅",
                    "reportCount": 0,
                    "startTime": 13514000
                }
            },
        ...]
***

