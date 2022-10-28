package com.javarush.island.kolesnikova.factory.unitsFactory;



import com.javarush.island.kolesnikova.constants.PropertiesUnit;
import com.javarush.island.kolesnikova.entities.units.carnivorous.Eagle;

public class EagleFactory implements UnitFactory {


    @Override
    public Eagle makeUnit() {
        return new Eagle(PropertiesUnit.unitsProperties(PropertiesUnit.UnitsName.EAGLE));
    }
}

