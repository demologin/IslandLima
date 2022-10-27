package com.javarush.island.akhundov.services;

import com.javarush.island.akhundov.animals.Animal;
import com.javarush.island.akhundov.field.Cell;
import com.javarush.island.akhundov.field.GameField;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class DyingService extends AbstractService{

    public DyingService(GameField gameField){
        this.gameField = gameField;
    }

    @Override
    public void cellAction(Cell cell) {
        cell.getLock().lock();
        try {
            //Создаю копию карты животных для изменения
            Map<Class<?>, ArrayList<Animal>> mapCopy = cell
                    .getAnimalsMap()
                    .entrySet()
                    .stream()
                    .collect(Collectors
                            .toMap(Map.Entry::getKey, e -> new ArrayList<>(e.getValue()))
                    );
            for (Map.Entry<Class<?>, ArrayList<Animal>> pair : mapCopy.entrySet()) {
                ArrayList<Animal> tempList = pair.getValue();
                for(Animal animal : tempList){
                    if(animal.getWeight() < animal.getNormalWeight() / 2) {
                        cell.getAnimalsMap().get(pair.getKey()).remove(animal);
                    }
                }
            }
        }finally {
            cell.getLock().unlock();
        }
    }
}
