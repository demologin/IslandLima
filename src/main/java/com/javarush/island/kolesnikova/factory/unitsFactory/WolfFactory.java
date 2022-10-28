package com.javarush.island.kolesnikova.factory.unitsFactory;

import com.javarush.island.kolesnikova.constants.PropertiesUnit;
import com.javarush.island.kolesnikova.entities.units.carnivorous.Wolf;


public class WolfFactory implements UnitFactory {


    @Override
    public Wolf makeUnit() {
        return new Wolf(PropertiesUnit.unitsProperties(PropertiesUnit.UnitsName.WOLF));
    }
}

