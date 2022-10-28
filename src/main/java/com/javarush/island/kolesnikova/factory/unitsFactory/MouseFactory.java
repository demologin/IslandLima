package com.javarush.island.kolesnikova.factory.unitsFactory;


import com.javarush.island.kolesnikova.constants.PropertiesUnit;
import com.javarush.island.kolesnikova.entities.units.herbivores.Mouse;

public class MouseFactory implements UnitFactory {


    @Override
    public Mouse makeUnit() {
        return new Mouse(PropertiesUnit.unitsProperties(PropertiesUnit.UnitsName.MOUSE));
    }
}

