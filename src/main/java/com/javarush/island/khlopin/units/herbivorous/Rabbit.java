package com.javarush.island.khlopin.units.herbivorous;


import com.javarush.island.khlopin.settings.Properties;
import com.javarush.island.khlopin.units.Herbivorous;


public class Rabbit extends Herbivorous {

    private final Properties properties;



    public Rabbit() {
        properties = new Properties(2, 150, 2, 0.45, "\uD83D\uDC07",12);

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
