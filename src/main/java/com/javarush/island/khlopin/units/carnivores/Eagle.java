package com.javarush.island.khlopin.units.carnivores;

import com.javarush.island.khlopin.settings.Properties;
import com.javarush.island.khlopin.units.Carnivores;


public class Eagle extends Carnivores {

    private final Properties properties;


    public Eagle() {
        this.properties = new Properties(6,20,3,1, "\uD83E\uDD85", 1);

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
