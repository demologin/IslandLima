package com.javarush.island.khmelov.repository;

import com.javarush.island.khmelov.entity.map.Cell;
import com.javarush.island.khmelov.entity.map.GameMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameMapCreatorTest {

    @Test
    void createRandomFilledGameMap() {
        GameMapCreator gameMapCreator = new GameMapCreator(new EntityFactory());
        GameMap randomFilledGameMap = gameMapCreator.createRandomFilledGameMap(4, 4);
        Cell[][] cells = randomFilledGameMap.getCells();
        int[][] expectedDirectionCount = {
                {2, 3, 3, 2},
                {3, 4, 4, 3},
                {3, 4, 4, 3},
                {2, 3, 3, 2},
        };
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Assertions.assertEquals(
                        expectedDirectionCount[i][j],
                        cells[i][j].getNextCellCount()
                );
            }
        }
    }
}