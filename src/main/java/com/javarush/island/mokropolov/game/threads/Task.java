package com.javarush.island.mokropolov.game.threads;import com.javarush.island.mokropolov.entity.Entity;import com.javarush.island.mokropolov.entity.animal.Animal;import com.javarush.island.mokropolov.game.map.Cell;public class Task {    private final Entity entity;    private final Cell cell;    public Entity getEntity() {        return entity;    }    public Cell getCell() {        return cell;    }    public Task(Entity entity, Cell cell) {        this.entity = entity;        this.cell = cell;    }    public void execute () {        if (entity instanceof Animal animal) {            animal.eat(cell);            animal.multiply(cell);            animal.move(cell);        } else {            entity.multiply(cell);        }    }}