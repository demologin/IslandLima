package com.javarush.island.ivanilov.workers;

import com.javarush.island.ivanilov.creatures.Animal;
import com.javarush.island.ivanilov.game.Cell;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;


public class CalcWorker implements Callable<Map<Type, Long>> {

    private final Cell cell;

    public CalcWorker(Cell cell) {
        this.cell = cell;
    }

    @Override
    public Map<Type, Long> call() {
        Map<Type, Long> calc = new HashMap<>();
        Map<Type, Set<Animal>> allAnimals = cell.getAnimals();
        for (var entry : allAnimals.entrySet()) {
            Type animalType = entry.getKey();
            long numberOfAnimals = entry.getValue().size();
            calc.put(animalType, numberOfAnimals);
        }
        double plantMass = cell.getPlants().getMass();
        calc.put(cell.getPlants().getClass(), Math.round(plantMass));

        return calc;
    }
}
