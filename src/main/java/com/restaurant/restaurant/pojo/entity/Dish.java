package com.restaurant.restaurant.pojo.entity;

import java.io.Serializable;

public class Dish implements Serializable {
    private Integer dishId;
    private Integer canteenId;
    private String dishName;
    // 菜系，仅能选择特定几个菜系
    private Integer series;
    private Integer price;
    private Integer priceSale;
    private Double totalScore;
    private Double weightSum;
    private byte[] image;

    public static final int LU_DISH = 0; //鲁菜
    public static final int CHUAN_DISH = 1; //川菜
    public static final int YUE_DISH = 2; //粤菜
    public static final int JIANG_SU_DISH = 3; //江苏菜
    public static final int MIN_DISH = 4; //闽菜
    public static final int ZHE_JIANG_DISH = 5; //浙江菜
    public static final int XIANG_DISH = 6; //湘菜
    public static final int HUI_DISH = 7; //徽菜
    public static final int HALAL_DISH = 8; //清真餐
    public static final int JAPAN_DISH = 9; //日本餐
    public static final int WESTERN_DISH = 10; //西餐
    public static final int OTHER_DISH = 114514; //其他


    public Dish() {
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public Integer getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(Integer canteenId) {
        this.canteenId = canteenId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(Integer priceSale) {
        this.priceSale = priceSale;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public Double getWeightSum() {
        return weightSum;
    }

    public void setWeightSum(Double weightSum) {
        this.weightSum = weightSum;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "dishId=" + dishId +
                ", canteenId=" + canteenId +
                ", dishName='" + dishName + '\'' +
                ", series=" + series +
                ", price=" + price +
                ", priceSale=" + priceSale +
                ", totalScore=" + totalScore +
                ", image=" + image +
                '}';
    }
}
