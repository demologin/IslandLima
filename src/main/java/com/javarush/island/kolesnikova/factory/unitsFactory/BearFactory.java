package com.javarush.island.kolesnikova.factory.unitsFactory;


import com.javarush.island.kolesnikova.constants.PropertiesUnit;
import com.javarush.island.kolesnikova.entities.units.carnivorous.Bear;

public class BearFactory implements UnitFactory {


    @Override
    public Bear makeUnit() {
        return new Bear(PropertiesUnit.unitsProperties(PropertiesUnit.UnitsName.BEAR));
    }
}

