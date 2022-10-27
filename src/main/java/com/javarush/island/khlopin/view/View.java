package com.javarush.island.khlopin.view;



import com.javarush.island.khlopin.field.MapOfIsland;
import com.javarush.island.khlopin.field.Statistic;

import java.util.Scanner;

public class View {
    private final MapOfIsland mapOfIsland = new MapOfIsland();

    private final Statistic statistic = new Statistic();

    private final Scanner scanner = new Scanner(System.in);


    public void printMap() {
        System.out.println(mapOfIsland.drawMap());
    }

    public void printStatistic() {
        System.out.println(statistic.printState());
    }

    public int printStart() {
        System.out.print("Введите количество тактов жизни симуляции: ");
        return scanner.nextInt();
    }

}
