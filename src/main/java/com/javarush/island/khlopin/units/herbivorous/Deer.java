package com.javarush.island.khlopin.units.herbivorous;

import com.javarush.island.khlopin.settings.Properties;
import com.javarush.island.khlopin.units.Herbivorous;


public class Deer extends Herbivorous {


    private final Properties properties;

    public Deer() {
        this.properties = new Properties(300, 20, 4, 50, "\uD83E\uDD8C", 7);

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
