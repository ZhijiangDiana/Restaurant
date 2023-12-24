package com.restaurant.restaurant.pojo.entity;

public class RecommendDish{
    private Integer recommendDishId;
    private Integer canteenId;
    private String title;
    private String body;

    public RecommendDish(Integer recommendDishId, Integer canteenId, String title, String body) {
        this.recommendDishId = recommendDishId;
        this.canteenId = canteenId;
        this.title = title;
        this.body = body;
    }

    public Integer getRecommendDishId() {
        return recommendDishId;
    }


    public void setRecommendDishId(Integer recommendDishId) {
        this.recommendDishId = recommendDishId;
    }

    public Integer getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(Integer canteenId) {
        this.canteenId = canteenId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "RecommendDish{" +
                "recommendDishId=" + recommendDishId +
                ", canteenId=" + canteenId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
