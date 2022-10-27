package com.javarush.island.plugin.entity.carnivores;

import com.javarush.island.plugin.entity.Animal;
import com.javarush.island.plugin.entity.Organism;
import com.javarush.island.plugin.gameset.Fields;
import com.javarush.island.plugin.gameset.GamePreferences;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Carnivore extends Animal {

    public Carnivore(Fields fields) {
        super(fields);
    }

    public void eat(Set<Animal> herbivores) {
        int randomNum;
        Iterator<Animal> iterator = herbivores.iterator();
        while (iterator.hasNext()) {
            Animal alive = iterator.next();
            //Создаем случайное число
            randomNum = ThreadLocalRandom.current().nextInt(10);
            //Если данное случайное число меньше вероятностного предела из настроек животного деленного на 10, то поедаем жертву(т.е. удаляем текущий объект из коллекции)
            int currentProbability = GamePreferences.getInstance().getProbabilities()[Organism.valueOf(this.getClass().getSimpleName().toUpperCase()).ordinal()][Organism.valueOf(alive.getClass().getSimpleName().toUpperCase()).ordinal()];
            if (randomNum < currentProbability) {
                iterator.remove();
            }
        }
    }
}
