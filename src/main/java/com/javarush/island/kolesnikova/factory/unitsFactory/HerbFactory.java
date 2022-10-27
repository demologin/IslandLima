package com.javarush.island.kolesnikova.factory.unitsFactory;



import com.javarush.island.kolesnikova.constants.PropertiesUnit;
import com.javarush.island.kolesnikova.entities.units.plants.Herb;

public class HerbFactory  implements UnitFactory {


    @Override
    public Herb makeUnit() {
        return new Herb(PropertiesUnit.unitsProperties(PropertiesUnit.UnitsName.HERB));
    }
}

