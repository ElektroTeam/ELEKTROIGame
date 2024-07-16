package utilities;

import battle.Battle;
import battle.MapBattle;
import entities.Bob;
import entities.Entity;
import entities.enemies.Boss;
import entities.enemies.Sentinel;
import enums.GameState;
import enums.StateOfBattle;

import java.util.Optional;
import java.util.concurrent.Semaphore;
import javax.swing.*;

/**
 * Update class that actualize the battle GUI
 */
public class UpdateBattle {
    /**
     * Update the HP bar for player and enemy
     * @param playerHP player HP bar
     * @param enemyHP enemy HP bar
     * @param bt battle we want to monitor
     */
    public static void updateGame(JProgressBar playerHP, JProgressBar enemyHP, Battle bt){
        playerHP.setValue(bt.getPlayer().getHP());
        enemyHP.setValue(bt.getEnemy().getHP());
    }

    /**
     * Update the button text
     * @param battle battle we want to monitor
     * @param itemButton button of the bag item
     * @param message message we set in button
     */
    public static void updateBag(Battle battle, JButton itemButton, String message){
        itemButton.setText(message);
    }

    /**
     * @return the original message
     */
    public static String originalState(){
        return "What will you do?";
    }

    /**
     * Shows a 2 seconds message
     * @param message message set in status
     * @param status JLabel with the message
     */
    public static void eventState(String message, JLabel status){
        new Thread(() -> {
            status.setText(message);
            try {
                Thread.sleep(2000); // waits 2 seconds
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            status.setText(UpdateBattle.originalState());
        }).start();
    }

    /**
     * Verify if the battle ends with a win or lose, if one of both is true
     * close the window, otherwise continue
     * @param battle battle we want to monitor
     * @param actualFrame JFrame of the battle GUI
     */
    public static void waitWinnerLoser(Battle battle, JFrame actualFrame){
        if (battle.verifyCondition().equals(StateOfBattle.Win)) {
            JOptionPane.showMessageDialog(null,"You win");
            actualFrame.dispose();
            if(battle.getEnemy() instanceof Sentinel){
                battle.getGamePanel().getNpcs()[4].setSpriteNum(3);
                battle.getGamePanel().getNpcs()[4].setWorldY(battle.getGamePanel().getTileSize()*9);
                battle.getGamePanel().getNpcs()[5].setSpriteNum(4);
                battle.getGamePanel().getNpcs()[5].setWorldY(battle.getGamePanel().getTileSize()*9);
                battle.getGamePanel().getUi().cutsceneMode("final_boss");
            } else if((battle.getEnemy() instanceof Boss)&&(battle.getGamePanel().getCurrentMap().getMapName().equals("cave"))){
                battle.getGamePanel().getUi().setCurrentCutsceneCode("cave_final");
                battle.getGamePanel().setGameState(GameState.FINAL_CINEMATIC_STATE);
            } else {
                battle.getGamePanel().getUi().cutsceneMode("won_battle");
            }

        }else if (battle.verifyCondition().equals(StateOfBattle.Lose)) {
            JOptionPane.showMessageDialog(null,"You fainted");
            actualFrame.dispose();
            if(battle.getGamePanel().getCurrentMap().getMapName().equals("mall")){
                battle.getGamePanel().getPlayer().setHP(100);
                battle.getGamePanel().getUi().cutsceneMode("finished_battle_mall");
            } else {
                battle.getGamePanel().setGameState(GameState.GAME_OVER_STATE);
            }
        }
    }

    /**
     * Shows a  2 seconds message of the enemy
     * @param message message of what enemy did
     * @param enemyStatus JLabel with the message
     */
    public static void enemyAttack(String message, JLabel enemyStatus){
        new Thread(() -> {
            enemyStatus.setText(message);
            try {
                Thread.sleep(2000); // waits 2 seconds
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            enemyStatus.setText("");
        }).start();
    }

    /**
     * verify if the enemy attacks, if that is the case shows a message
     * @param HPInitial HP of the player begining the fight
     * @param HPActual HP of the player after fight
     * @param enemyStatus JLabel with the message
     */
    public static void verifyEnemyAttack(int HPInitial, int HPActual, JLabel enemyStatus){
        if (HPActual < HPInitial){
            UpdateBattle.enemyAttack("Enemy hit you",enemyStatus);
        }else {
            UpdateBattle.enemyAttack("Enemy missed the attack", enemyStatus);
        }
    }
}
