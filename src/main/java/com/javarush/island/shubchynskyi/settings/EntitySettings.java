package com.javarush.island.shubchynskyi.settings;

/**
 *      Contract for new entity
 *
 *  Mandatory variables for entity:
 *          1. The beginning of the variable name must begin with the name of the entity class
 *          2. Entity avatar - must have a unique value
 *
 *  Animal example:
 *      public static final EntityEnums wolfType = EntityEnums.WOLF;
 *      public static final double wolfWeight = 50;
 *      public static final int wolfMaxPerCell = 30;
 *      public static final int wolfSpeed = 3;
 *      public static final double wolfMaxFood = 8;
 *      public static final String wolfAvatar = "\uD83D\uDC3A"
 *
 *  Plant example:
 *      public static final String treeName = "Tree";
 *      public static final EntityEnums treeType = EntityEnums.TREE;
 *      public static final double treeWeight = 20; //20
 *      public static final int treeMaxPerCell = 10; //50 //10
 *      public static final String treeAvatar = "\ud83c\udf32";
 */

public class EntitySettings {
    private EntitySettings() {}

    //* Packages for animals prototype *//
    private static final String HERBIVORES_PACKAGE = "com.javarush.island.shubchynskyi.entity.animals.herbivores";
    private static final String OMNIVORES_PACKAGE = "com.javarush.island.shubchynskyi.entity.animals.omnivores";
    private static final String PREDATORS_PACKAGE = "com.javarush.island.shubchynskyi.entity.animals.predators";
    public static final String[] ANIMAL_PACKAGES = new String[] {HERBIVORES_PACKAGE, OMNIVORES_PACKAGE, PREDATORS_PACKAGE};

    //* Packages for plants prototype *//
    private static final String EVERGREEN_PACKAGE = "com.javarush.island.shubchynskyi.entity.plants.evergreen";
    public static final String[] PLANT_PACKAGES = new String[] {EVERGREEN_PACKAGE};

    public enum EntityEnums {
        WOLF, BOA, FOX, BEAR, EAGLE,
        HORSE, DEER, RABBIT, MOUSE, GOAT, SHEEP, BOAR, BUFFALO, DUCK, CATERPILLAR,
        GRASS, TREE
    }

//************************** Animals **************************//

    //* Wolf *//
    public static final String wolfName = "Wolf";
    public static final EntityEnums wolfType = EntityEnums.WOLF;
    public static final double wolfWeight = 50;
    public static final int wolfMaxPerCell = 30;
    public static final int wolfSpeed = 3;
    public static final double wolfMaxFood = 8;
    public static final String wolfAvatar = "\uD83D\uDC3A";

    //* Boa *//
    public static final String boaName = "Boa";
    public static final EntityEnums boaType = EntityEnums.BOA;
    public static final double boaWeight = 15;
    public static final int boaMaxPerCell = 30;
    public static final int boaSpeed = 1;
    public static final double boaMaxFood = 3;
    public static final String boaAvatar = "\uD83D\uDC0D";

    //* Fox *//
    public static final String foxName = "Fox";
    public static final EntityEnums foxType = EntityEnums.FOX;
    public static final double foxWeight = 8;
    public static final int foxMaxPerCell = 30;
    public static final int foxSpeed = 2;
    public static final double foxMaxFood = 2;
    public static final String foxAvatar = "\uD83E\uDD8A";

    //* Bear *//
    public static final String bearName = "Bear";
    public static final EntityEnums bearType = EntityEnums.BEAR;
    public static final double bearWeight = 500;
    public static final int bearMaxPerCell = 5;
    public static final int bearSpeed = 2;
    public static final double bearMaxFood = 80;
    public static final String bearAvatar = "\uD83D\uDC3B";

    //* Eagle *//
    public static final String eagleName = "Eagle";
    public static final EntityEnums eagleType = EntityEnums.EAGLE;
    public static final double eagleWeight = 6;
    public static final int eagleMaxPerCell = 20;
    public static final int eagleSpeed = 3;
    public static final double eagleMaxFood = 1;
    public static final String eagleAvatar = "\uD83E\uDD85";

    //* Horse *//
    public static final String horseName = "Horse";
    public static final EntityEnums horseType = EntityEnums.HORSE;
    public static final double horseWeight = 400;
    public static final int horseMaxPerCell = 20;
    public static final int horseSpeed = 4;
    public static final double horseMaxFood = 60;
    public static final String horseAvatar = "\uD83D\uDC0E";

    //* Rabbit *//
    public static final String rabbitName = "Rabbit";
    public static final EntityEnums rabbitType = EntityEnums.RABBIT;
    public static final double rabbitWeight = 2;
    public static final int rabbitMaxPerCell = 150;
    public static final int rabbitSpeed = 2;
    public static final double rabbitMaxFood = 0.45d;
    public static final String rabbitAvatar = "\uD83D\uDC07";

    //* Mouse *//
    public static final String mouseName = "Mouse";
    public static final EntityEnums mouseType = EntityEnums.MOUSE;
    public static final double mouseWeight = 0.05d;
    public static final int mouseMaxPerCell = 500;
    public static final int mouseSpeed = 1;
    public static final double mouseMaxFood = 0.01d;
    public static final String mouseAvatar = "\uD83D\uDC01";

    //* Goat *//
    public static final String goatName = "Goat";
    public static final EntityEnums goatType = EntityEnums.GOAT;
    public static final double goatWeight = 60;
    public static final int goatMaxPerCell = 140;
    public static final int goatSpeed = 3;
    public static final double goatMaxFood = 10;
    public static final String goatAvatar = "\uD83D\uDC10";

    //* Sheep *//
    public static final String sheepName = "Sheep";
    public static final EntityEnums sheepType = EntityEnums.SHEEP;
    public static final double sheepWeight = 70;
    public static final int sheepMaxPerCell = 140;
    public static final int sheepSpeed = 3;
    public static final double sheepMaxFood = 15;
    public static final String sheepAvatar = "\uD83D\uDC11";

    //* Boar *//
    public static final String boarName = "Boar";
    public static final EntityEnums boarType = EntityEnums.BOAR;
    public static final double boarWeight = 400;
    public static final int boarMaxPerCell = 50;
    public static final int boarSpeed = 2;
    public static final double boarMaxFood = 50;
    public static final String boarAvatar = "\uD83D\uDC17";

    //* Buffalo *//
    public static final String buffaloName = "Buffalo";
    public static final EntityEnums buffaloType = EntityEnums.BUFFALO;
    public static final double buffaloWeight = 700;
    public static final int buffaloMaxPerCell = 10;
    public static final int buffaloSpeed = 3;
    public static final double buffaloMaxFood = 100;
    public static final String buffaloAvatar = "\uD83D\uDC03";

    //* Duck *//
    public static final String duckName = "Duck";
    public static final EntityEnums duckType = EntityEnums.DUCK;
    public static final double duckWeight = 1;
    public static final int duckMaxPerCell = 200;
    public static final int duckSpeed = 4;
    public static final double duckMaxFood = 0.15d;
    public static final String duckAvatar = "\uD83E\uDD86";

    //* Caterpillar *//
    public static final String caterpillarName = "Caterpillar";
    public static final EntityEnums caterpillarType = EntityEnums.CATERPILLAR;
    public static final double caterpillarWeight = 0.01d;
    public static final int caterpillarMaxPerCell = 1000;
    public static final int caterpillarSpeed = 0;
    public static final double caterpillarMaxFood = 0;
    public static final String caterpillarAvatar = "\uD83D\uDC1B";

    //* Deer *//
    public static final String deerName = "Deer";
    public static final EntityEnums deerType = EntityEnums.DEER;
    public static final double deerWeight = 300;
    public static final int deerMaxPerCell = 20;
    public static final int deerSpeed = 4;
    public static final double deerMaxFood = 50;
    public static final String deerAvatar = "\uD83E\uDD8C";


//************************** Plants **************************//

    //* Grass *//
    public static final String grassName = "Grass";
    public static final EntityEnums grassType = EntityEnums.GRASS;
    public static final double grassWeight = 1;
    public static final int grassMaxPerCell = 200;
    public static final String grassAvatar = "\uD83C\uDF3F";

    //* Tree *//
    public static final String treeName = "Tree";
    public static final EntityEnums treeType = EntityEnums.TREE;
    public static final double treeWeight = 10;
    public static final int treeMaxPerCell = 40;
    public static final String treeAvatar = "\ud83c\udf32";

}
