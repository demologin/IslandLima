package com.javarush.island.khlopin.units.carnivores;

import com.javarush.island.khlopin.settings.Properties;
import com.javarush.island.khlopin.units.Carnivores;

public class Bear extends Carnivores {
    private final Properties properties;


    public Bear() {
        this.properties = new Properties(500,5,2,80, "\uD83D\uDC3B", 0);


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