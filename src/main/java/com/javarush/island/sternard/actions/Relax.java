package com.javarush.island.sternard.actions;

import com.javarush.island.sternard.actions.interfaces.Relaxing;
import com.javarush.island.sternard.map.Cell;
import com.javarush.island.sternard.organisms.parents.Animal;
import com.javarush.island.sternard.settings.Settings;
import com.javarush.island.sternard.utils.Randomizer;

@SuppressWarnings("unused")// reflection api use this class and method
public class Relax implements Relaxing {
    public void action(Animal animal, Cell cell) {

        int relaxChance = Settings.get().getActions().get("relax");
        int randomChanceToRelax = Randomizer.get(100);

        if (relaxChance > randomChanceToRelax) {
            animal.increaseAnimalEnergy(animal);
        }
    }
}
