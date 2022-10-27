package com.javarush.island.kolesnikova.factory.unitsFactory;


import com.javarush.island.kolesnikova.constants.PropertiesUnit;
import com.javarush.island.kolesnikova.entities.units.herbivores.Duck;

public class DuckFactory implements UnitFactory {


    @Override
    public Duck makeUnit() {
        return new Duck(PropertiesUnit.unitsProperties(PropertiesUnit.UnitsName.DUCK));
    }
}

