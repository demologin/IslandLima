package com.javarush.island.sternard.actions;

import com.javarush.island.sternard.actions.interfaces.Eating;
import com.javarush.island.sternard.controller.Controller;
import com.javarush.island.sternard.map.Cell;
import com.javarush.island.sternard.organisms.parents.Animal;
import com.javarush.island.sternard.organisms.parents.Organism;
import com.javarush.island.sternard.settings.Settings;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.javarush.island.sternard.controller.Controller.isSameOrganisms;

@SuppressWarnings({"UnusedDeclaration"}) // reflection api use this class and method
public class Eat implements Eating {

    public void action(Animal animal, Cell cell) {
        List<Organism> possibleFoodInCellStream = cell.getOrganisms().stream()
                .filter(o -> !isSameOrganisms(animal, o))
                .toList();
        List<Organism> foodList = new ArrayList<>(possibleFoodInCellStream);

        if (!foodList.isEmpty()) {
            Iterator<Organism> iter = foodList.iterator();
            while (iter.hasNext()) {
                Organism food = iter.next();
                boolean isFood = animal.getPossibleFood().containsKey(food.getName());
                if (isFood) {
                    if (animal.getEnergy() >= 100) return;
                    this.tryToEat(animal, cell, food);
                    iter.remove();
                }
            }
        } else {
            animal.reduceAnimalEnergy(animal);
        }
    }

    private void tryToEat(Animal animal, Cell cell, Organism foodOrganism) {
        int getPossibleChance = animal.getPossibleFood().get(foodOrganism.getName());
        Integer eatChance = Settings.get().getActions().get("eat");
        double fullSatietyWeight = animal.getWeight() + animal.getMaxFoodForSatiety();
        if (getPossibleChance < eatChance) {
            if (foodOrganism.getWeight() >= animal.getMaxFoodForSatiety()) {
                animal.setWeight(fullSatietyWeight);
                animal.increaseAnimalEnergy(animal);
            } else {
                double notSatiatedButEats = animal.getWeight() + foodOrganism.getWeight();
                animal.setWeight(Math.min(notSatiatedButEats, fullSatietyWeight));
            }
            Controller.addToDiedOrganismsMap(foodOrganism);
            cell.removeOrganism(foodOrganism);
        }
    }
}
