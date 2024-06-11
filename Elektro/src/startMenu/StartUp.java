package startMenu;

import music.manager.AudioLoader;
import music.enums.AudioFile;
import utilities.Prompt;

/**
 * Public class for actions related to the start-up (when opening the app).
 */
public class StartUp {
    private StartMenu startMenu = new StartMenu();
    private LoadingScreen loadingScreen = new LoadingScreen();
    public StartUp() {

    }
    /**
     * Load the game.
     * Display the loading screen.
     * First display the dedications from the book authors.
     * Then display the name of the game.
     * Finally, display the main menu.
     */
    public void loadGame(){
        AudioLoader.loadSounds();
        AudioLoader.playSound(AudioFile.MENU);
        Prompt.clearTerminal();

        loadingScreen.setText("\"A todos los que, cada mañana, encuentran fuerzas para enfrentarse al mundo y vencerlo.\" ~ Javier Ruescas");
        loadingScreen.displayWindow();
        loadingScreen.setText("\"A todos aquellos que, como el astronauta que algún día pisará Marte, no deja de soñar.\" ~ Manu Carbajo");
        loadingScreen.displayWindow();
        loadingScreen.setText("Elektro 1: You Can (Not) Redo");
        loadingScreen.displayWindow();

        startMenu.menuAction();
    }
}
