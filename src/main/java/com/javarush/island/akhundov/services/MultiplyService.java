package com.javarush.island.akhundov.services;

import com.javarush.island.akhundov.animals.Animal;
import com.javarush.island.akhundov.field.Cell;
import com.javarush.island.akhundov.field.GameField;
import com.javarush.island.akhundov.plants.Grass;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class MultiplyService extends AbstractService{

    public MultiplyService(GameField gameField){
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
                if (tempList.size() > 1) { //Проверяю есть ли еще особи для спаривания
                    int maxAnimalCount = cell.getAnimalsMap().get(pair.getKey()).get(0).getMaxAmountOfAnimal(); // получаю количество животных на клетке
                    for (int i = 0; i < tempList.size() / 2; i++) { // делю их на пары и запускаю размножение
                        if (cell.getAnimalsMap().get(pair.getKey()).size() < maxAnimalCount) { // сравниваю текущее количество животных с максимальным
                            ArrayList<Animal> animalList = (cell.getAnimalsMap().get(pair.getKey())); //Получаю ссылку на список животных по соответствующему ключу в  карте.
                            animalList.add(tempList.get(i).multiply()); //добавляю новое животное в список  карты.
                        }
                    }
                }
            }

            cell.getPlantsMap().get("Grass").add(new Grass());
        }finally {
            cell.getLock().unlock();
        }
    }

}
