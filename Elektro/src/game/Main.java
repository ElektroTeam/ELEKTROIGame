package game;

import javax.swing.*;

/**
 * game.Main class to run the program.
 */
public class Main {
    /**
     * game.Main static class where we load the game.
     * @param args
     */
    public static JFrame window;
    public static void main(String[] args) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Elektro I: You Can (Not) Redo");
        //window.setUndecorated(true);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.setUpGame();
        gamePanel.startGameThread();
        //startUp.loadGame();
    }
}
