package com.javarush.island.kolesnikova.factory.unitsFactory;


import com.javarush.island.kolesnikova.constants.PropertiesUnit;
import com.javarush.island.kolesnikova.entities.units.herbivores.Sheep;

public class SheepFactory implements UnitFactory {


    @Override
    public Sheep makeUnit() {
        return new Sheep(PropertiesUnit.unitsProperties(PropertiesUnit.UnitsName.SHEEP));
    }
}

