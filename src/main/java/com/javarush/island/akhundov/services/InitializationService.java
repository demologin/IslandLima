package com.javarush.island.akhundov.services;

import com.javarush.island.akhundov.animals.Animal;
import com.javarush.island.akhundov.animals.herbivores.Herbivore;
import com.javarush.island.akhundov.animals.predators.Predator;
import com.javarush.island.akhundov.field.Cell;
import com.javarush.island.akhundov.field.GameField;
import com.javarush.island.akhundov.plants.Grass;
import com.javarush.island.akhundov.plants.Plant;
import com.javarush.island.akhundov.utility.ClassFinder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

public class InitializationService{
    private final GameField gameField;
    private final HashSet<Class<?>> classList;

    public InitializationService(GameField gameField){
        this.gameField = gameField;
        classList = setClassList();
    }

    public void runService(){
            for(int i = 0; i < gameField.getHeight(); i++){
                for(int j = 0; j < gameField.getWidth(); j++){
                    gameField.field[i][j] = new Cell(i,j);
                }
            }

        for(int i = 0; i < gameField.getHeight(); i++){
            for(int j = 0; j < gameField.getWidth(); j++){
                cellAction(gameField.field[i][j]);
            }
        }
    }

    public void cellAction(Cell cell) {
        initializeAnimalMap(cell.getAnimalsMap());
        initializeGrassMap(cell.getPlantsMap());
        initializeDirections(cell);
    }

    private void initializeAnimalMap(HashMap<Class<?>, ArrayList<Animal>> animalsMap){
        for (Class<?> t : classList) {
            ArrayList<Animal> tempList = new ArrayList<>();
            int numberOfAnimals;
            animalsMap.put(t, tempList);
            if (getRandomBoolean()) {
                try {
                    Constructor<?> constructor = t.getConstructor();
                    numberOfAnimals = getRandomNumber((Animal) constructor.newInstance());
                    for (int i = 0; i < numberOfAnimals; i++) {
                        tempList.add((Animal) constructor.newInstance());
                    }
                } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void initializeGrassMap(HashMap<String, ArrayList<Plant>> plantsMap){
        ArrayList<Plant> tempSet = new ArrayList<>();
        plantsMap.put("Grass", tempSet);
        int numberOfGrass = getRandomNumber(new Grass());
        for(int i = 0; i < numberOfGrass; i++){
            tempSet.add(new Grass());
        }
    }

    private  void initializeDirections(Cell cell){
        if(cell.getRow() == 0 && cell.getCol() == 0){
            cell.getDirectionList().add(gameField.getCell(cell.getRow() + 1, cell.getCol()));
            cell.getDirectionList().add(gameField.getCell(cell.getRow(), cell.getCol() + 1));
        }
        else if(cell.getRow() == gameField.getHeight() - 1 && cell.getCol() == gameField.getWidth() - 1){
            cell.getDirectionList().add(gameField.getCell(cell.getRow() - 1, cell.getCol()));
            cell.getDirectionList().add(gameField.getCell(cell.getRow(), cell.getCol() - 1));
        }
        else if(cell.getRow() == gameField.getHeight() - 1 && cell.getCol() == 0){
            cell.getDirectionList().add(gameField.getCell(cell.getRow(), cell.getCol() + 1));
            cell.getDirectionList().add(gameField.getCell(cell.getRow() - 1, cell.getCol()));
        }
        else if(cell.getRow() == 0 && cell.getCol() == gameField.getWidth() - 1){
            cell.getDirectionList().add(gameField.getCell(cell.getRow() + 1, cell.getCol()));
            cell.getDirectionList().add(gameField.getCell(cell.getRow() , cell.getCol() - 1));
        }
        else if(cell.getRow() == 0 && (cell.getCol() > 0 && cell.getCol() < gameField.getWidth() - 1)){
            cell.getDirectionList().add(gameField.getCell(cell.getRow() + 1, cell.getCol()));
            cell.getDirectionList().add(gameField.getCell(cell.getRow() , cell.getCol() + 1));
            cell.getDirectionList().add(gameField.getCell(cell.getRow() , cell.getCol() - 1));
        }
        else if(cell.getRow() == gameField.getHeight() - 1 && (cell.getCol() > 0 && cell.getCol() < gameField.getWidth() - 1)){
            cell.getDirectionList().add(gameField.getCell(cell.getRow() - 1, cell.getCol()));
            cell.getDirectionList().add(gameField.getCell(cell.getRow() , cell.getCol() + 1));
            cell.getDirectionList().add(gameField.getCell(cell.getRow() , cell.getCol() - 1));
        }
        else if((cell.getRow() > 0 && cell.getRow() < gameField.getHeight() - 1) && cell.getCol() == 0 ){
            cell.getDirectionList().add(gameField.getCell(cell.getRow() - 1, cell.getCol()));
            cell.getDirectionList().add(gameField.getCell(cell.getRow() + 1, cell.getCol()));
            cell.getDirectionList().add(gameField.getCell(cell.getRow() , cell.getCol() + 1));
        }
        else if((cell.getRow() > 0 && cell.getRow() < gameField.getHeight() - 1) && cell.getCol() == gameField.getWidth() - 1){
            cell.getDirectionList().add(gameField.getCell(cell.getRow() - 1, cell.getCol()));
            cell.getDirectionList().add(gameField.getCell(cell.getRow() + 1, cell.getCol()));
            cell.getDirectionList().add(gameField.getCell(cell.getRow() , cell.getCol() - 1));
        }
        else{
            cell.getDirectionList().add(gameField.getCell(cell.getRow() - 1, cell.getCol()));
            cell.getDirectionList().add(gameField.getCell(cell.getRow() + 1, cell.getCol()));
            cell.getDirectionList().add(gameField.getCell(cell.getRow() , cell.getCol() - 1));
            cell.getDirectionList().add(gameField.getCell(cell.getRow() , cell.getCol() + 1));
        }
    }

    private int getRandomNumber(Animal animal){
        return ThreadLocalRandom.current().nextInt(animal.getMaxAmountOfAnimal());
        //return ThreadLocalRandom.current().nextInt(10);
    }

    private int getRandomNumber(Grass grass){
        return ThreadLocalRandom.current().nextInt(grass.getMaxAmountOfPlant());
        //return ThreadLocalRandom.current().nextInt(10);
    }

    private boolean getRandomBoolean(){
        return ThreadLocalRandom.current().nextBoolean();
    }

    // Метод для того чтобы получить список всех возможных животных.
    private HashSet<Class<?>> setClassList(){
        HashSet<Class<?>> tempAnimalsSet;
        String HERBIVORES = "com.javarush.island.akhundov.animals.herbivores";
        tempAnimalsSet = ClassFinder.find(HERBIVORES);
        tempAnimalsSet.remove(Herbivore.class);
        String PREDATORS = "com.javarush.island.akhundov.animals.predators";
        tempAnimalsSet.addAll(ClassFinder.find(PREDATORS));
        tempAnimalsSet.remove(Predator.class);

        return tempAnimalsSet;
    }
}


