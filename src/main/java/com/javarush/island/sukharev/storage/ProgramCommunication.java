package com.javarush.island.sukharev.storage;

public class ProgramCommunication {

    public static int OBJECT_NUMBER = 0;
    public static final String LANGUAGE_SELECTION =
            "Choose language:\nВыберите язык:\nEnglish \t\tpress \t'1'\nРусский \t\tнажать \t'2'";


    public String defaultSettings(boolean language) {

        if (language) {
            return "Customize the program?" + "\n" + notReally(language);

        } else {
            return "Настроить программу?" + "\n" + notReally(language);

        }
    }

    private String notReally(boolean language) {

        if (language) {
            return "if \"Yes\"\t press '1'\nif \"No\"\t\t press '2'";

        } else {
            return "если \"Да\"\t\t нажмите '1'\nесли \"Нет\"\t\t нажмите '2'";

        }
    }



    public static String settingsGameObjects(boolean language) {
        if (language) {

            return "Configure " + nameObject(OBJECT_NUMBER++, language) + " settings:\n" +
                    "Yes \t\tpress '1'\n" +
                    "No \t\t\tpress '2'";

        } else {
            return "Настроить параметры " + nameObject(OBJECT_NUMBER++, language) + ":\n" +
                    "Да \t\t\tнажми '1'\n" +
                    "Нет \t\tнажми '2'";
        }
    }

    private static String nameObject(int objectNumber, boolean language) {
        return switch (objectNumber) {
            case 0 -> language ? "island" : "острова";             //остров
            case 1 -> language ? "wolf" : "волка";             //Волк
            case 2 -> language ? "boa" : "удава";             //Удав
            case 3 -> language ? "fox" : "лисы";              //Лиса
            case 4 -> language ? "bear" : "медведя";            //Медведь
            case 5 -> language ? "eagle" : "орела";             //Орел
            case 6 -> language ? "horse" : "лошади";           //Лошадь
            case 7 -> language ? "deep" : "оленя";          //Олень
            case 8 -> language ? "rabbit" : "кролика";          //Кролик
            case 9 -> language ? "mouse" : "мыши";      //Мышь
            case 10 -> language ? "goat" : "козы";         //Коза
            case 11 -> language ? "sheep" : "овцы";          //Овца
            case 12 -> language ? "boar" : "кабана";         //Кабан
            case 13 -> language ? "buffalo" : "буйвола";         //Буйвол
            case 14 -> language ? "duck" : "утки";         //Утка
            case 15 -> language ? "caterpillar" : "гусеницы";       //Гусеница

            default -> null;
        };
    }

    public String variableNameAnimal(int serialNumber) {
        return switch (serialNumber) {
            case 0 -> "animalWeight = ";
            case 1 -> "maxInCell = ";
            case 2 -> "travelSpeed = ";
            case 3 -> "kilogramsEat = ";
            default -> null;
        };
    }

    public String variableNameIsland(int serialNumber) {
        return switch (serialNumber) {
            case 0 -> "islandLength = ";
            case 1 -> "islandWidth = ";
            default -> null;
        };
    }

    private static ProgramCommunication pc;

    private ProgramCommunication() {
    }

    public static synchronized ProgramCommunication getProgramCommunication() {
        if (pc == null) {
            pc = new ProgramCommunication();
            return pc;
        }
        return pc;
    }

    public String phrasesForNumberVerificationMethod(boolean language) {

        if (language) {
            return "You entered an invalid number";
        } else {
            return "Вы введи не верное число";
        }
    }

    public String phrasesForNumberVerificationMethodException(boolean language) {

        if (language) {
            return "Type a number, not a text";
        } else {
            return "Введите число, а не текст";
        }
    }

    public String gameOver(boolean language) {
        if (language) {
            return """
                     ****************************************
                     ** We are glad that you were with us. **
                     ****************************************
                    """;
        } else {
            return  """
                     **********************************
                     ** Мы рады, что вы были с нами. **
                     **********************************
                    """;
        }
    }

    public String wantToStartTheGame(boolean language) {

        if (language) {
            return "Do you want to start the game?" + "\n" + notReally(language);

        } else {
            return "Хотите начать игру?" + "\n" + notReally(language);

        }
    }

    public String wantToProceedTheGame(boolean language) {

        if (language) {
            return "Do you want to continue the game?" + "\n" + notReally(language);

        } else {
            return "Хотите продолжить игру?" + "\n" + notReally(language);

        }
    }

    public String seeTheCageOfTheIsland(boolean language) {

        if (language) {
            return "Would you like to see what the cage of the island looks like?" + "\n" + notReally(language);

        } else {
            return "Хотите увидеть как выглядит клетка острова?" + "\n" + notReally(language);

        }
    }

    public String seeCellByCoordinates(boolean language) {

        if (language) {
            return "Do you want to see a specific cell? If not, then you will see cells with coordinates (0, 0), (1, 0), (0, 1)." + "\n" + notReally(language);

        } else {
            return "Вы хотите увидеть конкретную клетку? Если нет то вы увидите клетки с координатами (0, 0), (1, 0), (0, 1)." + "\n" + notReally(language);

        }
    }

    public String cellСoordinateШnput(boolean language) {

        if (language) {
            return "Enter cell coordinates. \"X\" and \"Y\"";

        } else {
            return "Введите координаты клетки. \"Х\" и \"Y\"";

        }
    }

    public String coordinateEntryConditions(boolean language) {
        if (language) {
            return "Enter only an integer, if you enter a floating point coordinate it will be rounded up to an integer.";

        } else {
            return "Вводите только целое число, если вы ведёте координату с плавающей точкой то она будет округленное до целого числа.";

        }
    }
}



