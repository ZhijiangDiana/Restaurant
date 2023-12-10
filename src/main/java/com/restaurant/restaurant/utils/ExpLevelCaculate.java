package com.restaurant.restaurant.utils;

import com.restaurant.restaurant.pojo.LevelInfo;

public class ExpLevelCaculate {
    public static LevelInfo caculateLevel(Integer exp){
        if(exp <= 50)
            return new LevelInfo("Lv1",200-exp);
        else if(exp > 50 && exp <= 200)
            return new LevelInfo("Lv2",500-exp);
        else if(exp > 200 && exp <= 500)
            return new LevelInfo("Lv3",1000-exp);
        else if(exp > 500 && exp <= 1000)
            return new LevelInfo("Lv4",2000-exp);
        else if(exp > 1000 && exp <= 2000)
            return new LevelInfo("Lv5",5000-exp);
        else
            return new LevelInfo("Lv6",9999999);
    }
}
