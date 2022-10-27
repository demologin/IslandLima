package com.javarush.island.plugin.gameset;

import com.javarush.island.plugin.entity.Animal;
import com.javarush.island.plugin.entity.Factory;
import com.javarush.island.plugin.entity.Organism;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class GameField {
    public static Cell[][] field = new Cell[GamePreferences.row][GamePreferences.col];

    //Инициализируем массив ячеек
    public void initialize() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = new Cell(i, j);
            }
        }
    }

    //Заселяем массив животными и растениями.
    public void setLive(){
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                {
                    Organism[] setOfOrganisms = Organism.values(); //Преобразовываем набор значений енама к обычному массиву
                    for (Organism organism : setOfOrganisms) { //Пробегаемся по массиву и создаем рандомное количество животных каждого вида
                        int num = ThreadLocalRandom.current().nextInt(0, GamePreferences.getInstance().getMap().get(organism).getMaxCount());
                        //int num = ThreadLocalRandom.current().nextInt(0, 3); // Упрощенный вариант для наглядности - мало животных на карте
                        Set<Animal> typeSet = new HashSet<>(); //Создаем сет для необходимого кол-ва животных

                        for (int k = 0; k < num; k++) {
                            Animal alive = Factory.getInstance().getAlive(organism);
                            typeSet.add(alive); //Заполняем его животными нужного вида
                        }
                        field[i][j].sets.put(organism, typeSet);
                    }
                }
            }
        }
    }

    //Выводим на экран состояние ячеек в текущий момент времени.
    public void printInfo(){
        //Этот блок, если нужна детальная статистика по каждой ячейке
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j] + "\t");
                System.out.print(field[i][j].sets);
                System.out.println();
            }
            System.out.println();
        }
        int totalCount = 0;

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                Map<Organism, Set<Animal>> map = field[i][j].sets;
                for (Set<Animal> value : map.values()) {
                    for (Animal animal : value) {
                        totalCount++;
                    }
                }
            }
        }

        System.out.println("Общее количество тварей на карте: " + totalCount);
    }

    //Делаем шаг с каждой итерацией
    public void makeStep(){
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j].calculate();
            }
        }
    }
}
