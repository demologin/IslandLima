package com.javarush.island.uzienko;

import com.javarush.island.uzienko.config.GameConfig;
import com.javarush.island.uzienko.sevices.GodsWillService;
import com.javarush.island.uzienko.sevices.GodsWillServiceImpl;
import com.javarush.island.uzienko.sevices.MainLoopWorker;
import com.javarush.island.uzienko.sevices.StatisticService;
import com.javarush.island.uzienko.storage.Storage;
import com.javarush.island.uzienko.storage.StorageImpl;
import com.javarush.island.uzienko.view.ConsoleGameView;
import com.javarush.island.uzienko.view.GameView;
import com.javarush.island.uzienko.repository.Repository;
import com.javarush.island.uzienko.repository.RepositoryImpl;

public class Runner {
    public static void main(String[] args) {

        GameConfig gameConfig = GameConfig.getInstance();

        Storage storage = new StorageImpl(gameConfig);
        Repository repository = new RepositoryImpl(storage);

        GodsWillService godsWillService = new GodsWillServiceImpl(gameConfig, repository);
        godsWillService.createTheIsland();

        StatisticService statisticService = new StatisticService(gameConfig, repository);

        GameView gameView = new ConsoleGameView(gameConfig, statisticService);

        MainLoopWorker game = new MainLoopWorker(gameView, gameConfig, godsWillService, statisticService);
        game.start();
    }
}
