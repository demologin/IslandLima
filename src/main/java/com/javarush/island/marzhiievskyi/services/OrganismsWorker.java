package com.javarush.island.marzhiievskyi.services;

import com.javarush.island.marzhiievskyi.entity.field.Cell;
import com.javarush.island.marzhiievskyi.entity.field.GameField;
import com.javarush.island.marzhiievskyi.entity.organisms.Animals;
import com.javarush.island.marzhiievskyi.entity.organisms.Organism;
import com.javarush.island.marzhiievskyi.utils.Task;

import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedDeque;

public class OrganismsWorker implements Runnable {

    private final GameField gameField;
    private final Organism typeOfOrganism;

    private final Queue<Task> tasks = new ConcurrentLinkedDeque<>();


    public OrganismsWorker(GameField gameField, Organism typeOfOrganism) {
        this.gameField = gameField;
        this.typeOfOrganism = typeOfOrganism;
    }


    @Override
    public void run() {

        Cell[][] fieldIsland = gameField.getFieldIsland();
        for (Cell[] cells : fieldIsland) {
            for (Cell cell : cells) {
                organismDoing(cell);
            }
        }
    }

    private void organismDoing(Cell cell) {
        Set<Organism> organismSet = cell.getMapOfAnimalsOnCell().get(typeOfOrganism);
        cell.getLock().lock();
        try {
            organismSet.forEach(organism -> {
                Task task = new Task(organism, action -> {
                    action.multiply(cell);
                    if (organism instanceof Animals animals) {
                        animals.eat(cell);
                        animals.move(cell);
                    }
                });
                tasks.add(task);
            });
        } finally {
            cell.getLock().unlock();
        }
        tasks.forEach(Task::execute);
        tasks.clear();
    }
}
