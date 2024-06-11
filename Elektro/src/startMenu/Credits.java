package startMenu;

import music.manager.AudioLoader;
import music.enums.AudioFile;
import utilities.Prompt;

/**
 * Public class for actions related to the Credits section.
 */
public class Credits {
    /**
     * Display the credits.
     * Displays the developers of the game and special credits/gratitude.
     */
    public void displayCredits(){
        AudioLoader.playSound(AudioFile.CREDITS);
        System.out.println("""
                  _______ _______ _______ _______ _______ _______ ______
                 /       |       |       |       |       |       |      \\
                |   C    |   R   |   E   |   D   |   I   |   T   |   S   |
                |________|_______|_______|_______|_______|_______|_______|
                |________|_______|_______|_______|_______|_______|_______|
                """);
        System.out.println("""
                \t\t\t\t\t\tDevelopers
                \t\t\t--------------------------------
                \t\t\t\tGameplay:
                \t\t\t\t  - Diego Iraheta
                \t\t\t\tArt designer:
                \t\t\t\t  - Isaac Tovar
                \t\t\t\tLevels, maps and lore:
                \t\t\t\t  - Alexander Morales
                \t\t\t\tMusic and audio effects:
                \t\t\t\t  - Abraham Flores
                """);
        System.out.println("""
               \t\t\t\t\tSpecial thanks to
               \t\t\t--------------------------------
               \t\t\t\t- Manu Carbajo (Book writer)
               \t\t\t\t- Javier Ruescas (Book writer)
               \t\t\t\t- Manuel Madriz (Instructor)
               \t\t\t\t- Ing. Enmanuel Araujo
               \t\t\t\t- Ing. Mario Lopez
               \t\t\t\t- Ing. Marlene Aguilar
               \t\t\t\t""");
        Prompt.promptEnterToContinue();
        AudioLoader.stopSound(AudioFile.CREDITS);
        Prompt.clearTerminal();
    }
}
