import startMenu.StartUp;

/**
 * Main class to run the program.
 */
public class Main {
    private static StartUp startUp = new StartUp();
    /**
     * Main static class where we load the game.
     * @param args
     */
    public static void main(String[] args) {
        startUp.loadGame();
    }
}
