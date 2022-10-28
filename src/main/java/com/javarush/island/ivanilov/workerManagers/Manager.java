package com.javarush.island.ivanilov.workerManagers;

import com.javarush.island.ivanilov.game.GameField;
import com.javarush.island.ivanilov.workers.Game;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ExecutorService;

@Getter
@Setter
public abstract class Manager implements Runnable {
    public static final int availableProcessors = Runtime.getRuntime().availableProcessors();
    protected ExecutorService executorService;
    protected Game game;
    protected GameField gameField;
}
