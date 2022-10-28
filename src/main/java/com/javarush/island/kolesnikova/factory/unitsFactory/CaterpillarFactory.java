package com.javarush.island.kolesnikova.factory.unitsFactory;


import com.javarush.island.kolesnikova.constants.PropertiesUnit;
import com.javarush.island.kolesnikova.entities.units.herbivores.Caterpillar;

public class CaterpillarFactory implements UnitFactory {


    @Override
    public Caterpillar makeUnit() {
        return new Caterpillar(PropertiesUnit.unitsProperties(PropertiesUnit.UnitsName.CATERPILLAR));
    }
}

