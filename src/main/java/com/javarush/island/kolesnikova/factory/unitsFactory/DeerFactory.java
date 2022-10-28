package com.javarush.island.kolesnikova.factory.unitsFactory;


import com.javarush.island.kolesnikova.constants.PropertiesUnit;
import com.javarush.island.kolesnikova.entities.units.herbivores.Deer;

public class DeerFactory implements UnitFactory {


    @Override
    public Deer makeUnit() {
        return new Deer(PropertiesUnit.unitsProperties(PropertiesUnit.UnitsName.DEER));
    }
}

