package com.javarush.island.kolesnikova.factory.unitsFactory;


import com.javarush.island.kolesnikova.constants.PropertiesUnit;
import com.javarush.island.kolesnikova.entities.units.herbivores.Goat;

public class GoatFactory implements UnitFactory {


    @Override
    public Goat makeUnit() {
        return new Goat(PropertiesUnit.unitsProperties(PropertiesUnit.UnitsName.GOAT));
    }
}

