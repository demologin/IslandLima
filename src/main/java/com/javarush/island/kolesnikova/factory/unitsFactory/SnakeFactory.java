package com.javarush.island.kolesnikova.factory.unitsFactory;


import com.javarush.island.kolesnikova.constants.PropertiesUnit;
import com.javarush.island.kolesnikova.entities.units.carnivorous.Snake;

public class SnakeFactory implements UnitFactory {


    @Override
    public Snake makeUnit() {
        return new Snake(PropertiesUnit.unitsProperties(PropertiesUnit.UnitsName.SNAKE));
    }
}

