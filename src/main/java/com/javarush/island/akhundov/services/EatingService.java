package com.javarush.island.akhundov.services;

import com.javarush.island.akhundov.animals.Animal;
import com.javarush.island.akhundov.animals.herbivores.Herbivore;
import com.javarush.island.akhundov.field.Cell;
import com.javarush.island.akhundov.field.GameField;
import com.javarush.island.akhundov.interfaces.Eatable;
import com.javarush.island.akhundov.interfaces.Organism;
import com.javarush.island.akhundov.plants.Grass;
import com.javarush.island.akhundov.utility.Preferences;

import java.util.ArrayList;
import java.util.Map;

public class EatingService extends AbstractService{

    private final Preferences preferences;

    public EatingService(GameField gameField, Preferences preferences){
        this.gameField = gameField;
        this.preferences = preferences;
    }



    @Override
    public void cellAction(Cell cell) {
        cell.getLock().lock();
        try {
            for (Map.Entry<Class<?>, ArrayList<Animal>> pair : cell.getAnimalsMap().entrySet()) {
                ArrayList<Animal> animalList = new ArrayList<>(pair.getValue());
                for (Animal animal : animalList) {
                    //if(animal.getHungry()) {
                        Eatable meal = findMeal(animal, cell);
                        if (meal != null) {
                            if (animal.eat(meal, preferences)) {
                                animal.setWeight(Math.min(animal.getWeight() + ((Organism) meal).getWeight(), animal.getNormalWeight()));

                                if (meal instanceof Grass) {
                                    cell.getPlantsMap().get("Grass").remove(meal);
                                } else {
                                    cell.getAnimalsMap().get(meal.getClass()).remove(meal); // тут не должно быть проблемы, потому что удаляется из другой части мапы.(Не уверен)
                                }
                                //animal.setHungry(false);
                            }
                        }
                   // }
                        animal.loseWeight();
                }
            }
        }finally {
            cell.getLock().unlock();
        }
    }

    private Eatable findMeal(Animal animal, Cell cell){

        if(animal instanceof Herbivore) {
            if (!cell.getPlantsMap().get("Grass").isEmpty()) {
                return cell.getPlantsMap().get("Grass").get(0); // Если трава не пустая возвращает траву
            }
        }
             if(!preferences.getPercentageMap().get(animal.getClass()).isEmpty()){// Проверяет не пустой ли список возможной еды для животного.
                for(Map.Entry<Class<?>, Integer> pair: preferences.getPercentageMap().get(animal.getClass()).entrySet()){ //пробегаем по списку возможной еды
                    if(!cell.getAnimalsMap().get(pair.getKey()).isEmpty()){ //Если такая еда присутствует на клетке
                        return (Eatable) cell.getAnimalsMap().get(pair.getKey()).get(0);
                    }
                }
            }
            return null;
    }
}
