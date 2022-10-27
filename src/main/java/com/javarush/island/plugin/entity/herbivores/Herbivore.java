package com.javarush.island.plugin.entity.herbivores;

import com.javarush.island.plugin.entity.Animal;
import com.javarush.island.plugin.gameset.Fields;


import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;


public abstract class Herbivore extends Animal {
    public Herbivore(Fields fields) {
        super(fields);
    }

    //Временная заглушка для реализации логики поедания травы с вероятностью в 50%(не реализован голод/сытость)
    public void eat(Set<Animal> grass){
        int randomNum;
        Iterator<Animal> iterator = grass.iterator();
        while (iterator.hasNext()) {
            Animal animal = iterator.next();
            //Создаем случайное число
            randomNum = ThreadLocalRandom.current().nextInt(2);
            //Если данное случайное число равно 1, то поедаем траву(т.е. удаляем текущий объект из коллекции)
            if (randomNum == 1) {
                iterator.remove();
            }
        }
    }
}
