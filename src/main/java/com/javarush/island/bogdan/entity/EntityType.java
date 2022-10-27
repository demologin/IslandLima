package com.javarush.island.bogdan.entity;

public enum EntityType {
    BEAR("🐻"),
    BOA("🐍"),
    EAGLE("🦅"),
    FOX("🦊"),
    WOLF("🐺"),
    BOAR("🐗"),
    BUFFALO("🐃"),
    CATERPILLAR("🐛"),
    DEER("🦌"),
    DUCK("🦆"),
    GOAT("🐐"),
    HORSE("🐎"),
    MOUSE("🐁"),
    RABBIT("🐇"),
    SHEEP("🐑"),
    PLANTS("🌿");

    EntityType(String icon) {
        this.icon = icon;
    }

    private final String icon;

    public String getIcon() {
        return icon;
    }
}