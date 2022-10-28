package com.javarush.island.alimin.entity.ecosystem;

import java.util.Set;

public enum Types {
    PLANT("Трава"),
    WOLF("Волк"),
    SNAKE("Змея"),
    FOX("Лиса"),
    BEAR("Медведь"),
    EAGLE("Орел"),
    HORSE("Лошадь"),
    DEER("Олень"),
    RABBIT("Кролик"),
    MOUSE("Мышь"),
    GOAT("Коза"),
    SHEEP("Овца"),
    BOAR("Кабан"),
    BUFFALO("Буйвол"),
    DUCK("Утка"),
    CATERPILLAR("Гусеница");

    private final String name;
    Types(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Set<Types> getHerbivorous() {
        return Set.of(HORSE, RABBIT, MOUSE, DUCK, CATERPILLAR);
    }
}
