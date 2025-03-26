package game;

import enums.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Public class that handles the keyboard.
 */
public class KeyboardHandler implements KeyListener {
    private GamePanel gamePanel;
    private boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, escapePressed;
    private ArrayList<Integer> devModeSequence = new ArrayList<>(Arrays.asList(KeyEvent.VK_D, KeyEvent.VK_E, KeyEvent.VK_V));
    private ArrayList<Integer> secretProjectsSequence = new ArrayList<>(Arrays.asList(KeyEvent.VK_D, KeyEvent.VK_A, KeyEvent.VK_I, KeyEvent.VK_E));
    private ArrayList<Integer> keySequenceDev = new ArrayList<>();
    private ArrayList<Integer> keySequenceSecretProjects = new ArrayList<>();

    // Debugging
    private boolean checkDrawTime = false, drawBattleAreas = false;
    /**
     * Public constructor.
     * @param gamePanel
     */
    public KeyboardHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    /**
     * Activated when a key is pressed.
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // Developer mode
        keySequenceDev.add(e.getKeyCode());
        keySequenceSecretProjects.add(e.getKeyCode());
        if(keySequenceDev.size() > devModeSequence.size()) {
            keySequenceDev.remove(0);
        }
        if(keySequenceSecretProjects.size() > secretProjectsSequence.size()) {
            keySequenceSecretProjects.remove(0);
        }
        if(keySequenceDev.equals(devModeSequence)) {
            if(gamePanel.isDeveloperMode()){
                System.out.println("Exited developer mode");
                gamePanel.setDeveloperMode(false);
            } else {
                System.out.println("Entered developer mode");
                gamePanel.setDeveloperMode(true);
            }
        }
        if(keySequenceSecretProjects.equals(secretProjectsSequence) && (gamePanel.getGameState() == GameState.TITLE_STATE)) {
            System.out.println("Entered secret projects sequence");
            gamePanel.getUi().setCharIndex(0);
            gamePanel.getUi().setCurrentAnimationText("");
            gamePanel.getUi().setCombinedText("");
            gamePanel.setGameState(GameState.SECRET_PROJECTS_SEQUENCE_STATE);
        }
        if(gamePanel.isDeveloperMode()){
            devMode(code);
        }
        if(gamePanel.getGameState() == GameState.SECRET_PROJECTS_SEQUENCE_STATE){
            if(code == KeyEvent.VK_ENTER) {
                gamePanel.getUi().setCharIndex(0);
                gamePanel.getUi().setCurrentAnimationText("");
                gamePanel.getUi().setCombinedText("");
                gamePanel.setGameState(GameState.FIRST_PROJECT_STATE);
                return;
            }
        }
        if(gamePanel.getGameState() == GameState.FIRST_PROJECT_STATE){
            if(code == KeyEvent.VK_ENTER) {
                gamePanel.getUi().setCharIndex(0);
                gamePanel.getUi().setCurrentAnimationText("");
                gamePanel.getUi().setCombinedText("");
                gamePanel.setGameState(GameState.SECOND_PROJECT_STATE);
                return;
            }
        }
        if(gamePanel.getGameState() == GameState.SECOND_PROJECT_STATE){
            if(code == KeyEvent.VK_ENTER) {
                gamePanel.getUi().setCharIndex(0);
                gamePanel.getUi().setCurrentAnimationText("");
                gamePanel.getUi().setCombinedText("");
                gamePanel.setGameState(GameState.THIRD_PROJECT_STATE);
                return;
            }
        }
        if(gamePanel.getGameState() == GameState.THIRD_PROJECT_STATE){
            if(code == KeyEvent.VK_ENTER) {
                gamePanel.getUi().setCharIndex(0);
                gamePanel.getUi().setCurrentAnimationText("");
                gamePanel.getUi().setCombinedText("");
                gamePanel.setGameState(GameState.TO_BE_CONTINUED_STATE);
                return;
            }
        }
        if(gamePanel.getGameState() == GameState.TO_BE_CONTINUED_STATE){
            if(code == KeyEvent.VK_ENTER) {
                gamePanel.getUi().setCharIndex(0);
                gamePanel.getUi().setCurrentAnimationText("");
                gamePanel.getUi().setCombinedText("");
                gamePanel.getUi().setOpacity(0.0f);
                gamePanel.setGameState(GameState.TITLE_STATE);
                return;
            }
        }
        if (gamePanel.getGameState() == GameState.TITLE_STATE) {
            // Title state
            titleState(code);
        } else if(gamePanel.getGameState() == GameState.CHOOSE_LEVEL_STATE){
            // Choose level state
            chooseLevelState(code);
        } else if(gamePanel.getGameState() == GameState.PLAY_STATE){
            // Play state
            playState(code);
        } else if(gamePanel.getGameState() == GameState.PAUSE_STATE){
            // Pause state
            pauseState(code);
        } else if(gamePanel.getGameState() == GameState.DIALOGUE_STATE){
            /// Dialogue state
            dialogueState(code);
        } else if(gamePanel.getGameState() == GameState.OPTIONS_STATE) {
            // Options state
            optionsState(code);
        } else if(gamePanel.getGameState() == GameState.GAME_OVER_STATE){
            // Game over state
            gameOverState(code);
        } else if(gamePanel.getGameState() == GameState.MAP_STATE){
            // Map state
            mapState(code);
        } else if(gamePanel.getGameState() == GameState.CUTSCENE_STATE) {
            // Cutscene state
            cutsceneState(code);
        } else if(gamePanel.getGameState() == GameState.DIARY_STATE){
            // Diary state
            diaryState(code);
        } else if(gamePanel.getGameState() == GameState.MALL_CINEMATIC_STATE){
            // Mall cinematic state
            mallCinematicState(code);
        } else if(gamePanel.getGameState() == GameState.CAVE_CINEMATIC_STATE) {
            // Cave cinematic state
            caveCinematicState(code);
        } else if(gamePanel.getGameState() == GameState.FINAL_CINEMATIC_STATE) {
            // Final cinematic state
            finalCinematicState(code);
        } else if(gamePanel.getGameState() == GameState.FINAL_CINEMATIC_FINISHED_STATE){
            // Final cinematic finished state
            finalCinematicFinishedState(code);
        } else if(gamePanel.getGameState() == GameState.BATTLE_STATE){
            // Battle state
            battleState(code);
        } else if(gamePanel.getGameState() == GameState.CREDITS_STATE){
            // Credits state
            creditsState(code);
        }

    }
    /**
     * Handle the keyboard in dev mode.
     * @param code
     */
    public void devMode(int code){
        if(gamePanel.getGameState() == GameState.TITLE_STATE){
            // Skip loading screens
        } else if(gamePanel.getGameState() == GameState.PLAY_STATE) {
            if(code == KeyEvent.VK_0){
                // Get current locations
                System.out.println("Player X: "+(gamePanel.getPlayer().getWorldX()/gamePanel.getTileSize())+" | Player Y: "+(gamePanel.getPlayer().getWorldY()/gamePanel.getTileSize()));
            }
            if(code == KeyEvent.VK_9){
                gamePanel.getCurrentMap().loadMap();
                gamePanel.drawToTemptScreen();
                gamePanel.drawToScreen();
                System.out.println("Reloaded map.");
            }
            if(code == KeyEvent.VK_8){
                // Debugging
                if(!checkDrawTime){
                    checkDrawTime = true;
                } else if(checkDrawTime){
                    checkDrawTime = false;
                }
            }
            if(code == KeyEvent.VK_7){
                if(drawBattleAreas){
                    drawBattleAreas = false;
                } else{
                    drawBattleAreas = true;
                }
            }
            if(code == KeyEvent.VK_6){
                gamePanel.setGameState(GameState.MAP_STATE);
            }
            if(code == KeyEvent.VK_5){
                gamePanel.setGameState(GameState.DIARY_STATE);
            }
            if(code == KeyEvent.VK_4){
                gamePanel.finishGame();
            }
            if(code == KeyEvent.VK_3){
                gamePanel.getUi().setRemoveLockedDialogue(true);
                gamePanel.setGameState(GameState.CHOOSE_LEVEL_STATE);
            }
            if(code == KeyEvent.VK_O){
                System.out.println("Entered game over state.");
                gamePanel.setGameState(GameState.GAME_OVER_STATE);
            }
            if(code == KeyEvent.VK_K){
                if(gamePanel.getGameState() == GameState.PLAY_STATE){
                    gamePanel.getUi().setCurrentCutsceneCode("ema_found");
                    gamePanel.setGameState(GameState.MALL_CINEMATIC_STATE);
                } else {
                    gamePanel.setGameState(GameState.PLAY_STATE);
                }
            }
            if(code == KeyEvent.VK_J){
                if(gamePanel.getGameState() == GameState.PLAY_STATE){
                    gamePanel.getUi().setCurrentCutsceneCode("cave_final");
                    gamePanel.setGameState(GameState.FINAL_CINEMATIC_STATE);
                } else {
                    gamePanel.setGameState(GameState.PLAY_STATE);
                }
            }
            if(code == KeyEvent.VK_C){
                if(gamePanel.isCheckColissions()){
                    gamePanel.setCheckColissions(false);
                } else {
                    gamePanel.setCheckColissions(true);
                }
            }
            if(code == KeyEvent.VK_B){
                System.out.println("Entered battle state");
                gamePanel.setGameState(GameState.BATTLE_STATE);
            }
        } else if(gamePanel.getGameState() == GameState.GAME_OVER_STATE){
            if(code == KeyEvent.VK_O){
                System.out.println("Exited game over state.");
                gamePanel.setGameState(GameState.PLAY_STATE);
                // Heal(?)
            }
        }
    }
    /**
     * Handle the keyboard in the credits state.
     * @param code
     */
    public void creditsState(int code){
        /*if(code == KeyEvent.VK_ENTER){
            gamePanel.setGameState(GameState.TITLE_STATE);
        }*/
        gamePanel.setGameState(GameState.TITLE_STATE);
    }
    /**
     * Handle the keyboard in the battle state.
     * @param code
     */
    public void battleState(int code){
        /*if(code == KeyEvent.VK_ENTER){
            gamePanel.setGameState(GameState.PLAY_STATE);
        }*/
    }
    /**
     * Handle the keyboard in the mall state.
     * @param code
     */
    public void mallCinematicState(int code){
        if(code == KeyEvent.VK_ENTER){
            gamePanel.getKeyboardHandler().setEnterPressed(true);
        }
    }
    /**
     * Handle the keyboard in the cave cinematic state.
     * @param code
     */
    public void caveCinematicState(int code){
        if(code == KeyEvent.VK_ENTER){
            gamePanel.getKeyboardHandler().setEnterPressed(true);
        }
    }
    /**
     * Handle the keyboard in the final cinematic state.
     * @param code
     */
    public void finalCinematicState(int code){
        if(code == KeyEvent.VK_ENTER){
            gamePanel.getKeyboardHandler().setEnterPressed(true);
        }
    }
    /**
     * Handle the keyboard in the final cinematic (game finished) state.
     * @param code
     */
    public void finalCinematicFinishedState(int code){
        gamePanel.setGameState(GameState.TITLE_STATE);
    }
    /**
     * Handle the keyboard in the diary state.
     * @param code
     */
    public void diaryState(int code){
        if(code == KeyEvent.VK_ESCAPE){
            gamePanel.setGameState(GameState.PLAY_STATE);
        }
        if(code == KeyEvent.VK_A){
            if(gamePanel.getUi().getDiaryPage() > 0){
                gamePanel.getUi().setDiaryPage(gamePanel.getUi().getDiaryPage()-1);
            }
        }
        if(code == KeyEvent.VK_D){
            if(gamePanel.getUi().getDiaryPage() < (gamePanel.getUi().getDiaryEntries().length/2)){
                gamePanel.getUi().setDiaryPage(gamePanel.getUi().getDiaryPage()+1);
            }
        }
    }
    /**
     * Handle the keyboard in the cutscene state.
     * @param code
     */
    public void cutsceneState(int code){
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
    }
    /**
     * Handle the keyboard in the cutscene state.
     * @param code
     */
    public void mapState(int code){
        if(code == KeyEvent.VK_ESCAPE){
            gamePanel.setGameState(GameState.PLAY_STATE);
        }
    }
    /**
     * Handle the keyboard in the game over state.
     * @param code
     */
    public void gameOverState(int code){
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            gamePanel.getUi().setCommandNum(gamePanel.getUi().getCommandNum()-1);
            if(gamePanel.getUi().getCommandNum()<0) {
                gamePanel.getUi().setCommandNum(1);
            }
        } else if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gamePanel.getUi().setCommandNum(gamePanel.getUi().getCommandNum()+1);
            if(gamePanel.getUi().getCommandNum()>1) {
                gamePanel.getUi().setCommandNum(0);
            }
        } else if(code == KeyEvent.VK_ENTER){
            if(gamePanel.getUi().getCommandNum()==0) {
                gamePanel.setGameState(GameState.PLAY_STATE);
                gamePanel.retry();
            } else if(gamePanel.getUi().getCommandNum()==1) {
                gamePanel.setGameState(GameState.TITLE_STATE);
                gamePanel.restart();
            }
        }
    }
    /**
     * Handle the keyboard in the title state.
     * @param code
     */
    public void titleState(int code){
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            gamePanel.getUi().setCommandNum(gamePanel.getUi().getCommandNum()-1);
            if(gamePanel.getUi().getCommandNum()<0) {
                gamePanel.getUi().setCommandNum(2);
            }
        } else if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gamePanel.getUi().setCommandNum(gamePanel.getUi().getCommandNum()+1);
            if(gamePanel.getUi().getCommandNum()>2) {
                gamePanel.getUi().setCommandNum(0);
            }
        } else if(code == KeyEvent.VK_ENTER) {
            if(gamePanel.getUi().getCommandNum()==0) {
                //gamePanel.getUi().titleScreenState = 1;
                gamePanel.getUi().setRemoveLockedDialogue(true);
                gamePanel.setGameState(GameState.CHOOSE_LEVEL_STATE);
            } else if(gamePanel.getUi().getCommandNum()==1){
                // Credits
                gamePanel.setGameState(GameState.CREDITS_STATE);
            } else if(gamePanel.getUi().getCommandNum()==2) {
                System.exit(0);
            }
        }
    }
    /**
     * Handle the keyboard in the choose level state.
     * @param code
     */
    public void chooseLevelState(int code){
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            gamePanel.getUi().setCommandNum(gamePanel.getUi().getCommandNum()-1);
            if(gamePanel.getUi().getCommandNum()<0) {
                gamePanel.getUi().setCommandNum(gamePanel.getLevelList().size());
            }
        } else if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gamePanel.getUi().setCommandNum(gamePanel.getUi().getCommandNum()+1);
            if(gamePanel.getUi().getCommandNum()>gamePanel.getLevelList().size()) {
                gamePanel.getUi().setCommandNum(0);
            }
        } else if(code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
    }
    /**
     * Handle the keyboard in the playing state.
     * @param code
     */
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
        if(code == KeyEvent.VK_1){
            if(gamePanel.getPlayer().isMapFound()){
                gamePanel.setGameState(GameState.MAP_STATE);
            }
        }
        if(code == KeyEvent.VK_2){
            if(gamePanel.getPlayer().isDiaryFound()){
                gamePanel.setGameState(GameState.DIARY_STATE);
            }
        }
        if(code == KeyEvent.VK_ESCAPE) {
            escapePressed = true;
            //gamePanel.setGameState(GameState.PAUSE_STATE);
            gamePanel.setGameState(GameState.OPTIONS_STATE);
        }
        if(code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
    }
    public void dialogueState(int code){
        if(code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
    }
    public void pauseState(int code){
        if(code == KeyEvent.VK_ESCAPE){
            gamePanel.setGameState(GameState.PLAY_STATE);
        }
    }
    /**
     * Handle the keyboard in the options state.
     * @param code
     */
    public void optionsState(int code){
        if(code == KeyEvent.VK_ESCAPE) {
            gamePanel.setGameState(GameState.PLAY_STATE);
            gamePanel.getUi().setCommandNum(0);
            gamePanel.getUi().setSubState(0);
        } else if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        int maxCommandNum = 0;
        switch (gamePanel.getUi().getSubState()){
            case 0:
                maxCommandNum = 5;
                break;
            case 3:
                maxCommandNum = 1;
                break;
        }
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            gamePanel.getUi().setCommandNum(gamePanel.getUi().getCommandNum()-1);
            if(gamePanel.getUi().getCommandNum()<0) {
                gamePanel.getUi().setCommandNum(maxCommandNum);
            }
        } else if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gamePanel.getUi().setCommandNum(gamePanel.getUi().getCommandNum()+1);
            if(gamePanel.getUi().getCommandNum()>maxCommandNum) {
                gamePanel.getUi().setCommandNum(0);
            }
        } else if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            if(gamePanel.getUi().getSubState()==0){
                if((gamePanel.getUi().getCommandNum()==1)&&(gamePanel.getMusic().volumeScale > 0)) {
                    gamePanel.getMusic().volumeScale--;
                    gamePanel.getMusic().checkVolume();
                } else if((gamePanel.getUi().getCommandNum()==2)&&(gamePanel.getSoundEffect().volumeScale > 0)) {
                    gamePanel.getSoundEffect().volumeScale--;
                }
            }
        } else if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            if(gamePanel.getUi().getSubState()==0){
                if((gamePanel.getUi().getCommandNum()==1)&&(gamePanel.getMusic().volumeScale < 5)) {
                    gamePanel.getMusic().volumeScale++;
                    gamePanel.getMusic().checkVolume();
                } else if((gamePanel.getUi().getCommandNum()==2)&&(gamePanel.getSoundEffect().volumeScale < 5)) {
                    gamePanel.getSoundEffect().volumeScale++;
                }
            }
        }
    }
    /**
     * Handle the keyboard when a key is released.
     * @param e the event to be processed
     */
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
    /**
     * Check if the up key is pressed.
     * @return
     */
    public boolean isUpPressed() {return upPressed;}
    /**
     * Set if the up key is pressed or not.
     * @param upPressed
     */
    public void setUpPressed(boolean upPressed) {this.upPressed = upPressed;}
    /**
     * Check if the down key is pressed.
     * @return
     */
    public boolean isDownPressed() {return downPressed;}
    /**
     * Set if the down key is pressed or not.
     * @param downPressed
     */
    public void setDownPressed(boolean downPressed) {this.downPressed = downPressed;}
    /**
     * Check if the left key is pressed.
     * @return
     */
    public boolean isLeftPressed() {return leftPressed;}
    /**
     * Set if the left key is pressed or not.
     * @param leftPressed
     */
    public void setLeftPressed(boolean leftPressed) {this.leftPressed = leftPressed;}
    /**
     * Check if the right key is pressed.
     * @return
     */
    public boolean isRightPressed() {return rightPressed;}
    /**
     * Set if the right key is pressed or not.
     * @param rightPressed
     */
    public void setRightPressed(boolean rightPressed) {this.rightPressed = rightPressed;}
    /**
     * Check if the enter key is pressed.
     * @return
     */
    public boolean isEnterPressed() {return enterPressed;}
    /**
     * Set if the enter key is pressed or not.
     * @param enterPressed
     */
    public void setEnterPressed(boolean enterPressed) {this.enterPressed = enterPressed;}
    /**
     * Check if the escape key is pressed.
     * @return
     */
    public boolean isEscapePressed() {return escapePressed;}
    /**
     * Set if the escape key is pressed or not.
     * @param escapePressed
     */
    public void setEscapePressed(boolean escapePressed) {this.escapePressed = escapePressed;}
    /**
     * Check if draw time is activated or not.
     * @return
     */
    public boolean isCheckDrawTime() {return checkDrawTime;}
    /**
     * Set whether check draw is activated or not.
     * @param checkDrawTime
     */
    public void setCheckDrawTime(boolean checkDrawTime) {this.checkDrawTime = checkDrawTime;}
    /**
     * Check if the developer wants to draw battle areas or not.
     * @return
     */
    public boolean isDrawBattleAreas() {return drawBattleAreas;}
    /**
     * Set whether the developer wants to draw battle areas or not.
     * @param drawBattleAreas
     */
    public void setDrawBattleAreas(boolean drawBattleAreas) {this.drawBattleAreas = drawBattleAreas;}
}
