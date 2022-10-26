package com.javarush.island.ivanilov.workers;

import com.javarush.island.ivanilov.entities.GameInfo;
import com.javarush.island.ivanilov.entities.Stats;
import com.javarush.island.ivanilov.exceptions.IslandGameException;
import com.javarush.island.ivanilov.game.GameField;
import com.javarush.island.ivanilov.utils.Waiter;
import com.javarush.island.ivanilov.workerManagers.*;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

@Getter
@Setter
public class Game implements Runnable {
    private static final int availableProcessors = Runtime.getRuntime().availableProcessors();
    public static final String GAME_NOT_INITIALIZED = "The com.javarush.ivanilov.game hasn't been initialized yet.";

    private volatile GameField gameField;
    private boolean isStopped;
    private boolean isInitialized;
    private int rows;
    private int columns;
    private int iteration;

    public Game(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        iteration = 0;
    }

    @Override
    public void run() {
        this.initialize();
    }

    private void initialize() {
        isStopped = false;
        gameField = new GameField(rows, columns);
        DirectionsManager directionsManager = new DirectionsManager(this);
        managePhase(directionsManager);
        isInitialized = true;
    }

    public GameInfo doIteration() {
        if (isInitialized && !isStopped) {
            try {
                iteration++;
                Manager eatManager = new EatManager(this);
                managePhase(eatManager);
                Manager moveManager = new MoveManager(this);
                managePhase(moveManager);
                Manager reproduceManager = new ReproduceManager(this);
                managePhase(reproduceManager);
                GameInfo gameInfo = formGameInfo(this);
                isStopped = checkStopCriteria(gameInfo);
                return gameInfo;
            } catch (Exception e) {
                throw new IslandGameException(e);
            }
        }
        throw new IslandGameException(GAME_NOT_INITIALIZED);
    }

    private boolean checkStopCriteria(GameInfo gameInfo) {
        Map<Type, Long> numberOfCreatures = gameInfo.getStats().getNumberOfCreatures();
        Set<Type> animalsLeft = new HashSet<>();

        try {
            numberOfCreatures
                    .entrySet()
                    .stream()
                    .filter(e -> gameField.getAnimalBuilder().prototypes.containsKey(e.getKey()))
                    .filter(e -> (e.getValue() > 0))
                    .forEach(e -> animalsLeft.add(e.getKey()));

            return animalsLeft.size() == 0;
        } catch (Exception e) {
            throw new IslandGameException(e);
        }
    }

    private static GameInfo formGameInfo(Game game) {
        CalcManager calcManager = new CalcManager(game);
        ExecutorService calcExecutor = Executors.newFixedThreadPool(availableProcessors);
        Future<Stats> stats = calcExecutor.submit((Callable<Stats>)calcManager);
        Waiter.awaitTermination(calcExecutor);
        GameInfo gameInfo;
        try {
            gameInfo = new GameInfo(stats.get(), game.getGameField());
        } catch (InterruptedException | ExecutionException e) {
            throw new IslandGameException(e);
        }
        return gameInfo;
    }

    private void managePhase(Manager manager) {
        ExecutorService reproduceExecutor = Executors.newFixedThreadPool(availableProcessors);
        reproduceExecutor.submit(manager);
        Waiter.awaitTermination(reproduceExecutor);
    }
}
