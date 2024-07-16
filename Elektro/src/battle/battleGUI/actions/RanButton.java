package battle.battleGUI.actions;

import battle.Battle;

import enums.StateOfBattle;
import utilities.UpdateBattle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action of Escape button
 */
public class RanButton implements ActionListener {
    private Battle bt;
    private JProgressBar playerHP;
    private JProgressBar enemyHP;
    private JLabel status;
    private JFrame actualFrame;
    private JLabel enemyStatus;

    /**
     * public constructor for the action
     * @param bt battle which wants to monitor
     * @param playerHP HP bar of player
     * @param enemyHP HP bar of enemy
     * @param status JLabel with status message
     * @param actualFrame JFrame of the battle GUI
     * @param enemyStatus JLabel with enemy action status
     */
    public RanButton(Battle bt, JProgressBar playerHP, JProgressBar enemyHP, JLabel status, JFrame actualFrame, JLabel enemyStatus) {
        this.bt = bt;
        this.playerHP = playerHP;
        this.enemyHP = enemyHP;
        this.status = status;
        this.actualFrame = actualFrame;
        this.enemyStatus = enemyStatus;
    }

    /**
     * Handles the action event triggered by user interaction.
     * First check if player escape, if that is the case, close the JFrame
     * but if is obligatory or didn't escape show a message and update the bars
     * @param e the action event triggered by the user's interaction
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int org = bt.getPlayer().getHP();
        StateOfBattle escapeTry = bt.ranAttempt();
        UpdateBattle.updateGame(playerHP,enemyHP,bt);
        if (escapeTry.equals(StateOfBattle.Ran)){
            JOptionPane.showMessageDialog(null,"You escaped");
            bt.getGamePanel().getUi().cutsceneMode("escaped_battle");
            actualFrame.dispose();
        } else if (escapeTry.equals(StateOfBattle.readyAndGo) && bt.isObligatory()) {
            UpdateBattle.eventState("You can not escape",status);
        } else {
            UpdateBattle.eventState("You got stuck!",status);
        }
        UpdateBattle.verifyEnemyAttack(org,bt.getPlayer().getHP(),enemyStatus);
        UpdateBattle.waitWinnerLoser(bt, actualFrame);
    }


}
