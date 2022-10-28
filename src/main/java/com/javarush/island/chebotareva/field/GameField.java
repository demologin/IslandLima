package com.javarush.island.chebotareva.field;

import com.javarush.island.chebotareva.data.Animal;
import com.javarush.island.chebotareva.data.Animals;
import com.javarush.island.chebotareva.data.Inputdata;
import com.javarush.island.chebotareva.thread.ThreadCreation;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class GameField {
    private final Cell[][] field = new Cell[Inputdata.xFieldLength][Inputdata.yFieldWidth];
    Animals allAnimals = new Animals();
    String log = "";


    // заселяем поле животными
    public void initialize() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = new Cell();
                field[i][j].firstAnimalCreate();
            }
        }
    }

    //вывести на экран статистику
    public void showState() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j].countOfAnimal() + " | ");
            }
            System.out.println();
        }
    }

    public void allAnimalAmount() {
        int WolfNumber = 0;
        int SnakeNumber = 0;
        int FoxNumber = 0;
        int BearNumber = 0;
        int EagleNumber = 0;
        int HorseNumber = 0;
        int DeerNumber = 0;
        int RabbitNumber = 0;
        int MouseNumber = 0;
        int GoatNumber = 0;
        int SheepNumber = 0;
        int BoarNumber = 0;
        int BuffaloNumber = 0;
        int DuckNumber = 0;
        int CaterpillarNumber = 0;
        int PlantNumber = 0;

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                for (Map.Entry<String, LinkedList<Animal>> entry : field[i][j].animalSet.entrySet()) {
                    String key = entry.getKey();
                    LinkedList value = entry.getValue();
                    int animalOneTypeNumber = value.size();
                    if (key == "Wolf") {
                        WolfNumber = WolfNumber + animalOneTypeNumber;
                    } else if (key == "Snake") {
                        SnakeNumber = SnakeNumber + animalOneTypeNumber;
                    } else if (key == "Fox") {
                        FoxNumber = FoxNumber + animalOneTypeNumber;
                    } else if (key == "Bear") {
                        BearNumber = BearNumber + animalOneTypeNumber;
                    } else if (key == "Eagle") {
                        EagleNumber = EagleNumber + animalOneTypeNumber;
                    } else if (key == "Horse") {
                        HorseNumber = HorseNumber + animalOneTypeNumber;
                    } else if (key == "Deer") {
                        DeerNumber = DeerNumber + animalOneTypeNumber;
                    } else if (key == "Rabbit") {
                        RabbitNumber = RabbitNumber + animalOneTypeNumber;
                    } else if (key == "Mouse") {
                        MouseNumber = MouseNumber + animalOneTypeNumber;
                    } else if (key == "Goat") {
                        GoatNumber = GoatNumber + animalOneTypeNumber;
                    } else if (key == "Sheep") {
                        SheepNumber = SheepNumber + animalOneTypeNumber;
                    } else if (key == "Boar") {
                        BoarNumber = BoarNumber + animalOneTypeNumber;
                    } else if (key == "Buffalo") {
                        BuffaloNumber = BuffaloNumber + animalOneTypeNumber;
                    } else if (key == "Duck") {
                        DuckNumber = DuckNumber + animalOneTypeNumber;
                    } else if (key == "Caterpillar") {
                        CaterpillarNumber = CaterpillarNumber + animalOneTypeNumber;
                    } else if (key == "Plant") {
                        PlantNumber = PlantNumber + animalOneTypeNumber;
                    }
                }
            }
        }
        System.out.println("Wolf: " + WolfNumber);
        System.out.println("Snake: " + SnakeNumber);
        System.out.println("Fox: " + FoxNumber);
        System.out.println("Bear: " + BearNumber);
        System.out.println("Eagle: " + EagleNumber);
        System.out.println("Horse: " + HorseNumber);
        System.out.println("Deer: " + DeerNumber);
        System.out.println("Rabbit: " + RabbitNumber);
        System.out.println("Mouse: " + MouseNumber);
        System.out.println("Goat: " + GoatNumber);
        System.out.println("Sheep: " + SheepNumber);
        System.out.println("Boar: " + BoarNumber);
        System.out.println("Buffalo: " + BoarNumber);
        System.out.println("Duck: " + BoarNumber);
        System.out.println("Caterpillar: " + BoarNumber);
        System.out.println("Plant: " + BoarNumber);
    }

    public void eat() {
        //
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                //synchronized (field[i][j]) {
                ThreadCreation threadCreation = new ThreadCreation(field[i][j]);
                executorService.submit(threadCreation);
                //}
            }
        }
        executorService.shutdown();
    }

    public void multiply() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                int r = ThreadLocalRandom.current().nextInt(2); // размножаться или нет в текущей ячейке
                if (r >= 0) {
                    for (Map.Entry<String, LinkedList<Animal>> entry : field[i][j].animalSet.entrySet()) {
                        String key = entry.getKey();
                        LinkedList value = entry.getValue();
                        if (value.size() >= 2) {
                            int probabilities = ThreadLocalRandom.current().nextInt(100);
                            if (probabilities > 10) { //увеличить значение вероятности для полно программы
                                value.add(allAnimals.makeAnimalMainSet().get(key));
                            }
                        }
                    }
                }
            }
        }
    }

    public void move() {
        int speed;
        int stepsI = 0;
        int stepsJ = 0;

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                //synchronized (field[i][j]) {
                for (Map.Entry<String, LinkedList<Animal>> entry : field[i][j].animalSet.entrySet()) {
                    LinkedList<Animal> value = entry.getValue();
                    String name = entry.getKey();
                    if (!value.isEmpty()) {
                        int animalgoaway = 0;
                        try {
                            for (Animal animal : value) {

                                speed = animal.getSpeed(); // макс кол-во шагов
                                int r = ThreadLocalRandom.current().nextInt(-1 * speed, speed + 1);
                                int IorJ = ThreadLocalRandom.current().nextInt(2);
                                if (IorJ > 0) {
                                    stepsI = r;
                                } else {
                                    stepsJ = r;
                                }
                                // удаление из списка мапы и добавление в список мапы с новыми координатами
                                if ((i + stepsI) < 0 || (i + stepsI) >= field.length || (j + stepsJ) < 0 || (j + stepsJ) >= field[i].length) {
                                    stepsI = 0;
                                    stepsJ = 0;
                                }
                                if (stepsI != 0 || stepsJ != 0) {
                                    double newWeight = animal.getWeight() - animal.getWeight() / 3;
                                    animal.setWeight(newWeight);
                                    // synchronized (field[i + stepsI][j + stepsJ]) {
                                    if (animal.getWeight() > animal.getMinWeight()) {

                                        field[i + stepsI][j + stepsJ].animalSet.get(name).add(animal);
                                        animalgoaway += 1;
                                    } else {
                                        animalgoaway += 1;
                                    }
                                    //}
                                    log = log + "\n" + name + " moves from map" + (i) + ":" + (j) + " to map" + (i + stepsI) + ":" + (j + stepsJ);
                                }

                            } // заканчиваем цикл перебора животных
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                        for (int k = 0; k < animalgoaway; k++) {
                            if (!value.isEmpty()) {
                                value.remove(0);

                            }
                        }
                    }
                }
                //}
            }
        }
    }
}



