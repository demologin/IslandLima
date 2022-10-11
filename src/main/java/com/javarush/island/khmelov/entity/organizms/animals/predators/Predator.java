package com.javarush.island.khmelov.entity.organizms.animals.predators;

import com.javarush.island.khmelov.entity.organizms.Limit;
import com.javarush.island.khmelov.entity.organizms.animals.Animal;

public abstract class Predator extends Animal {


    public Predator(String name, String icon, double weight, Limit limit) {
        super(name, icon, weight, limit);
    }


}
