package com.javarush.island.khmelov.entity.organizms.animals.predators;

import com.javarush.island.khmelov.abstraction.annotations.Setting;
import com.javarush.island.khmelov.entity.organizms.Limit;

@Setting(name = "Медведь", icon = "\uD83D\uDC3A", maxWeight = 50, maxCount = 30, maxSpeed = 3, maxFood = 8)
public class Bear extends Predator {

    public Bear(String name, String icon, double weight, Limit limit) {
        super(name, icon, weight, limit);
    }

}
