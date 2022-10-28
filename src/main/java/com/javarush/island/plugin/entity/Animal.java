package com.javarush.island.plugin.entity;

import com.javarush.island.plugin.gameset.Cell;
import com.javarush.island.plugin.gameset.Fields;
import com.javarush.island.plugin.gameset.GameField;
import com.javarush.island.plugin.gameset.GamePreferences;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal {

    private final String name;
    private final String icon;
    private final double startWeight;
    private final int velocity;
    private final double maxWeight;
    private final int maxCount;
    private final int maxSpeed;
    private final double maxFood;

    public Animal(Fields fields) {
        this.name = fields.getName();
        this.icon = fields.getIcon();
        this.startWeight = fields.getStartWeight();
        this.velocity = fields.getVelocity();
        this.maxWeight = fields.getMaxWeight();
        this.maxCount = fields.getMaxCount();
        this.maxSpeed = fields.getMaxSpeed();
        this.maxFood = fields.getMaxFood();
    }
    public boolean move(Cell cell){
        int newRow = cell.getRow() + ThreadLocalRandom.current().nextInt(0,velocity*2) - velocity;//Вычисляем новые координаты нахождения животного.
        int newCol = cell.getCol() + ThreadLocalRandom.current().nextInt(0,velocity*2) - velocity;

        //Вычисляем случайным образом будет ли двигаться животное или останется на месте.
        boolean isMove = ThreadLocalRandom.current().nextBoolean();
        //System.out.println("isMove " + isMove);

        //Если новые координаты выходят за пределы игрового поля, животное остается на месте.
        if (newRow >= GamePreferences.row || newCol >= GamePreferences.col || newRow < 0 || newCol < 0) {
            isMove = false;
        }
        //Если новые координаты совпадают со старыми, животные остаются на месте
        if (newRow == cell.getRow() && newCol == cell.getCol()){
            isMove = false;
        }

        //Перемещаем животное в нужную ячейку
        if (isMove){
            GameField.field[newRow][newCol].sets.get(Organism.valueOf(this.getClass().getSimpleName().toUpperCase())).add(this);
        }
        return isMove;
    }

    @Override
    public String toString() {
        //return name + icon;
        return icon;
    }
}
