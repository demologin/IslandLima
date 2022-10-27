package com.javarush.island.sukharev.game_objects.animal.abstracts;

import com.javarush.island.sukharev.factory.EntityFactory;
import com.javarush.island.sukharev.game_objects.island.Island;
import com.javarush.island.sukharev.game_objects.vegetation.abstracts.Vegetation;
import com.javarush.island.sukharev.storage.DataBase;

import java.math.BigDecimal;
import java.util.Random;
import java.util.Set;

public abstract class Animal {
    private boolean isIEat;
    private boolean isTheyEatMe;
    private boolean isIHaveMultiplied;
    private boolean iWent;



    private final String icon;
    private BigDecimal weight;
    private final int id;


    protected Animal(String icon, BigDecimal weight, int id) {
        this.icon = icon;
        this.weight = weight;
        this.id = id;
        this.setIEat(true);
        this.setTheyEatMe(true);
        this.setIHaveMultiplied(true);
        this.setiWent(true);
    }

    public abstract void eat(Set<Animal> animalList, Set<Vegetation> vegetationList, Random random, DataBase dataBase);
    public abstract Set<Animal> reproduction(Island island, Set<Animal> animalList, Random random, DataBase dataBase, EntityFactory entityFactory);
    public abstract int[] wentToAnotherCell(Island island, Random random, DataBase dataBase, int x, int y);

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public boolean isiWent() {
        return iWent;
    }

    public void setiWent(boolean iWent) {
        this.iWent = iWent;
    }

    public int getId() {
        return id;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return icon;
    }

    public boolean isIEat() {
        return isIEat;
    }

    public void setIEat(boolean iEat) {
        isIEat = iEat;
    }

    public boolean isTheyEatMe() {
        return isTheyEatMe;
    }

    public void setTheyEatMe(boolean theyEatMe) {
        isTheyEatMe = theyEatMe;
    }

    public boolean isIHaveMultiplied() {
        return isIHaveMultiplied;
    }

    public void setIHaveMultiplied(boolean iHaveMultiplied) {
        isIHaveMultiplied = iHaveMultiplied;
    }

}
