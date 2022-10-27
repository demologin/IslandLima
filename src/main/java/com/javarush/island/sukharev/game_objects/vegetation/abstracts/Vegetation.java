package com.javarush.island.sukharev.game_objects.vegetation.abstracts;

import java.math.BigDecimal;

public abstract class Vegetation {
    private String icon;

    private boolean isTheyEatMe;
    private BigDecimal weight;

    public Vegetation(BigDecimal weight) {
        this.icon = "\uD83C\uDF3F";
        this.weight = weight;
        this.isTheyEatMe = true;
    }

    public boolean isTheyEatMe() {
        return isTheyEatMe;
    }

    public void setTheyEatMe(boolean theyEatMe) {
        isTheyEatMe = theyEatMe;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return icon;
    }
}
