package com.javarush.island.khlopin.units.herbivorous;

import com.javarush.island.khlopin.settings.Properties;
import com.javarush.island.khlopin.units.Herbivorous;


public class Buffalo extends Herbivorous {

    private final Properties properties;

    public Buffalo() {
        this.properties = new Properties(700,10,3,100,"\uD83D\uDC03",5);

    }

    public Properties getProperties() {
        return properties;
    }

    @Override
    public int getId() {
        return getProperties().id;
    }


}
