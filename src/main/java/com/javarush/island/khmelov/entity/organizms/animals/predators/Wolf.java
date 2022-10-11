package com.javarush.island.khmelov.entity.organizms.animals.predators;

import com.javarush.island.khmelov.abstraction.annotations.Setting;
import com.javarush.island.khmelov.entity.organizms.Limit;

@Setting(name = "Волк", icon = "\uD83D\uDC3A", maxWeight = 50, maxCount = 30, maxSpeed = 3, maxFood = 8)
public class Wolf extends Predator {



    public Wolf(String name, String icon, double weight, Limit limit) {
        super(name, icon, weight, limit);
    }

}
