package com.javarush.island.kolesnikova.factory.unitsFactory;

import com.javarush.island.kolesnikova.constants.PropertiesUnit;
import com.javarush.island.kolesnikova.entities.units.herbivores.Rabbit;

public class RabbitFactory implements UnitFactory {

    @Override
    public Rabbit makeUnit() {
        return new Rabbit(PropertiesUnit.unitsProperties(PropertiesUnit.UnitsName.RABBIT));
    }
}
