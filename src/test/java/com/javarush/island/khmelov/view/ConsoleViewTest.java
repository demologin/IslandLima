package com.javarush.island.khmelov.view;

import com.javarush.island.khmelov.entity.map.GameMap;
import com.javarush.island.khmelov.repository.EntityFactory;
import com.javarush.island.khmelov.repository.GameMapCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConsoleViewTest {

    private ConsoleView consoleView;

    @BeforeEach
    private void setup() {
        GameMapCreator gameMapCreator = new GameMapCreator(new EntityFactory());
        GameMap map = gameMapCreator.createRandomFilledGameMap(4, 20);
        consoleView = new ConsoleView(map);
    }

    @Test
    void showMap() {
        String view = consoleView.showMap();
        String statistics = consoleView.showStatistics();
        Assertions.assertTrue(view.contains("╔"));
        Assertions.assertTrue(view.contains("\n╠"));
        Assertions.assertTrue(view.contains("\n╚"));
        Assertions.assertTrue(view.contains("╗\n"));
        Assertions.assertTrue(view.contains("╣\n"));
        Assertions.assertTrue(view.contains("╝"));
        Assertions.assertTrue(statistics.contains("{"));
        Assertions.assertTrue(statistics.contains("}"));
    }

    @Test
    void getColor() {
        int n = 100;
        for (int i = 0; i <= n; i++) {
            String color = ConsoleView.Color.getColor(i, n);
            System.out.print(color + " " + i + " "+ConsoleView.Color.RESET);
        }
    }
}