package com.javarush.island.kolesnikova.entities.units;


import com.javarush.island.kolesnikova.actions.Eating;
import com.javarush.island.kolesnikova.actions.Reproduction;
import com.javarush.island.kolesnikova.actions.Running;
import com.javarush.island.kolesnikova.constants.PropertiesUnit;


public abstract class Animals extends Unit
        implements Reproduction, Eating, Running {


    public Animals(PropertiesUnit propertiesUnit) {
        this.icon = propertiesUnit.getIcon();
        this.name = propertiesUnit.getName();
        this.weight = propertiesUnit.getWeight();
        this.maxUnitsInCell = propertiesUnit.getMaxUnitsInCell();
        this.speed = propertiesUnit.getSpeed();
        this.kilogramOfFood = propertiesUnit.getKilogramOfFood();
        this.id = getId();


    }

    @Override
    public void run() {
        super.run();
    }

    }



