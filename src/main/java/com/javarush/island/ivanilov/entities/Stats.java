package com.javarush.island.ivanilov.entities;

import com.javarush.island.ivanilov.creatures.Creature;
import com.javarush.island.ivanilov.creatures.Plant;
import com.javarush.island.ivanilov.game.GameField;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;
import java.util.Map;

@Getter
@Setter
public class Stats {
    private int iteration;
    private GameField gameField;
    private Map<Type, Long> numberOfCreatures;

    public Stats(GameField gameField, Map<Type, Long> numberOfCreatures, int iteration) {
        this.gameField = gameField;
        this.numberOfCreatures = numberOfCreatures;
        this.iteration = iteration;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Week #")
                .append(iteration)
                .append(", ")
                .append("population {");
        Map<Type, Object> prototypes = gameField.getAnimalBuilder().getPrototypes();

        for (var type: numberOfCreatures.entrySet()) {
            Creature creature = getCreaturePrototype(prototypes, type);

            stringBuilder.append(" ")
                    .append(creature.getIcon())
                    .append("-")
                    .append(type.getValue())
                    .append(";");
        }

        stringBuilder.append("}");

        return stringBuilder.toString();
    }

    private static Creature getCreaturePrototype(Map<Type, Object> prototypes, Map.Entry<Type, Long> type) {
        Object o = prototypes.get(type.getKey());

        if (o == null)
            return new Plant(0);

        return (Creature) o;
    }
}
