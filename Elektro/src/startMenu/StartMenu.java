package startMenu;

import game.Game;
import game.Settings;
import music.manager.AudioLoader;
import music.enums.AudioFile;
import utilities.Prompt;

/**
 * Public class related to the main menu (start-menu).
 */
public class StartMenu {
    private Credits credits = new Credits();
    private Settings settings = Settings.getInstance();
    private Game game = new Game();
    private Prompt prompt = new Prompt();
    /**
     * Display the menu options.
     */
    public void displayMenu(){
        AudioLoader.playSound(AudioFile.MENU);
        System.out.println("---------------------");
        System.out.println("1. Play");
        System.out.println("2. Settings");
        System.out.println("3. Credits");
        System.out.println("4. Exit");
        System.out.print("> ");
    }
    /**
     * The actions for the main menu.
     * Display the menu options, then ask the user what option they want to do and then do the action they asked to do.
     * The user will stay on the system until they ask to leave the system.
     */
    public void menuAction(){

        boolean keepInSystem = true;
        while(keepInSystem){
            displayMenu();
            switch(prompt.requestInt()){

                case 1:
                    // Call play method
                    //SoundLoader.stopSound(SoundType.MENU);
                    game.loadGame();
                    Prompt.clearTerminal();
                    break;
                case 2:
                    // Settings
                    //SoundLoader.stopSound(SoundType.MENU);
                    settings.displaySettings();
                    Prompt.clearTerminal();
                    break;
                case 3:
                    // Credits
                    AudioLoader.stopSound(AudioFile.MENU);
                    credits.displayCredits();
                    break;
                case 4:
                    // Exit game
                    System.out.println("Closing Application...");
                    System.exit(0);
                    break;
                default:
                    // None of the options listed above
                    System.out.println("Please enter a valid option.");
                    break;
            }
        }
        AudioLoader.closeSound();
    }
}
