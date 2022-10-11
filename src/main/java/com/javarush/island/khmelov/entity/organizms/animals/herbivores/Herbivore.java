package com.javarush.island.khmelov.entity.organizms.animals.herbivores;

import com.javarush.island.khmelov.entity.organizms.Limit;
import com.javarush.island.khmelov.entity.organizms.animals.Animal;

public abstract class Herbivore extends Animal {
    public Herbivore(String name, String icon, double weight, Limit limit) {
        super(name, icon, weight, limit);
    }
}
