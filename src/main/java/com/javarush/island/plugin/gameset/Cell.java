package com.javarush.island.plugin.gameset;

import com.javarush.island.plugin.entity.Animal;
import com.javarush.island.plugin.entity.Factory;
import com.javarush.island.plugin.entity.Organism;
import com.javarush.island.plugin.entity.carnivores.Carnivore;
import com.javarush.island.plugin.entity.carnivores.Wolf;
import com.javarush.island.plugin.entity.herbivores.Herbivore;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Cell {
    int row;
    int col;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Map<Organism, Set<Animal>> sets = new HashMap<>();

    public void calculate() {
        //Логика еды
        eat();

        //Логика передвижения
        move();

        //Логика размножения
        multiply();
    }

    private void eat() {
        for (Map.Entry<Organism, Set<Animal>> pair : sets.entrySet()) {
            Set<Animal> aliveSet = pair.getValue();
            for (Animal alive : aliveSet) {
                if (alive instanceof Carnivore) {
                    Organism[] herbivores = Organism.getHerbivores();
                    for (int i = 0; i < herbivores.length; i++) {
                        ((Carnivore) alive).eat(sets.get(herbivores[i]));
                    }

                }
                else if (alive instanceof Herbivore) {
                    Set<Animal> plants = sets.get(Organism.PLANT);
                    if (plants != null) {
                        ((Herbivore) alive).eat(plants);
                    }
                }
            }
        }
    }

    private void move() {
        for (Map.Entry<Organism, Set<Animal>> pair : sets.entrySet()) {
            Set<Animal> aliveSet = pair.getValue();
            Iterator<Animal> iterator = aliveSet.iterator();
            while (iterator.hasNext()) {
                Animal alive = iterator.next();
                boolean isMove = alive.move(this);
                if (isMove) iterator.remove();
            }
        }
    }

    private void multiply() {
        /*
         Логика проста до безобразия: если каждой твари по паре, то у каждой пары рождается
         по одному детенышу. То есть считаем общее поголовье в одной ячейке и увеличиваем его
         в полтора раза в каждом такте.
        */
        Iterator<Map.Entry<Organism, Set<Animal>>> iterator = sets.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Organism, Set<Animal>> pair = iterator.next();
            Organism name = pair.getKey();
            Set<Animal> animals = pair.getValue();
            if (animals != null) {
                int numberOfChildren = animals.size()/2;
                for (int i = 0; i < numberOfChildren; i++) {
                    Animal animal = Factory.getInstance().getAlive(name);
                    animals.add(animal);
                }
            }
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return "\t|" + row + "/" + col + "|\t";
    }
}
