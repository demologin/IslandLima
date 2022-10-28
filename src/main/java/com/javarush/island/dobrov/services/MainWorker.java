package com.javarush.island.dobrov.services;

import com.javarush.island.dobrov.Animal;
import com.javarush.island.dobrov.Field.Field;
import com.javarush.island.dobrov.Grass.Grass;
import com.javarush.island.dobrov.exceptions.IslandException;
import com.javarush.island.dobrov.herbivores.*;
import com.javarush.island.dobrov.predators.*;
import com.javarush.island.dobrov.utils.Animal_Properties;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class MainWorker {


    private final List<Animal> set = new ArrayList<>();
 //   private final List<GameExecutor> gameExecutors = new ArrayList<>();
    private final Field field;

    public MainWorker(Field field) {
        this.field = field;
        // private static final int nThreads = Runtime.getRuntime().availableProcessors();
        List<Class<? extends Animal>> animalClasses = Arrays.asList(Buffalo.class,
                Bunny.class, Caterpillar.class, Deer.class, Duck.class, Goat.class,
                Hog.class, Horse.class, Mouse.class, Sheep.class, Bear.class, Eagle.class,
                Fox.class, Snake.class, Wolf.class, Grass.class);
        for (Class<? extends Animal> animalClass : animalClasses) {
            if (animalClass.isAnnotationPresent(Animal_Properties.class)) {
                try {

                    Constructor<? extends Animal> constructor = animalClass.getConstructor();

                    Animal animal = constructor.newInstance();

                    set.add(animal);

                } catch (IslandException | InstantiationException | IllegalAccessException |
                         InvocationTargetException | NoSuchMethodException e) {
                    throw new IslandException(e);
                }
            }
        }

    }

    public void go() {

        for (Animal animal : set) {
            new GameExecutor(animal);

        }
        field.showStat();

//        ScheduledExecutorService service = Executors.newScheduledThreadPool(nThreads);
//
//        service.scheduleWithFixedDelay(()->{
//            ExecutorService pool = Executors.newFixedThreadPool(nThreads);
//            gameExecutors.forEach(pool::submit);
//            pool.shutdown();
//
//            try {
//                if(pool.awaitTermination(1,TimeUnit.DAYS)){
//
//                    field.showStat();
//
//                }
//            } catch (InterruptedException e) {
//                throw new IslandException(e);
//            }
//        },0,1,TimeUnit.SECONDS);

    }
}
