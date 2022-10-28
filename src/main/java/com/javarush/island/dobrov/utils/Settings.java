package com.javarush.island.dobrov.utils;

public class Settings {
    public static final int[][] probabilities = new int[AnimalType.values().length][AnimalType.values().length];


    public static final int row = 10;
    public static final int col = 10;




    static{

        probabilities[AnimalType.WOLF.ordinal()][AnimalType.WOLF.ordinal()] = 0;
        probabilities[AnimalType.WOLF.ordinal()][AnimalType.SNAKE.ordinal()] = 0;
        probabilities[AnimalType.WOLF.ordinal()][AnimalType.FOX.ordinal()] = 0;
        probabilities[AnimalType.WOLF.ordinal()][AnimalType.BEAR.ordinal()] = 0;
        probabilities[AnimalType.WOLF.ordinal()][AnimalType.EAGLE.ordinal()] = 0;
        probabilities[AnimalType.WOLF.ordinal()][AnimalType.HORSE.ordinal()] = 10;
        probabilities[AnimalType.WOLF.ordinal()][AnimalType.DEER.ordinal()] = 15;
        probabilities[AnimalType.WOLF.ordinal()][AnimalType.BUNNY.ordinal()] = 60;
        probabilities[AnimalType.WOLF.ordinal()][AnimalType.MOUSE.ordinal()] = 80;
        probabilities[AnimalType.WOLF.ordinal()][AnimalType.GOAT.ordinal()] = 60;
        probabilities[AnimalType.WOLF.ordinal()][AnimalType.SHEEP.ordinal()] = 70;
        probabilities[AnimalType.WOLF.ordinal()][AnimalType.HOG.ordinal()] = 15;
        probabilities[AnimalType.WOLF.ordinal()][AnimalType.BUFFALO.ordinal()] = 10;
        probabilities[AnimalType.WOLF.ordinal()][AnimalType.DUCK.ordinal()] = 40;
        probabilities[AnimalType.WOLF.ordinal()][AnimalType.CATERPILLAR.ordinal()] = 0;
        probabilities[AnimalType.WOLF.ordinal()][AnimalType.Grass.ordinal()] = 0;

        probabilities[AnimalType.SNAKE.ordinal()][AnimalType.WOLF.ordinal()] = 0;
        probabilities[AnimalType.SNAKE.ordinal()][AnimalType.SNAKE.ordinal()] = 0;
        probabilities[AnimalType.SNAKE.ordinal()][AnimalType.FOX.ordinal()] = 15;
        probabilities[AnimalType.SNAKE.ordinal()][AnimalType.BEAR.ordinal()] = 0;
        probabilities[AnimalType.SNAKE.ordinal()][AnimalType.EAGLE.ordinal()] = 0;
        probabilities[AnimalType.SNAKE.ordinal()][AnimalType.HORSE.ordinal()] = 0;
        probabilities[AnimalType.SNAKE.ordinal()][AnimalType.DEER.ordinal()] = 0;
        probabilities[AnimalType.SNAKE.ordinal()][AnimalType.BUNNY.ordinal()] = 20;
        probabilities[AnimalType.SNAKE.ordinal()][AnimalType.MOUSE.ordinal()] = 40;
        probabilities[AnimalType.SNAKE.ordinal()][AnimalType.GOAT.ordinal()] = 0;
        probabilities[AnimalType.SNAKE.ordinal()][AnimalType.SHEEP.ordinal()] = 0;
        probabilities[AnimalType.SNAKE.ordinal()][AnimalType.HOG.ordinal()] = 0;
        probabilities[AnimalType.SNAKE.ordinal()][AnimalType.BUFFALO.ordinal()] = 0;
        probabilities[AnimalType.SNAKE.ordinal()][AnimalType.DUCK.ordinal()] = 10;
        probabilities[AnimalType.SNAKE.ordinal()][AnimalType.CATERPILLAR.ordinal()] = 0;
        probabilities[AnimalType.SNAKE.ordinal()][AnimalType.Grass.ordinal()] = 0;

        probabilities[AnimalType.FOX.ordinal()][AnimalType.WOLF.ordinal()] = 0;
        probabilities[AnimalType.FOX.ordinal()][AnimalType.SNAKE.ordinal()] = 0;
        probabilities[AnimalType.FOX.ordinal()][AnimalType.FOX.ordinal()] = 0;
        probabilities[AnimalType.FOX.ordinal()][AnimalType.BEAR.ordinal()] = 0;
        probabilities[AnimalType.FOX.ordinal()][AnimalType.EAGLE.ordinal()] = 0;
        probabilities[AnimalType.FOX.ordinal()][AnimalType.HORSE.ordinal()] = 0;
        probabilities[AnimalType.FOX.ordinal()][AnimalType.DEER.ordinal()] = 0;
        probabilities[AnimalType.FOX.ordinal()][AnimalType.BUNNY.ordinal()] = 70;
        probabilities[AnimalType.FOX.ordinal()][AnimalType.MOUSE.ordinal()] = 90;
        probabilities[AnimalType.FOX.ordinal()][AnimalType.GOAT.ordinal()] = 0;
        probabilities[AnimalType.FOX.ordinal()][AnimalType.SHEEP.ordinal()] = 0;
        probabilities[AnimalType.FOX.ordinal()][AnimalType.HOG.ordinal()] = 0;
        probabilities[AnimalType.FOX.ordinal()][AnimalType.BUFFALO.ordinal()] = 0;
        probabilities[AnimalType.FOX.ordinal()][AnimalType.DUCK.ordinal()] = 60;
        probabilities[AnimalType.FOX.ordinal()][AnimalType.CATERPILLAR.ordinal()] = 40;
        probabilities[AnimalType.FOX.ordinal()][AnimalType.Grass.ordinal()] = 0;

        probabilities[AnimalType.BEAR.ordinal()][AnimalType.WOLF.ordinal()] = 0;
        probabilities[AnimalType.BEAR.ordinal()][AnimalType.SNAKE.ordinal()] = 80;
        probabilities[AnimalType.BEAR.ordinal()][AnimalType.FOX.ordinal()] = 0;
        probabilities[AnimalType.BEAR.ordinal()][AnimalType.BEAR.ordinal()] = 0;
        probabilities[AnimalType.BEAR.ordinal()][AnimalType.EAGLE.ordinal()] = 0;
        probabilities[AnimalType.BEAR.ordinal()][AnimalType.HORSE.ordinal()] = 40;
        probabilities[AnimalType.BEAR.ordinal()][AnimalType.DEER.ordinal()] = 80;
        probabilities[AnimalType.BEAR.ordinal()][AnimalType.BUNNY.ordinal()] = 80;
        probabilities[AnimalType.BEAR.ordinal()][AnimalType.MOUSE.ordinal()] = 90;
        probabilities[AnimalType.BEAR.ordinal()][AnimalType.GOAT.ordinal()] = 70;
        probabilities[AnimalType.BEAR.ordinal()][AnimalType.SHEEP.ordinal()] = 70;
        probabilities[AnimalType.BEAR.ordinal()][AnimalType.HOG.ordinal()] = 50;
        probabilities[AnimalType.BEAR.ordinal()][AnimalType.BUFFALO.ordinal()] = 20;
        probabilities[AnimalType.BEAR.ordinal()][AnimalType.DUCK.ordinal()] = 10;
        probabilities[AnimalType.BEAR.ordinal()][AnimalType.CATERPILLAR.ordinal()] = 0;
        probabilities[AnimalType.BEAR.ordinal()][AnimalType.Grass.ordinal()] = 0;

        probabilities[AnimalType.EAGLE.ordinal()][AnimalType.WOLF.ordinal()] = 0;
        probabilities[AnimalType.EAGLE.ordinal()][AnimalType.SNAKE.ordinal()] = 0;
        probabilities[AnimalType.EAGLE.ordinal()][AnimalType.FOX.ordinal()] = 10;
        probabilities[AnimalType.EAGLE.ordinal()][AnimalType.BEAR.ordinal()] = 0;
        probabilities[AnimalType.EAGLE.ordinal()][AnimalType.EAGLE.ordinal()] = 0;
        probabilities[AnimalType.EAGLE.ordinal()][AnimalType.HORSE.ordinal()] = 0;
        probabilities[AnimalType.EAGLE.ordinal()][AnimalType.DEER.ordinal()] = 0;
        probabilities[AnimalType.EAGLE.ordinal()][AnimalType.BUNNY.ordinal()] = 90;
        probabilities[AnimalType.EAGLE.ordinal()][AnimalType.MOUSE.ordinal()] = 90;
        probabilities[AnimalType.EAGLE.ordinal()][AnimalType.GOAT.ordinal()] = 0;
        probabilities[AnimalType.EAGLE.ordinal()][AnimalType.SHEEP.ordinal()] = 0;
        probabilities[AnimalType.EAGLE.ordinal()][AnimalType.HOG.ordinal()] = 0;
        probabilities[AnimalType.EAGLE.ordinal()][AnimalType.BUFFALO.ordinal()] = 0;
        probabilities[AnimalType.EAGLE.ordinal()][AnimalType.DUCK.ordinal()] = 80;
        probabilities[AnimalType.EAGLE.ordinal()][AnimalType.CATERPILLAR.ordinal()] = 0;
        probabilities[AnimalType.EAGLE.ordinal()][AnimalType.Grass.ordinal()] = 0;

        probabilities[AnimalType.HORSE.ordinal()][AnimalType.WOLF.ordinal()] = 0;
        probabilities[AnimalType.HORSE.ordinal()][AnimalType.SNAKE.ordinal()] = 0;
        probabilities[AnimalType.HORSE.ordinal()][AnimalType.FOX.ordinal()] = 0;
        probabilities[AnimalType.HORSE.ordinal()][AnimalType.BEAR.ordinal()] = 0;
        probabilities[AnimalType.HORSE.ordinal()][AnimalType.EAGLE.ordinal()] = 0;
        probabilities[AnimalType.HORSE.ordinal()][AnimalType.HORSE.ordinal()] = 0;
        probabilities[AnimalType.HORSE.ordinal()][AnimalType.DEER.ordinal()] = 0;
        probabilities[AnimalType.HORSE.ordinal()][AnimalType.BUNNY.ordinal()] = 0;
        probabilities[AnimalType.HORSE.ordinal()][AnimalType.MOUSE.ordinal()] = 0;
        probabilities[AnimalType.HORSE.ordinal()][AnimalType.GOAT.ordinal()] = 0;
        probabilities[AnimalType.HORSE.ordinal()][AnimalType.SHEEP.ordinal()] = 0;
        probabilities[AnimalType.HORSE.ordinal()][AnimalType.HOG.ordinal()] = 0;
        probabilities[AnimalType.HORSE.ordinal()][AnimalType.BUFFALO.ordinal()] = 0;
        probabilities[AnimalType.HORSE.ordinal()][AnimalType.DUCK.ordinal()] = 0;
        probabilities[AnimalType.HORSE.ordinal()][AnimalType.CATERPILLAR.ordinal()] = 0;
        probabilities[AnimalType.HORSE.ordinal()][AnimalType.Grass.ordinal()] = 100;

        probabilities[AnimalType.DEER.ordinal()][AnimalType.WOLF.ordinal()] = 0;
        probabilities[AnimalType.DEER.ordinal()][AnimalType.SNAKE.ordinal()] = 0;
        probabilities[AnimalType.DEER.ordinal()][AnimalType.FOX.ordinal()] = 0;
        probabilities[AnimalType.DEER.ordinal()][AnimalType.BEAR.ordinal()] = 0;
        probabilities[AnimalType.DEER.ordinal()][AnimalType.EAGLE.ordinal()] = 0;
        probabilities[AnimalType.DEER.ordinal()][AnimalType.HORSE.ordinal()] = 0;
        probabilities[AnimalType.DEER.ordinal()][AnimalType.DEER.ordinal()] = 0;
        probabilities[AnimalType.DEER.ordinal()][AnimalType.BUNNY.ordinal()] = 0;
        probabilities[AnimalType.DEER.ordinal()][AnimalType.MOUSE.ordinal()] = 0;
        probabilities[AnimalType.DEER.ordinal()][AnimalType.GOAT.ordinal()] = 0;
        probabilities[AnimalType.DEER.ordinal()][AnimalType.SHEEP.ordinal()] = 0;
        probabilities[AnimalType.DEER.ordinal()][AnimalType.HOG.ordinal()] = 0;
        probabilities[AnimalType.DEER.ordinal()][AnimalType.BUFFALO.ordinal()] = 0;
        probabilities[AnimalType.DEER.ordinal()][AnimalType.DUCK.ordinal()] = 0;
        probabilities[AnimalType.DEER.ordinal()][AnimalType.CATERPILLAR.ordinal()] = 0;
        probabilities[AnimalType.DEER.ordinal()][AnimalType.Grass.ordinal()] = 100;

        probabilities[AnimalType.BUNNY.ordinal()][AnimalType.WOLF.ordinal()] = 0;
        probabilities[AnimalType.BUNNY.ordinal()][AnimalType.SNAKE.ordinal()] = 0;
        probabilities[AnimalType.BUNNY.ordinal()][AnimalType.FOX.ordinal()] = 0;
        probabilities[AnimalType.BUNNY.ordinal()][AnimalType.BEAR.ordinal()] = 0;
        probabilities[AnimalType.BUNNY.ordinal()][AnimalType.EAGLE.ordinal()] = 0;
        probabilities[AnimalType.BUNNY.ordinal()][AnimalType.HORSE.ordinal()] = 0;
        probabilities[AnimalType.BUNNY.ordinal()][AnimalType.DEER.ordinal()] = 0;
        probabilities[AnimalType.BUNNY.ordinal()][AnimalType.BUNNY.ordinal()] = 0;
        probabilities[AnimalType.BUNNY.ordinal()][AnimalType.MOUSE.ordinal()] = 0;
        probabilities[AnimalType.BUNNY.ordinal()][AnimalType.GOAT.ordinal()] = 0;
        probabilities[AnimalType.BUNNY.ordinal()][AnimalType.SHEEP.ordinal()] = 0;
        probabilities[AnimalType.BUNNY.ordinal()][AnimalType.HOG.ordinal()] = 0;
        probabilities[AnimalType.BUNNY.ordinal()][AnimalType.BUFFALO.ordinal()] = 0;
        probabilities[AnimalType.BUNNY.ordinal()][AnimalType.DUCK.ordinal()] = 0;
        probabilities[AnimalType.BUNNY.ordinal()][AnimalType.CATERPILLAR.ordinal()] = 0;
        probabilities[AnimalType.BUNNY.ordinal()][AnimalType.Grass.ordinal()] = 100;

        probabilities[AnimalType.MOUSE.ordinal()][AnimalType.WOLF.ordinal()] = 0;
        probabilities[AnimalType.MOUSE.ordinal()][AnimalType.SNAKE.ordinal()] = 0;
        probabilities[AnimalType.MOUSE.ordinal()][AnimalType.FOX.ordinal()] = 0;
        probabilities[AnimalType.MOUSE.ordinal()][AnimalType.BEAR.ordinal()] = 0;
        probabilities[AnimalType.MOUSE.ordinal()][AnimalType.EAGLE.ordinal()] = 0;
        probabilities[AnimalType.MOUSE.ordinal()][AnimalType.HORSE.ordinal()] = 0;
        probabilities[AnimalType.MOUSE.ordinal()][AnimalType.DEER.ordinal()] = 0;
        probabilities[AnimalType.MOUSE.ordinal()][AnimalType.BUNNY.ordinal()] = 0;
        probabilities[AnimalType.MOUSE.ordinal()][AnimalType.MOUSE.ordinal()] = 0;
        probabilities[AnimalType.MOUSE.ordinal()][AnimalType.GOAT.ordinal()] = 0;
        probabilities[AnimalType.MOUSE.ordinal()][AnimalType.SHEEP.ordinal()] = 0;
        probabilities[AnimalType.MOUSE.ordinal()][AnimalType.HOG.ordinal()] = 0;
        probabilities[AnimalType.MOUSE.ordinal()][AnimalType.BUFFALO.ordinal()] = 0;
        probabilities[AnimalType.MOUSE.ordinal()][AnimalType.DUCK.ordinal()] = 0;
        probabilities[AnimalType.MOUSE.ordinal()][AnimalType.CATERPILLAR.ordinal()] = 90;
        probabilities[AnimalType.MOUSE.ordinal()][AnimalType.Grass.ordinal()] = 100;

        probabilities[AnimalType.GOAT.ordinal()][AnimalType.WOLF.ordinal()] = 0;
        probabilities[AnimalType.GOAT.ordinal()][AnimalType.SNAKE.ordinal()] = 0;
        probabilities[AnimalType.GOAT.ordinal()][AnimalType.FOX.ordinal()] = 0;
        probabilities[AnimalType.GOAT.ordinal()][AnimalType.BEAR.ordinal()] = 0;
        probabilities[AnimalType.GOAT.ordinal()][AnimalType.EAGLE.ordinal()] = 0;
        probabilities[AnimalType.GOAT.ordinal()][AnimalType.HORSE.ordinal()] = 0;
        probabilities[AnimalType.GOAT.ordinal()][AnimalType.DEER.ordinal()] = 0;
        probabilities[AnimalType.GOAT.ordinal()][AnimalType.BUNNY.ordinal()] = 0;
        probabilities[AnimalType.GOAT.ordinal()][AnimalType.MOUSE.ordinal()] = 0;
        probabilities[AnimalType.GOAT.ordinal()][AnimalType.GOAT.ordinal()] = 0;
        probabilities[AnimalType.GOAT.ordinal()][AnimalType.SHEEP.ordinal()] = 0;
        probabilities[AnimalType.GOAT.ordinal()][AnimalType.HOG.ordinal()] = 0;
        probabilities[AnimalType.GOAT.ordinal()][AnimalType.BUFFALO.ordinal()] = 0;
        probabilities[AnimalType.GOAT.ordinal()][AnimalType.DUCK.ordinal()] = 0;
        probabilities[AnimalType.GOAT.ordinal()][AnimalType.CATERPILLAR.ordinal()] = 0;
        probabilities[AnimalType.GOAT.ordinal()][AnimalType.Grass.ordinal()] = 100;

        probabilities[AnimalType.SHEEP.ordinal()][AnimalType.WOLF.ordinal()] = 0;
        probabilities[AnimalType.SHEEP.ordinal()][AnimalType.SNAKE.ordinal()] = 0;
        probabilities[AnimalType.SHEEP.ordinal()][AnimalType.FOX.ordinal()] = 0;
        probabilities[AnimalType.SHEEP.ordinal()][AnimalType.BEAR.ordinal()] = 0;
        probabilities[AnimalType.SHEEP.ordinal()][AnimalType.EAGLE.ordinal()] = 0;
        probabilities[AnimalType.SHEEP.ordinal()][AnimalType.HORSE.ordinal()] = 0;
        probabilities[AnimalType.SHEEP.ordinal()][AnimalType.DEER.ordinal()] = 0;
        probabilities[AnimalType.SHEEP.ordinal()][AnimalType.BUNNY.ordinal()] = 0;
        probabilities[AnimalType.SHEEP.ordinal()][AnimalType.MOUSE.ordinal()] = 0;
        probabilities[AnimalType.SHEEP.ordinal()][AnimalType.GOAT.ordinal()] = 0;
        probabilities[AnimalType.SHEEP.ordinal()][AnimalType.SHEEP.ordinal()] = 0;
        probabilities[AnimalType.SHEEP.ordinal()][AnimalType.HOG.ordinal()] = 0;
        probabilities[AnimalType.SHEEP.ordinal()][AnimalType.BUFFALO.ordinal()] = 0;
        probabilities[AnimalType.SHEEP.ordinal()][AnimalType.DUCK.ordinal()] = 0;
        probabilities[AnimalType.SHEEP.ordinal()][AnimalType.CATERPILLAR.ordinal()] = 0;
        probabilities[AnimalType.SHEEP.ordinal()][AnimalType.Grass.ordinal()] = 100;

        probabilities[AnimalType.HOG.ordinal()][AnimalType.WOLF.ordinal()] = 0;
        probabilities[AnimalType.HOG.ordinal()][AnimalType.SNAKE.ordinal()] = 0;
        probabilities[AnimalType.HOG.ordinal()][AnimalType.FOX.ordinal()] = 0;
        probabilities[AnimalType.HOG.ordinal()][AnimalType.BEAR.ordinal()] = 0;
        probabilities[AnimalType.HOG.ordinal()][AnimalType.EAGLE.ordinal()] = 0;
        probabilities[AnimalType.HOG.ordinal()][AnimalType.HORSE.ordinal()] = 0;
        probabilities[AnimalType.HOG.ordinal()][AnimalType.DEER.ordinal()] = 0;
        probabilities[AnimalType.HOG.ordinal()][AnimalType.BUNNY.ordinal()] = 0;
        probabilities[AnimalType.HOG.ordinal()][AnimalType.MOUSE.ordinal()] = 50;
        probabilities[AnimalType.HOG.ordinal()][AnimalType.GOAT.ordinal()] = 0;
        probabilities[AnimalType.HOG.ordinal()][AnimalType.SHEEP.ordinal()] = 0;
        probabilities[AnimalType.HOG.ordinal()][AnimalType.HOG.ordinal()] = 0;
        probabilities[AnimalType.HOG.ordinal()][AnimalType.BUFFALO.ordinal()] = 0;
        probabilities[AnimalType.HOG.ordinal()][AnimalType.DUCK.ordinal()] = 0;
        probabilities[AnimalType.HOG.ordinal()][AnimalType.CATERPILLAR.ordinal()] = 90;
        probabilities[AnimalType.HOG.ordinal()][AnimalType.Grass.ordinal()] = 100;

        probabilities[AnimalType.BUFFALO.ordinal()][AnimalType.WOLF.ordinal()] = 0;
        probabilities[AnimalType.BUFFALO.ordinal()][AnimalType.SNAKE.ordinal()] = 0;
        probabilities[AnimalType.BUFFALO.ordinal()][AnimalType.FOX.ordinal()] = 0;
        probabilities[AnimalType.BUFFALO.ordinal()][AnimalType.BEAR.ordinal()] = 0;
        probabilities[AnimalType.BUFFALO.ordinal()][AnimalType.EAGLE.ordinal()] = 0;
        probabilities[AnimalType.BUFFALO.ordinal()][AnimalType.HORSE.ordinal()] = 0;
        probabilities[AnimalType.BUFFALO.ordinal()][AnimalType.DEER.ordinal()] = 0;
        probabilities[AnimalType.BUFFALO.ordinal()][AnimalType.BUNNY.ordinal()] = 0;
        probabilities[AnimalType.BUFFALO.ordinal()][AnimalType.MOUSE.ordinal()] = 0;
        probabilities[AnimalType.BUFFALO.ordinal()][AnimalType.GOAT.ordinal()] = 0;
        probabilities[AnimalType.BUFFALO.ordinal()][AnimalType.SHEEP.ordinal()] = 0;
        probabilities[AnimalType.BUFFALO.ordinal()][AnimalType.HOG.ordinal()] = 0;
        probabilities[AnimalType.BUFFALO.ordinal()][AnimalType.BUFFALO.ordinal()] = 0;
        probabilities[AnimalType.BUFFALO.ordinal()][AnimalType.DUCK.ordinal()] = 0;
        probabilities[AnimalType.BUFFALO.ordinal()][AnimalType.CATERPILLAR.ordinal()] = 0;
        probabilities[AnimalType.BUFFALO.ordinal()][AnimalType.Grass.ordinal()] = 100;

        probabilities[AnimalType.DUCK.ordinal()][AnimalType.WOLF.ordinal()] = 0;
        probabilities[AnimalType.DUCK.ordinal()][AnimalType.SNAKE.ordinal()] = 0;
        probabilities[AnimalType.DUCK.ordinal()][AnimalType.FOX.ordinal()] = 0;
        probabilities[AnimalType.DUCK.ordinal()][AnimalType.BEAR.ordinal()] = 0;
        probabilities[AnimalType.DUCK.ordinal()][AnimalType.EAGLE.ordinal()] = 0;
        probabilities[AnimalType.DUCK.ordinal()][AnimalType.HORSE.ordinal()] = 0;
        probabilities[AnimalType.DUCK.ordinal()][AnimalType.DEER.ordinal()] = 0;
        probabilities[AnimalType.DUCK.ordinal()][AnimalType.BUNNY.ordinal()] = 0;
        probabilities[AnimalType.DUCK.ordinal()][AnimalType.MOUSE.ordinal()] = 0;
        probabilities[AnimalType.DUCK.ordinal()][AnimalType.GOAT.ordinal()] = 0;
        probabilities[AnimalType.DUCK.ordinal()][AnimalType.SHEEP.ordinal()] = 0;
        probabilities[AnimalType.DUCK.ordinal()][AnimalType.HOG.ordinal()] = 0;
        probabilities[AnimalType.DUCK.ordinal()][AnimalType.BUFFALO.ordinal()] = 0;
        probabilities[AnimalType.DUCK.ordinal()][AnimalType.DUCK.ordinal()] = 0;
        probabilities[AnimalType.DUCK.ordinal()][AnimalType.CATERPILLAR.ordinal()] = 90;
        probabilities[AnimalType.DUCK.ordinal()][AnimalType.Grass.ordinal()] = 100;

        probabilities[AnimalType.CATERPILLAR.ordinal()][AnimalType.WOLF.ordinal()] = 0;
        probabilities[AnimalType.CATERPILLAR.ordinal()][AnimalType.SNAKE.ordinal()] = 0;
        probabilities[AnimalType.CATERPILLAR.ordinal()][AnimalType.FOX.ordinal()] = 0;
        probabilities[AnimalType.CATERPILLAR.ordinal()][AnimalType.BEAR.ordinal()] = 0;
        probabilities[AnimalType.CATERPILLAR.ordinal()][AnimalType.EAGLE.ordinal()] = 0;
        probabilities[AnimalType.CATERPILLAR.ordinal()][AnimalType.HORSE.ordinal()] = 0;
        probabilities[AnimalType.CATERPILLAR.ordinal()][AnimalType.DEER.ordinal()] = 0;
        probabilities[AnimalType.CATERPILLAR.ordinal()][AnimalType.BUNNY.ordinal()] = 0;
        probabilities[AnimalType.CATERPILLAR.ordinal()][AnimalType.MOUSE.ordinal()] = 0;
        probabilities[AnimalType.CATERPILLAR.ordinal()][AnimalType.GOAT.ordinal()] = 0;
        probabilities[AnimalType.CATERPILLAR.ordinal()][AnimalType.SHEEP.ordinal()] = 0;
        probabilities[AnimalType.CATERPILLAR.ordinal()][AnimalType.HOG.ordinal()] = 0;
        probabilities[AnimalType.CATERPILLAR.ordinal()][AnimalType.BUFFALO.ordinal()] = 0;
        probabilities[AnimalType.CATERPILLAR.ordinal()][AnimalType.DUCK.ordinal()] = 0;
        probabilities[AnimalType.CATERPILLAR.ordinal()][AnimalType.CATERPILLAR.ordinal()] = 0;
        probabilities[AnimalType.CATERPILLAR.ordinal()][AnimalType.Grass.ordinal()] = 100;


    }




}
