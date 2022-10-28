package com.javarush.island.khlopin.units.herbivorous;

import com.javarush.island.khlopin.settings.Properties;
import com.javarush.island.khlopin.units.Herbivorous;


public class Sheep extends Herbivorous {

    private final Properties properties;


    public Sheep() {

        properties = new Properties(70,140,3,15,"\uD83D\uDC11",13);

    }
    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public int getId() {
        return getProperties().id;
    }

}
