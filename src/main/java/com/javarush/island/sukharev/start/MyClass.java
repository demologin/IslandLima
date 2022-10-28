package com.javarush.island.sukharev.start;

import com.javarush.island.sukharev.factory.Settings;
import com.javarush.island.sukharev.storage.ProgramCommunication;


public class MyClass {

    public static void main(String[] args) {
        final String WELCOME_MESSAGE =
                """
                        *************
                        ** Welcome **
                        *************
                        """;
        System.out.println(WELCOME_MESSAGE);
        ProgramCommunication pc = ProgramCommunication.getProgramCommunication();
        Settings settings = Settings.getSettings();

        System.out.println(ProgramCommunication.LANGUAGE_SELECTION);
        boolean initialLanguageType = true;
        boolean language = settings.flagRegulator(pc, initialLanguageType);
        settings.setUpOrLeave(pc, language);

        pc.gameOver(language);
    }

}
