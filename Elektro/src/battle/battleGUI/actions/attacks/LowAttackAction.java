package battle.battleGUI.actions.attacks;

import battle.Battle;
import utilities.UpdateBattle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action for the low attack button
 */
public class LowAttackAction implements ActionListener{
    private Battle bt;
    private JProgressBar playerHP;
    private JProgressBar enemyHP;
    private JLabel status;
    private JDialog options;
    private JFrame actualFrame;
    private JLabel enemyStatus;

    /**
     * public constructor for the action
     * @param bt battle which wants to monitor
     * @param playerHP HP bar of player
     * @param enemyHP HP bar of enemy
     * @param status JLabel with status message
     * @param options JDialog of the attack options
     * @param actualFrame JFrame of the battle GUI
     * @param enemyStatus JLabel with enemy action status
     */
    public LowAttackAction(Battle bt, JProgressBar playerHP, JProgressBar enemyHP, JLabel status, JDialog options, JFrame actualFrame, JLabel enemyStatus) {
        this.bt = bt;
        this.playerHP = playerHP;
        this.enemyHP = enemyHP;
        this.status = status;
        this.options = options;
        this.actualFrame = actualFrame;
        this.enemyStatus = enemyStatus;
    }

    /**
     * Handles the action event triggered by user interaction. This method initiates a fight with a low attack,
     * shows a message based on the damage dealt and checks the state of the battle.
     *
     * @param e the action event triggered by the user's interaction
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //bt.fight(1);
        int initialHP = bt.getPlayer().getHP();
        int damage = bt.fight(1);
        if (damage == 0){
            UpdateBattle.eventState("You missed!",status);
        }else {
            UpdateBattle.eventState("Perfect hit!",status);
        }
        UpdateBattle.verifyEnemyAttack(initialHP,bt.getPlayer().getHP(),enemyStatus);
        UpdateBattle.updateGame(playerHP,enemyHP,bt);
        options.dispose();
        UpdateBattle.waitWinnerLoser(bt,actualFrame);
    }
}
