# 需求c：菜品检索、菜品评价
***

## /GetDishByAttr 菜品检索
### 概述
    输入option及其对应参数，可以搜索出所有符合条件的菜品
### 接口
    传入：
        option(必需) int型，其他参数取决于option的值
        0 传入series int型，表示菜系编号
        1 传入minPrice和maxPrice int型，表示最低价和最高价
        2 传入canteen String型，食堂名称
        3 传入score double型，最低分
        {
            "option": "3",
            "score": "3"
        }
    无有效传出
***

## /GetDishInfo 获取菜品详情
    输入dishId，可以查出该菜品的详情
### 接口
    传入：
        dishId(必需) int型，菜品id
    传出：
        dishId(必有) int型，菜品id
        canteenId(必有) int型，食堂id
        dishName(必有) String型，菜品名
        image(可能有) 二进制数组，菜品图片
        price(必有) int型，菜品一般价格
        priceSale(必有) int型，菜品打折加个
        series(必有) int型，菜系序号
        totalScore(必有) double型，总分
        weightSum(必有) double型，总权重
        {
            "canteenId": 1,
            "dishId": 1,
            "dishName": "战场的真实-手打土豆泥",
            "image": "xxxxxxxxxxxxx", 
            "price": 11,
            "priceSale": 0,
            "series": 10,
            "totalScore": 3.9999999999999996,
            "weightSum": 6.72504133255542
        }
***

## /PublicDishComment 评价菜品
### 概述
    输入前端收集到的信息，可以将评论上传到数据库
### 接口
    传入：
        dishId(必需) int型，菜品id
        score(必需) double型，评价得分
        title(必需) String型，评价标题
        body(必需) String型，评价主体
        image(可选) 二进制数组，图片
        {
            "body": "哼哼哼啊啊啊啊啊",
            "title": "非常的好吃，非常的会员制", 
            "dishId": 6,
            "score": 5
        }
    无有效传出
***


        