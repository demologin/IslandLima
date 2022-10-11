package com.javarush.island.khmelov.view;

import com.javarush.island.khmelov.entity.map.GameMap;
import org.junit.jupiter.api.Test;
import com.javarush.island.khmelov.repository.EntityFactory;
import com.javarush.island.khmelov.repository.GameMapCreator;

class ConsoleViewTest {

    @Test
    void showMap() {
        GameMapCreator gameMapCreator = new GameMapCreator(new EntityFactory());
        GameMap map = gameMapCreator.createRandomFilledGameMap(4, 20);
        ConsoleView consoleView = new ConsoleView(map);
        String view = consoleView.showMap();
        String statistics = consoleView.showStatistics();


    }
}