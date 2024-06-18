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
                    gamePanel.drawToTemptScreen();
                    gamePanel.drawToScreen();
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
            titleState(code);
        // Play state
        } else if(gamePanel.gameState == gamePanel.playState){
            playState(code);
        // Pause state
        } else if(gamePanel.gameState == gamePanel.pauseState){
            pauseState(code);
        // Dialogue state
        } else if(gamePanel.gameState == gamePanel.dialogueState){
            dialogueState(code);
        // Options state
        } else if(gamePanel.gameState == gamePanel.optionsState){
            optionsState(code);
        }
    }
    public void titleState(int code){
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            gamePanel.ui.commandNum--;
            if(gamePanel.ui.commandNum < 0) {
                gamePanel.ui.commandNum = 2;
            }
        } else if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gamePanel.ui.commandNum++;
            if(gamePanel.ui.commandNum > 2) {
                gamePanel.ui.commandNum = 0;
            }
        } else if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            if(gamePanel.ui.commandNum == 0) {
                //gamePanel.ui.titleScreenState = 1;
                gamePanel.gameState = gamePanel.playState;
                gamePanel.playMusic(0);
            } else if(gamePanel.ui.commandNum == 1) {
                // Later
            } else if(gamePanel.ui.commandNum == 2) {
                System.exit(0);
            }
        }
    }
    public void playState(int code) {
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        if(code == KeyEvent.VK_ESCAPE) {
            escapePressed = true;
            //gamePanel.gameState = gamePanel.pauseState;
            gamePanel.gameState = gamePanel.optionsState;
        }
        if(code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
    }
    public void dialogueState(int code){
        if(code == KeyEvent.VK_ENTER) {
            gamePanel.gameState = gamePanel.playState;
        }
    }
    public void pauseState(int code){
        if(code == KeyEvent.VK_ESCAPE){
            gamePanel.gameState = gamePanel.playState;
        }
    }
    public void optionsState(int code){
        if(code == KeyEvent.VK_ESCAPE) {
            gamePanel.gameState = gamePanel.playState;
        } else if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        int maxCommandNum = 0;
        switch (gamePanel.ui.subState){
            case 0:
                maxCommandNum = 5;
                break;
            case 3:
                maxCommandNum = 1;
                break;
        }
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            gamePanel.ui.commandNum--;
            if(gamePanel.ui.commandNum < 0) {
                gamePanel.ui.commandNum = maxCommandNum;
            }
        } else if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gamePanel.ui.commandNum++;
            if(gamePanel.ui.commandNum > maxCommandNum) {
                gamePanel.ui.commandNum = 0;
            }
        } else if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            if(gamePanel.ui.subState == 0){
                if((gamePanel.ui.commandNum==1)&&(gamePanel.music.volumeScale > 0)) {
                    gamePanel.music.volumeScale--;
                    gamePanel.music.checkVolume();
                } else if((gamePanel.ui.commandNum==2)&&(gamePanel.soundEffect.volumeScale > 0)) {
                    gamePanel.soundEffect.volumeScale--;
                }
            }
        } else if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            if(gamePanel.ui.subState == 0){
                if((gamePanel.ui.commandNum==1)&&(gamePanel.music.volumeScale < 5)) {
                    gamePanel.music.volumeScale++;
                    gamePanel.music.checkVolume();
                } else if((gamePanel.ui.commandNum==2)&&(gamePanel.soundEffect.volumeScale < 5)) {
                    gamePanel.soundEffect.volumeScale++;
                }
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }
}
