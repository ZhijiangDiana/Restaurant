package com.restaurant.restaurant.pojo;

public class LevelInfo {
    private String level;
    private int expToUpgrade;

    public LevelInfo(String level, int expToUpgrade) {
        this.level = level;
        this.expToUpgrade = expToUpgrade;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getExpToUpgrade() {
        return expToUpgrade;
    }

    public void setExpToUpgrade(int expToUpgrade) {
        this.expToUpgrade = expToUpgrade;
    }
}
