package com.javarush.island.sternard.services;

import com.javarush.island.sternard.controller.Controller;
import com.javarush.island.sternard.map.Cell;
import com.javarush.island.sternard.organisms.parents.Animal;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@AllArgsConstructor
public class CellService {
    private final Controller controller;

    public Runnable cellServiceStart() {
        return () -> {
            if (controller.isEndCellService(controller.getDAY_NUMBER().get())) {
                controller.stopSimulation();
            }
            for (int height = 0; height < controller.getHeight(); ++height) {
                for (int width = 0; width < controller.getWidth(); ++width) {
                    Cell cell = controller.getCells()[height][width];
                    controller.getCellRunExecutor().submit(getRunnable(cell));
                }
            }
        };
    }

    private Runnable getRunnable(Cell cell) {
        return () -> {
            List<Animal> animals = new CopyOnWriteArrayList<>(cell.getAnimals());
            for (Animal animal : animals) {
                if (!controller.isDead(animal)) {
                    controller.animalChooseActionAndDoIt(animal, cell);
                } else {
                    cell.removeOrganism(animal);
                }
            }
        };
    }
}