package com.javarush.island.sternard.organisms.parents;

import com.javarush.island.sternard.settings.Settings;

import java.util.Map;

public class Animal extends Organism {

    public Animal(String name, double weight, int maxOnCell, String icon, String organismMainType, String organismType, Map<String, Integer> possibleFood, int speed, double energy, double maxFoodForSatiety) {
        super(name, weight, maxOnCell, icon, organismMainType, organismType, possibleFood, speed, energy, maxFoodForSatiety);
    }

    public void reduceAnimalEnergy(Animal animal) {
        double reduceEnergyPercent = Settings.get().getReduceEnergyPercent();
//        animal.setEnergy(animal.getEnergy() - ((animal.getEnergy() / 100) * reduceEnergyPercent));
        animal.setEnergy(animal.getEnergy() - reduceEnergyPercent);
    }

    public void increaseAnimalEnergy(Animal animal) {
        double increaseEnergyPercent = Settings.get().getIncreaseEnergyPercent();
//        animal.setEnergy(((animal.getEnergy() / 100) * increaseEnergyPercent) + animal.getEnergy());
        animal.setEnergy(increaseEnergyPercent + animal.getEnergy());
    }

}
