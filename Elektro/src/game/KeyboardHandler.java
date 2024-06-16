package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;

public class KeyboardHandler implements KeyListener {
    private GamePanel gamePanel;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, escapePressed;
    public static final ArrayList<Integer> devModeSequence = new ArrayList<>(Arrays.asList(KeyEvent.VK_D, KeyEvent.VK_E, KeyEvent.VK_V));
    private ArrayList<Integer> keySequence = new ArrayList<>();
    // Debugging
    boolean checkDrawTime = false;
    public KeyboardHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // Developer mode
        keySequence.add(e.getKeyCode());
        if(keySequence.size() > devModeSequence.size()) {
            keySequence.remove(0);
        }
        if(keySequence.equals(devModeSequence)) {
            if(gamePanel.developerMode){
                System.out.println("Exited developer mode");
                gamePanel.developerMode = false;
            } else {
                System.out.println("Entered developer mode");
                gamePanel.developerMode = true;
            }
        }
        if(gamePanel.developerMode){
            if(gamePanel.gameState == gamePanel.titleState){
                // Skip loading screens
            }
            if(gamePanel.gameState == gamePanel.playState) {
                if(code == KeyEvent.VK_L){
                    // Get current locations
                    System.out.println("Player X: "+(gamePanel.player.worldX/gamePanel.tileSize)+" | Player Y: "+(gamePanel.player.worldY/gamePanel.tileSize));
                    System.out.println("Ema X: "+(gamePanel.npcs[0].worldX/gamePanel.tileSize)+ " | Ema Y: "+(gamePanel.npcs[0].worldY/gamePanel.tileSize));
                }
                if(code == KeyEvent.VK_R){
                    gamePanel.tileManager.loadMap("/maps/house.txt");
                    System.out.println("Reloaded map.");
                }
                if(code == KeyEvent.VK_T){
                    // Debugging
                    if(!checkDrawTime){
                        checkDrawTime = true;
                    } else if(checkDrawTime){
                        checkDrawTime = false;
                    }
                }
            }
        }
        // Title state
        if (gamePanel.gameState == gamePanel.titleState) {
            if(code == KeyEvent.VK_W) {
                gamePanel.ui.commandNum--;
                if(gamePanel.ui.commandNum < 0) {
                    gamePanel.ui.commandNum = 2;
                }
            } else if(code == KeyEvent.VK_S) {
                gamePanel.ui.commandNum++;
                if(gamePanel.ui.commandNum > 2) {
                    gamePanel.ui.commandNum = 0;
                }
            } else if(code == KeyEvent.VK_ENTER) {
                if(gamePanel.ui.commandNum == 0) {
                    //gamePanel.ui.titleScreenState = 1;
                    gamePanel.gameState = gamePanel.playState;
                } else if(gamePanel.ui.commandNum == 1) {
                    // Later
                } else if(gamePanel.ui.commandNum == 2) {
                    System.exit(0);
                }
            }
        // Play state
        } else if(gamePanel.gameState == gamePanel.playState){
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if(code == KeyEvent.VK_ESCAPE) {
                escapePressed = true;
                gamePanel.gameState = gamePanel.pauseState;
            }
            if(code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }

        // Pause state
        } else if(gamePanel.gameState == gamePanel.pauseState){
            if(code == KeyEvent.VK_ESCAPE) {
                gamePanel.gameState = gamePanel.playState;
            }
        // Dialogue state
        } else if(gamePanel.gameState == gamePanel.dialogueState){
            if(code == KeyEvent.VK_ENTER) {
                gamePanel.gameState = gamePanel.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
