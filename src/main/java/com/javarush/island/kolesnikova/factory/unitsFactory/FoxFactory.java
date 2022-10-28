package com.javarush.island.kolesnikova.factory.unitsFactory;


import com.javarush.island.kolesnikova.constants.PropertiesUnit;
import com.javarush.island.kolesnikova.entities.units.carnivorous.Fox;

public class FoxFactory implements UnitFactory {


    @Override
    public Fox makeUnit() {
        return new Fox(PropertiesUnit.unitsProperties(PropertiesUnit.UnitsName.FOX));
    }
}

