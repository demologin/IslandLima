package com.javarush.island.kolesnikova.factory.unitsFactory;


import com.javarush.island.kolesnikova.constants.PropertiesUnit;
import com.javarush.island.kolesnikova.entities.units.herbivores.Hog;

public class HogFactory implements UnitFactory {


    @Override
    public Hog makeUnit() {
        return new Hog(PropertiesUnit.unitsProperties(PropertiesUnit.UnitsName.HOG));
    }
}

