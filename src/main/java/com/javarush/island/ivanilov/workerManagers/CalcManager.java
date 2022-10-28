package com.javarush.island.ivanilov.workerManagers;

import com.javarush.island.ivanilov.entities.Stats;
import com.javarush.island.ivanilov.exceptions.IslandGameException;
import com.javarush.island.ivanilov.utils.Waiter;
import com.javarush.island.ivanilov.workers.CalcWorker;
import com.javarush.island.ivanilov.workers.Game;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Getter
@Setter
public class CalcManager extends Manager implements Callable<Stats> {

    private final Game game;
    private Stats stats;
    public CalcManager(Game game) {
        this.game = game;
        this.gameField = game.getGameField();
        this.executorService = Executors.newFixedThreadPool(availableProcessors);
    }

    @Override
    public void run() {
        stats = call();
    }

    @Override
    public Stats call() {
        Map<Type, Long> numberOfCreatures = new HashMap<>();
        List<Callable<Map<Type, Long>>> callables = new LinkedList<>();

        Arrays.stream(gameField.getRealm())
                .forEach(cells -> Arrays.stream(cells)
                        .forEach(cell -> callables.add(new CalcWorker(cell))));

        List<Future<Map<Type, Long>>> workers = generateFutures(callables);
        executorService.shutdown();

        try{
            for (var future: workers) {
                Map<Type, Long> creaturesInCell = future.get();
                for (var entry: creaturesInCell.entrySet()) {
                    Type type = entry.getKey();
                    Long quantity = entry.getValue();

                    if (numberOfCreatures.get(type) == null) {
                        numberOfCreatures.put(type, quantity);
                    } else {
                        Long oldQuantity = numberOfCreatures.get(type);
                        numberOfCreatures.put(
                                type,
                                oldQuantity + quantity);
                    }
                }
            }
        } catch (Exception e) {
            throw new IslandGameException(e.getCause());
        }
        executorService.shutdown();
        Waiter.awaitTermination(executorService);
        return new Stats(gameField, numberOfCreatures, game.getIteration());
    }

    private List<Future<Map<Type, Long>>> generateFutures(List<Callable<Map<Type, Long>>> callables) {
        try {
            return executorService.invokeAll(callables);
        } catch (InterruptedException e) {
            throw new IslandGameException(e);
        }
    }
}
