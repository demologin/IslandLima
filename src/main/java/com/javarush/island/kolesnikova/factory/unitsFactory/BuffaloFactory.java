package com.javarush.island.kolesnikova.factory.unitsFactory;


import com.javarush.island.kolesnikova.constants.PropertiesUnit;
import com.javarush.island.kolesnikova.entities.units.herbivores.Buffalo;

public class BuffaloFactory implements UnitFactory {


    @Override
    public Buffalo makeUnit() {
        return new Buffalo(PropertiesUnit.unitsProperties(PropertiesUnit.UnitsName.BUFFALO));
    }
}

