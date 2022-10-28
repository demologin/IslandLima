package com.javarush.island.kolesnikova.factory;

import com.javarush.island.kolesnikova.constants.PropertiesUnit;
import com.javarush.island.kolesnikova.entities.units.Unit;
import com.javarush.island.kolesnikova.factory.unitsFactory.*;

import java.util.Map;


public class UnitsFactory {


    private final static Map<PropertiesUnit.UnitsName, UnitFactory> unitsMakerMap = Map.ofEntries(
            Map.entry(PropertiesUnit.UnitsName.WOLF, new WolfFactory()),
            Map.entry(PropertiesUnit.UnitsName.SNAKE, new SnakeFactory()),
            Map.entry(PropertiesUnit.UnitsName.FOX, new FoxFactory()),
            Map.entry(PropertiesUnit.UnitsName.BEAR, new BearFactory()),
            Map.entry(PropertiesUnit.UnitsName.EAGLE, new EagleFactory()),
            Map.entry(PropertiesUnit.UnitsName.HORSE, new HorseFactory()),
            Map.entry(PropertiesUnit.UnitsName.DEER, new DeerFactory()),
            Map.entry(PropertiesUnit.UnitsName.RABBIT, new RabbitFactory()),
            Map.entry(PropertiesUnit.UnitsName.MOUSE, new MouseFactory()),
            Map.entry(PropertiesUnit.UnitsName.GOAT, new GoatFactory()),
            Map.entry(PropertiesUnit.UnitsName.SHEEP, new SheepFactory()),
            Map.entry(PropertiesUnit.UnitsName.HOG, new HogFactory()),
            Map.entry(PropertiesUnit.UnitsName.BUFFALO, new BuffaloFactory()),
            Map.entry(PropertiesUnit.UnitsName.DUCK, new DuckFactory()),
            Map.entry(PropertiesUnit.UnitsName.CATERPILLAR, new CaterpillarFactory()),
            Map.entry(PropertiesUnit.UnitsName.HERB, new HerbFactory()));

    public static Unit getUnit(PropertiesUnit.UnitsName name) {
        UnitFactory unitFactory = unitsMakerMap.get(name);
        return unitFactory.makeUnit();

    }

}
