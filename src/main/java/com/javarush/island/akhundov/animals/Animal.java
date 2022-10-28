package com.javarush.island.akhundov.animals;

import com.javarush.island.akhundov.field.Cell;
import com.javarush.island.akhundov.interfaces.Eatable;
import com.javarush.island.akhundov.interfaces.Organism;
import com.javarush.island.akhundov.utility.Preferences;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal implements Organism {
   protected int maxAmountOfAnimal;

   protected double normalWeight;
   protected double weight;
   protected int speed;
   protected boolean isHungry = false;
   protected double mealAmountToEat;

   public abstract Animal multiply();

   public Cell move(Cell cell){
      int index = ThreadLocalRandom.current().nextInt(cell.getDirectionList().size());
      return cell.getDirectionList().get(index);
   }

   public void loseWeight() {
      weight -= normalWeight/10;
      if (weight <= normalWeight - mealAmountToEat / 2) {
         isHungry = true;
      }
   }

   protected boolean tryToEat(Animal animal, Preferences preferences){
      int percent = preferences.getPercentageMap().get(this.getClass()).get(animal.getClass());
      int random = ThreadLocalRandom.current().nextInt(101);
      return random <= percent;
   }

   public abstract boolean eat(Eatable meal, Preferences preferences);

   public int getSpeed() {
      return speed;
   }
   @Override
   public double getWeight() {
      return weight;
   }

   public void setWeight(double weight) {
      this.weight = weight;
   }

   public double getNormalWeight() {
      return normalWeight;
   }

   public void  setHungry(boolean isHungry){
      this.isHungry = isHungry;
   }

   public boolean getHungry(){
      return  this.isHungry;
   }

   public int getMaxAmountOfAnimal() {
      return maxAmountOfAnimal;
   }
}
