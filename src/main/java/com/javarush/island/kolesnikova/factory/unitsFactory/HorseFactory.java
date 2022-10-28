package com.javarush.island.kolesnikova.factory.unitsFactory;


import com.javarush.island.kolesnikova.constants.PropertiesUnit;
import com.javarush.island.kolesnikova.entities.units.herbivores.Horse;

public class HorseFactory implements UnitFactory {


    @Override
    public Horse makeUnit() {
        return new Horse(PropertiesUnit.unitsProperties(PropertiesUnit.UnitsName.HORSE));
    }
}

