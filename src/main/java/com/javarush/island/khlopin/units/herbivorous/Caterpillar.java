package com.javarush.island.khlopin.units.herbivorous;


import com.javarush.island.khlopin.settings.Properties;
import com.javarush.island.khlopin.units.Herbivorous;


public class Caterpillar extends Herbivorous {

    private final Properties properties;


    public Caterpillar() {

        this.properties = new Properties(0.01,100,0,0,"\uD83D\uDC1B",6);

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
