package com.javarush.island.akhundov.workers;

import com.javarush.island.akhundov.field.GameField;
import com.javarush.island.akhundov.services.*;
import com.javarush.island.akhundov.utility.Preferences;
import com.javarush.island.akhundov.utility.Printer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameWorker extends Thread{

    public static final int PERIOD = 1000;
    public GameField gameField;
    private final InitializationService initializationService;
    private final MultiplyService multiplyService;
    private final EatingService eatingService;
    private final MoveService moveService;
    private final DyingService dyingService;
    private final Printer printer;


    public GameWorker(GameField gameField){
        this.gameField = gameField;
        Preferences preferences = new Preferences();
        initializationService = new InitializationService(gameField);
        multiplyService = new MultiplyService(gameField);
        eatingService = new EatingService(gameField, preferences);
        moveService = new MoveService(gameField);
        dyingService = new DyingService(gameField);
        printer = new Printer(gameField, preferences);
    }

    @Override
    public void run() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);
        initializationService.runService();

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            ExecutorService servicePool = Executors.newFixedThreadPool(4);
            Worker multiplyWorker = new Worker(multiplyService);
            Worker eatingWorker = new Worker(eatingService);
            Worker moveWorker = new Worker(moveService);
            Worker dyingWorker = new Worker(dyingService);
            //servicePool.submit(multiplyWorker); //Раскомментировать для размножения. Закомментировал чтобы проверить смертность и выход из программы.
            //При большом размножении кладет компьютер на лопатки) Лучше тестить на маленьких цифрах(Клеток поменьше).
            servicePool.submit(eatingWorker);
            servicePool.submit(moveWorker);
            servicePool.submit(dyingWorker);
            servicePool.shutdown();
            try {
                if (servicePool.awaitTermination(PERIOD, TimeUnit.MILLISECONDS)) {
                    boolean isAllDead = printer.printMap();
                    if(isAllDead){
                        scheduledExecutorService.shutdown();
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, PERIOD, PERIOD, TimeUnit.MILLISECONDS);
    }
}
