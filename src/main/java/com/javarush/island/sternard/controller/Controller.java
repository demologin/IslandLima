package com.javarush.island.sternard.controller;

import com.javarush.island.sternard.exception.HandlerExceptions;
import com.javarush.island.sternard.map.Cell;
import com.javarush.island.sternard.organisms.factory.OrganismFactory;
import com.javarush.island.sternard.organisms.parents.Animal;
import com.javarush.island.sternard.organisms.parents.Organism;
import com.javarush.island.sternard.settings.Settings;
import com.javarush.island.sternard.utils.GameLogger;
import com.javarush.island.sternard.utils.Randomizer;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static com.javarush.island.sternard.constant.lang.English.*;

@Getter
public class Controller {
    public static final long START_TIMER = System.currentTimeMillis();
    private final ExecutorService cellRunExecutor;
    private final int height = Settings.get().getHeightMap();
    private final int width = Settings.get().getWidthMap();
    private final Cell[][] cells;
    private final AtomicInteger DAY_NUMBER = new AtomicInteger(0);
    private static final Map<String, Integer> diedOrganisms = new HashMap<>();
    private static final int corePoolSize = Runtime.getRuntime().availableProcessors();
    public static final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(corePoolSize);

    public Controller() {
        this.cells = new Cell[height][width];
        this.cellRunExecutor = Executors.newWorkStealingPool(corePoolSize);
    }

    public void initGame() {
        int maxOrganismsInCell = Settings.get().getMaxAnimalCountInCell();
        GameLogger.getLog().info(INIT_GAME_START_WITH_ORGANISMS_IN_CELL, maxOrganismsInCell);
        int countOrganisms = 0;
        for (int y = 0; y < this.getHeight(); ++y) {
            for (int x = 0; x < this.getWidth(); ++x) {
                this.cells[y][x] = new Cell(x, y);
                for (int i = 0; i <= Randomizer.get(maxOrganismsInCell); ++i) {
                    Organism randomOrganism = this.getRandomOrganism();
                    int maxOrganismsInCellRandom = Randomizer.get(randomOrganism.getMaxOnCell());
                    for (int n = 0; n < maxOrganismsInCellRandom; n++) {
                        this.cells[y][x].addOrganism(randomOrganism);
                        countOrganisms++;
                    }
                }
            }
            if ((System.currentTimeMillis() - START_TIMER > 4000) && (y == this.getHeight() / 2)) {
                System.out.println(GAME_IS_INITIALIZE_WAIT);
            }
        }
        String initEndText = INIT_TIME_TEXT + ": " + (System.currentTimeMillis() - START_TIMER) + " ms";
        System.out.println(initEndText);
        GameLogger.getLog().info("initGame end. Created " + countOrganisms + " organisms. " + initEndText);
    }

    private Organism getRandomOrganism() {
        Object[] organismClasses = Settings.getClassesOrganisms().keySet().toArray();
        String getOrganismClassName = (String) organismClasses[Randomizer.get(organismClasses.length)];
        return OrganismFactory.createOrganism(getOrganismClassName);
    }

    public void startService(Runnable runnable, long initialDelay, long delay) {
        executorService.scheduleWithFixedDelay(runnable, initialDelay, delay, TimeUnit.MILLISECONDS);
    }

    public synchronized void animalChooseActionAndDoIt(Animal animal, Cell cell) {
        Map<String, Integer> actions = Settings.get().getActions();
        List<String> list = new ArrayList<>(actions.keySet());
        String keyAction = list.get(Randomizer.get(list.size()));
        String simpleClassNameAction = getSimpleClassName(keyAction);
        invokeAction(animal, cell, simpleClassNameAction);
    }

    private void invokeAction(Animal animal, Cell cell, String simpleClassNameAction) {
        try {
            Class<?> actionClassGetFromMap = Settings.getClassesActions().get(simpleClassNameAction);
            Object action = actionClassGetFromMap
                    .getConstructor()
                    .newInstance();

            // TODO need refactoring, but i do not have any idea how...
            if (simpleClassNameAction.equals("Move")) {
                Method method = action.getClass()
                        .getDeclaredMethod("action", Animal.class, Cell.class, Cell[][].class, int.class, int.class);
                method.invoke(action, animal, cell, this.getCells(), this.getHeight(), this.getWidth());
            } else {
                Method method = action.getClass().getDeclaredMethod("action", Animal.class, Cell.class);
                method.invoke(action, animal, cell);
            }
            ////////

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            GameLogger.getLog().error(e.getMessage(), e);
            throw new HandlerExceptions(ERROR_WITH_ACTION, e.getStackTrace());
        }
    }

    public static String getSimpleClassName(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
    }

    public static Map<String, Integer> getDiedOrganisms() {
        return diedOrganisms;
    }

    public static boolean isSameOrganisms(Organism organism, Organism o) {
        return Objects.equals(organism.getClass().getSimpleName(), o.getClass().getSimpleName());
    }

    public void stopSimulation() {
        Controller.executorService.shutdown();
        long timeEndSimulation = System.currentTimeMillis() - START_TIMER;
        GameLogger.getLog().info(SIMULATION_END + timeEndSimulation + " ms");
    }

    public boolean isEndCellService(int currentDay) {
        return currentDay >= Settings.get().getMaxNumberOfDays();
    }

    public static void addToDiedOrganismsMap(Organism organism) {
        String organismIcon = organism.getIcon();
        if (!diedOrganisms.containsKey(organismIcon))
            diedOrganisms.put(organismIcon, 1);
        else
            diedOrganisms.put(organismIcon, diedOrganisms.get(organismIcon) + 1);
    }

    public boolean isDead(Animal animal) {
        int minEnergyToDie = Settings.get().getMinEnergyToDie();
        if (animal.getEnergy() <= minEnergyToDie) {
            addToDiedOrganismsMap(animal);
            return true;
        }
        return false;
    }

    public void statisticCollect(Map<String, Integer> statistics, List<Organism> allOrganisms) {
        for (int y = 0; y < this.getHeight(); ++y) {
            for (int x = 0; x < this.getWidth(); ++x) {
                Cell cell = this.getCells()[y][x];
                allOrganisms.addAll(cell.getOrganisms());
            }
        }
        for (Organism organism : allOrganisms) {
            String organismSimpleName = organism.getClass().getSimpleName();
            if (!statistics.containsKey(organismSimpleName))
                statistics.put(organismSimpleName, 1);
            else
                statistics.put(organismSimpleName, statistics.get(organismSimpleName) + 1);
        }
    }
}
