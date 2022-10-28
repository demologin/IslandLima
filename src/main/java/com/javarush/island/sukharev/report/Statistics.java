package com.javarush.island.sukharev.report;

import com.javarush.island.sukharev.game_objects.animal.abstracts.Animal;
import com.javarush.island.sukharev.game_objects.animal.herbivores.*;
import com.javarush.island.sukharev.game_objects.animal.predatory.*;
import com.javarush.island.sukharev.game_objects.island.Cell;
import com.javarush.island.sukharev.game_objects.island.Island;
import com.javarush.island.sukharev.game_objects.vegetation.abstracts.Vegetation;
import com.javarush.island.sukharev.storage.DataBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Statistics {

    public void printTheCage(Island island, int x, int y) {

        Cell cell = island.findCage(x, y);

        Set<Animal> animalList = island.findCage(x, y).getAnimalSet();
        Set<Vegetation> vegetationList = island.findCage(x, y).getGrassSet();
        System.out.println(cell);

        Stream<String> stream1 = animalList.stream().map(k -> "[" + k.getIcon() + "]");
        Stream<String> stream2 = vegetationList.stream().map(k -> "[" + k.getIcon() + "]");
        List<String> stringList = Stream.concat(stream1, stream2).toList();

        IntStream.rangeClosed(0, stringList.size() - 1)
                .mapToObj(i -> (i % 43 == 0 && i != 0) ? String.format("%s%n", stringList.get(i)) :
                        String.format("%s", stringList.get(i)))
                .forEachOrdered(System.out::print);


    }

    public void countingAnimals(DataBase dataBase, Island island) {
        for (int i = 0; i < dataBase.getIslandWidth(); i++) {
            for (int j = 0; j < dataBase.getIslandLength(); j++) {
                statistics.cellStatistics(island, i, j);
            }
        }
    }

    public void islandStatistics(DataBase dataBase,Island island) {
        List<Wolf> wolfList = new ArrayList<>();
        List<Fox> foxList = new ArrayList<>();
        List<Eagle> eagleList = new ArrayList<>();
        List<Boa> boaList = new ArrayList<>();
        List<Bear> bearList = new ArrayList<>();
        List<Sheep> sheepList = new ArrayList<>();
        List<Rabbit> rabbitList = new ArrayList<>();
        List<Mouse> mouseList = new ArrayList<>();
        List<Horse> horseList = new ArrayList<>();
        List<Goat> goatList = new ArrayList<>();
        List<Duck> duckList = new ArrayList<>();
        List<Deer> deerList = new ArrayList<>();
        List<Caterpillar> caterpillarList = new ArrayList<>();
        List<Buffalo> buffaloList = new ArrayList<>();
        List<Boar> boarList = new ArrayList<>();
        Set<Vegetation> vegetationSet = island.findCage(0, 0).getGrassSet();

        for (int i = 0; i < dataBase.getIslandWidth(); i++) {
            for (int j = 0; j < dataBase.getIslandLength(); j++) {
                whoAreYou(island, i, j, wolfList, foxList, eagleList, boaList, bearList, sheepList, rabbitList, mouseList,
                        horseList, goatList, duckList, deerList, caterpillarList, buffaloList, boarList);
            }
        }



        print(100, 100, wolfList, foxList, eagleList, boaList, bearList, sheepList, rabbitList, mouseList, horseList,
                goatList, duckList, deerList, caterpillarList, buffaloList, boarList, vegetationSet);

    }

    public void cellStatistics(Island island, int x, int y) {
        List<Wolf> wolfList = new ArrayList<>();
        List<Fox> foxList = new ArrayList<>();
        List<Eagle> eagleList = new ArrayList<>();
        List<Boa> boaList = new ArrayList<>();
        List<Bear> bearList = new ArrayList<>();
        List<Sheep> sheepList = new ArrayList<>();
        List<Rabbit> rabbitList = new ArrayList<>();
        List<Mouse> mouseList = new ArrayList<>();
        List<Horse> horseList = new ArrayList<>();
        List<Goat> goatList = new ArrayList<>();
        List<Duck> duckList = new ArrayList<>();
        List<Deer> deerList = new ArrayList<>();
        List<Caterpillar> caterpillarList = new ArrayList<>();
        List<Buffalo> buffaloList = new ArrayList<>();
        List<Boar> boarList = new ArrayList<>();
        Set<Vegetation> vegetationSet = island.findCage(x, y).getGrassSet();

        whoAreYou(island, x, y, wolfList, foxList, eagleList, boaList, bearList, sheepList, rabbitList, mouseList,
                horseList, goatList, duckList, deerList, caterpillarList, buffaloList, boarList);

        print(x, y, wolfList, foxList, eagleList, boaList, bearList, sheepList, rabbitList, mouseList, horseList,
                goatList, duckList, deerList, caterpillarList, buffaloList, boarList, vegetationSet);

    }

    private void print(int x, int y, List<Wolf> wolfList, List<Fox> foxList, List<Eagle> eagleList, List<Boa> boaList,
                              List<Bear> bearList, List<Sheep> sheepList, List<Rabbit> rabbitList, List<Mouse> mouseList,
                              List<Horse> horseList, List<Goat> goatList, List<Duck> duckList, List<Deer> deerList,
                              List<Caterpillar> caterpillarList, List<Buffalo> buffaloList, List<Boar> boarList,
                              Set<Vegetation> vegetationSet) {
        String wolfIcon;
        String boaIcon;
        String foxIcon;
        String bearIcon;
        String eagleIcon;
        String horseIcon;
        String deerIcon;
        String rabbitIcon;
        String mouseIcon;
        String goatIcon;
        String sheepIcon;
        String boarIcon;
        String buffaloIcon;
        String duckIcon;
        String caterpillarIcon;
        String vegetationIcon = null;

        String coordinates = "X = " + x + " Y = " + y;
        if (!wolfList.isEmpty()) {
            wolfIcon = wolfList.get(0).getIcon() + " = " + wolfList.size();
        } else {
            wolfIcon = "\uD83D\uDC3A = 0";
        }

        if (!boaList.isEmpty()) {
            boaIcon = boaList.get(0).getIcon() + " = " + boaList.size();
        } else {
            boaIcon = "\uD83D\uDC0D = 0";
        }

        if (!foxList.isEmpty()) {
            foxIcon = foxList.get(0).getIcon() + " = " + foxList.size();
        } else {
            foxIcon = "\uD83E\uDD8A = 0";
        }

        if (!bearList.isEmpty()) {
            bearIcon = bearList.get(0).getIcon() + " = " + bearList.size();
        } else {
            bearIcon = "\uD83D\uDC3B = 0";
        }

        if (!eagleList.isEmpty()) {
            eagleIcon = eagleList.get(0).getIcon() + " = " + eagleList.size();
        } else {
            eagleIcon = "\uD83E\uDD85 = 0";
        }

        if (!horseList.isEmpty()) {
            horseIcon = horseList.get(0).getIcon() + " = " + horseList.size();
        } else {
            horseIcon = "\uD83D\uDC0E = 0";
        }

        if (!deerList.isEmpty()) {
            deerIcon = deerList.get(0).getIcon() + " = " + deerList.size();
        } else {
            deerIcon = "\uD83E\uDD8C = 0";
        }

        if (!rabbitList.isEmpty()) {
            rabbitIcon = rabbitList.get(0).getIcon() + " = " + rabbitList.size();
        } else {
            rabbitIcon = "\uD83D\uDC07 = 0";
        }

        if (!mouseList.isEmpty()) {
            mouseIcon = mouseList.get(0).getIcon() + " = " + mouseList.size();
        } else {
            mouseIcon = "\uD83D\uDC01 = 0";
        }

        if (!goatList.isEmpty()) {
            goatIcon = goatList.get(0).getIcon() + " = " + goatList.size();
        } else {
            goatIcon = "\uD83D\uDC10 = 0";
        }

        if (!sheepList.isEmpty()) {
            sheepIcon = sheepList.get(0).getIcon() + " = " + sheepList.size();
        } else {
            sheepIcon = "\uD83D\uDC11 = 0";
        }

        if (!boarList.isEmpty()) {
            boarIcon = boarList.get(0).getIcon() + " = " + boarList.size();
        } else {
            boarIcon = "\uD83D\uDC17 = 0";
        }

        if (!buffaloList.isEmpty()) {
            buffaloIcon = buffaloList.get(0).getIcon() + " = " + buffaloList.size();
        } else {
            buffaloIcon = "\uD83D\uDC03 = 0";
        }

        if (!duckList.isEmpty()) {
            duckIcon = duckList.get(0).getIcon() + " = " + duckList.size();
        } else {
            duckIcon = "\uD83E\uDD86 = 0";
        }

        if (!caterpillarList.isEmpty()) {
            caterpillarIcon = caterpillarList.get(0).getIcon() + " = " + caterpillarList.size();
        } else {
            caterpillarIcon = "\uD83D\uDC1B = 0";
        }

        if (!vegetationSet.isEmpty()) {
            for (Vegetation vegetation : vegetationSet) {
                vegetationIcon = vegetation.getIcon() + " = " + vegetationSet.size();
            }

        } else {
            vegetationIcon = "\uD83C\uDF3F = 0";
        }

        System.out.println();

        System.out.printf("| %12s || %7s || %7s || %7s || %5s || %7s || %7s || %7s || %9s || %9s || %9s || %9s || %7s || %6s || %8s || %7s || %7s |",
                coordinates, wolfIcon, boaIcon, foxIcon, bearIcon, eagleIcon, horseIcon, deerIcon, rabbitIcon, mouseIcon, goatIcon, sheepIcon, boarIcon, buffaloIcon, duckIcon, caterpillarIcon, vegetationIcon);
    }

    private void whoAreYou(Island island, int x, int y, List<Wolf> wolfList, List<Fox> foxList, List<Eagle> eagleList,
                           List<Boa> boaList, List<Bear> bearList, List<Sheep> sheepList, List<Rabbit> rabbitList,
                           List<Mouse> mouseList, List<Horse> horseList, List<Goat> goatList, List<Duck> duckList,
                           List<Deer> deerList, List<Caterpillar> caterpillarList, List<Buffalo> buffaloList, List<Boar> boarList) {

        for (Animal a : island.findCage(x, y).getAnimalSet()) {
            switch (a.getId()) {
                case 1 -> wolfList.add((Wolf) a);                           //Волк
                case 2 -> boaList.add((Boa) a);                             //Удав
                case 3 -> foxList.add((Fox) a);                             //Лиса
                case 4 -> bearList.add((Bear) a);                           //Медведь
                case 5 -> eagleList.add((Eagle) a);                         //Орел
                case 6 -> horseList.add((Horse) a);                         //Лошадь
                case 7 -> deerList.add((Deer) a);                           //Олень
                case 8 -> rabbitList.add((Rabbit) a);                       //Кролик
                case 9 -> mouseList.add((Mouse) a);                         //Мышь
                case 10 -> goatList.add((Goat) a);                          //Коза
                case 11 -> sheepList.add((Sheep) a);                        //Овца
                case 12 -> boarList.add((Boar) a);                          //Кабан
                case 13 -> buffaloList.add((Buffalo) a);                    //Буйвол
                case 14 -> duckList.add((Duck) a);                          //Утка
                case 15 -> caterpillarList.add((Caterpillar) a);             //Гусеница
                default -> {
                }
            }
        }
    }

    private static Statistics statistics;

    private Statistics() {
    }

    public static synchronized Statistics getStatistics() {
        if (statistics == null) {
            statistics = new Statistics();
            return statistics;
        }
        return statistics;
    }
}
