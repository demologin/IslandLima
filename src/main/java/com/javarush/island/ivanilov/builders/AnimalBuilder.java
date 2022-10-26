package com.javarush.island.ivanilov.builders;

import com.javarush.island.ivanilov.creatures.Animal;
import com.javarush.island.ivanilov.exceptions.IslandGameException;
import lombok.Getter;
import org.reflections.Reflections;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.reflections.scanners.Scanners.SubTypes;
import static org.reflections.scanners.Scanners.TypesAnnotated;

@Getter
public class AnimalBuilder {
    public Map<Type, Object> prototypes;

    public AnimalBuilder() {
        this.prototypes = buildPrototypes();
    }

    public Map<Type, Object> buildPrototypes() {
        Map<Type, Object> prototypes = new HashMap<>();
        Reflections reflections = new Reflections(Animal.class);
        Set<Class<?>> allClasses =
                reflections.get(SubTypes.of(TypesAnnotated.with(AnimalScanner.class)).asClass());
        for (var clazz : allClasses) {
            try {
                prototypes.put(clazz, clazz.getConstructor().newInstance());
            } catch (Exception e) {
                throw new IslandGameException(e);
            }
        }
        return prototypes;
    }
}
