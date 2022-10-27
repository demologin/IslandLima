package com.javarush.island.khlopin.units.herbivorous;

import com.javarush.island.khlopin.settings.Properties;
import com.javarush.island.khlopin.units.Herbivorous;


public class WildBoar extends Herbivorous {
    private final Properties properties;


    public WildBoar() {
        this.properties = new Properties(400,50,2,50,"\uD83D\uDC11",14);
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
