package com.javarush.island.khlopin.units;

import com.javarush.island.khlopin.units.carnivores.*;
import com.javarush.island.khlopin.units.herbivorous.*;
import com.javarush.island.khlopin.units.plant.Grass;
import com.javarush.island.khlopin.units.plant.Plant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnitDistributor {
    private final List<Unit> units = Arrays.asList(new Wolf(), new Snake(), new Fox(), new Eagle(), new Bear(), new Buffalo(), new Caterpillar(),
            new Deer(), new Duck(), new Goat(), new Horse(), new Mouse(), new Rabbit(), new Sheep(), new WildBoar(), new Grass());


    public List<Unit> getUnits() {
        return units;
    }

    public List<Unit> getHerbivores() {
        List<Unit> herbivores = new ArrayList<>();

        for (Unit unit : units) {
            if (unit instanceof  Herbivorous) {
                herbivores.add(unit);
            }
        }
        return herbivores;
    }

    public List<Unit> getPlants() {
        List<Unit> plants = new ArrayList<>();

        for (Unit unit : units) {
            if (unit instanceof Plant) {
                plants.add(unit);
            }
        }
        return plants;
    }

    public List<Unit> getCarnivores() {
        List<Unit> carnivores = new ArrayList<>();

        for (Unit unit : units) {
            if (unit instanceof  Carnivores) {
                carnivores.add(unit);
            }
        }
        return carnivores;
    }

}
