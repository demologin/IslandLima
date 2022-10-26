package com.javarush.island.uzienko.entity.properties;

import com.javarush.island.uzienko.storage.Coords;
import lombok.*;

import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ResidentProperties {
    private String type;
    private String family;
    private String icon;
    private double weight;
    private int speed;
    private double food;
    private int maxInCell;
    private Map<String, Integer> foodList;
    private double currentWeight = weight;
    private boolean dead = false;

    @Setter(AccessLevel.NONE)
    private Coords coords = new Coords(0, 0);

    public ResidentProperties(ResidentProperties residentProperties) {
        this.type = residentProperties.getType();
        this.family = residentProperties.getFamily();
        this.icon = residentProperties.getIcon();
        this.weight = residentProperties.getWeight();
        this.speed = residentProperties.getSpeed();
        this.food = residentProperties.getFood();
        this.maxInCell = residentProperties.getMaxInCell();
        this.foodList = residentProperties.getFoodList();
        this.currentWeight = residentProperties.getWeight();
        this.dead = false;
        setCoords(coords);
    }

    public void setCoords(Coords coords) {
        this.coords = new Coords(coords.getX(), coords.getY());
    }


}
