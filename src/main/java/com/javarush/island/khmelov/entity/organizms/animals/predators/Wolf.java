package com.javarush.island.khmelov.entity.organizms.animals.predators;

import com.javarush.island.khmelov.abstraction.annotations.TypeData;
import com.javarush.island.khmelov.entity.organizms.Limit;

@TypeData(name = "Волк", icon = "\uD83D\uDC3A", maxWeight = 50, maxCount = 30, maxSpeed = 3, maxFood = 8)
public class Wolf extends Predator {


    public Wolf(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }

}
