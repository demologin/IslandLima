package com.javarush.island.khlopin.units.carnivores;

import com.javarush.island.khlopin.settings.Properties;
import com.javarush.island.khlopin.units.Carnivores;

public class Fox extends Carnivores {

    private final Properties properties;


    public Fox() {

        this.properties = new Properties(8,30,2,2, "\uD83E\uDD8A",2);

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
