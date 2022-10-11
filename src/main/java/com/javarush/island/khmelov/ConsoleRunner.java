package com.javarush.island.khmelov;

import com.javarush.island.khmelov.entity.Game;
import com.javarush.island.khmelov.entity.map.GameMap;
import com.javarush.island.khmelov.repository.EntityFactory;
import com.javarush.island.khmelov.repository.Factory;
import com.javarush.island.khmelov.repository.GameMapCreator;
import com.javarush.island.khmelov.services.GameWorker;
import com.javarush.island.khmelov.view.ConsoleView;
import com.javarush.island.khmelov.view.View;

public class ConsoleRunner {
    public static void main(String[] args) {
        Factory entityFactory = new EntityFactory();
        GameMapCreator gameMapCreator = new GameMapCreator(entityFactory);
        GameMap gameMap = gameMapCreator.createRandomFilledGameMap(5, 20);
        View view=new ConsoleView(gameMap);
        Game game = new Game(gameMap, entityFactory,view);
        GameWorker gameWorker = new GameWorker(game);
        gameWorker.start();
    }
}
