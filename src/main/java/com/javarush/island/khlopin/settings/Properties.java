package com.javarush.island.khlopin.settings;

public class Properties {

    public double maxWeight;
    public int maxCount;
    public int maxSpeed;
    public double foodForSaturation;
    public String icon;

    public double satiety;

    public int id;

    public Properties(double weight, int maxCount, int maxSpeed, double foodForSaturation, String icon, int id) {
        this.maxWeight = weight;
        this.maxCount = maxCount;
        this.maxSpeed = maxSpeed;
        this.foodForSaturation = foodForSaturation;
        this.icon = icon;
        this.id = id;
        this.satiety = foodForSaturation;
    }
}
