package com.javarush.island.osypenko.pref;

public class ObjectPrefs {
    private final String name; // Имя животного
    private final String icon; // Иконка животного
    private final double weight; // Вес
    private final int maxNumberOfAnimalsOfThisSpeciesPerCage; // Максимпльное количество животных данного вида в одной клетке
    private final int speedOfMovingCellsMove; // Скорость перемещения по клеткам
    private final double kilogramsOfFoodAnAnimalNeedsForSatiety; // Килограммы пищи необходимые для насыщения
    private final double deathByStarvationAtWeight; // смерть от голода при весе

    public ObjectPrefs(String name, String icon, double weight, int maxNumberOfAnimalsOfThisSpeciesPerCage, int speedOfMovingCellsMove, double kilogramsOfFoodAnAnimalNeedsForSatiety, double deathByStarvationAtWeight) {
        this.name = name;
        this.icon = icon;
        this.weight = weight;
        this.maxNumberOfAnimalsOfThisSpeciesPerCage = maxNumberOfAnimalsOfThisSpeciesPerCage;
        this.speedOfMovingCellsMove = speedOfMovingCellsMove;
        this.kilogramsOfFoodAnAnimalNeedsForSatiety = kilogramsOfFoodAnAnimalNeedsForSatiety;
        this.deathByStarvationAtWeight = deathByStarvationAtWeight;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public double getWeight() {
        return weight;
    }

    public int getMaxNumberOfAnimalsOfThisSpeciesPerCage() {
        return maxNumberOfAnimalsOfThisSpeciesPerCage;
    }

    public int getSpeedOfMovingCellsMove() {
        return speedOfMovingCellsMove;
    }

    public double getKilogramsOfFoodAnAnimalNeedsForSatiety() {
        return kilogramsOfFoodAnAnimalNeedsForSatiety;
    }

    public double getDeathByStarvationAtWeight() {
        return deathByStarvationAtWeight;
    }

    @Override
    public String toString() {
        return "Fields{" +
                "name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", weight=" + weight +
                ", maxNumberOfAnimalsOfThisSpeciesPerCage=" + maxNumberOfAnimalsOfThisSpeciesPerCage +
                ", speedOfMovingCellsMove=" + speedOfMovingCellsMove +
                ", kilogramsOfFoodAnAnimalNeedsForSatiety=" + kilogramsOfFoodAnAnimalNeedsForSatiety +
                '}';
    }
}
