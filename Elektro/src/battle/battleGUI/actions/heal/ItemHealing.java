package battle.battleGUI.actions.heal;

import battle.Battle;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * abstract class that represent an action depends on healing buttons
 */
public abstract class ItemHealing implements ActionListener {
    protected Battle bt;
    protected JProgressBar playerHP;
    protected JProgressBar enemyHP;
    protected JDialog optionDialog;
    protected JButton button;
    protected JLabel status;
    /**
     * public constructor of the action
     * @param bt battle which wants to monitor
     * @param playerHP HP bar of player
     * @param enemyHP HP bar of enemy
     * @param optionDialog JDialog with healing options
     * @param button which button we want to update
     * @param status message in battle GUI
     */
    public ItemHealing(Battle bt, JProgressBar playerHP, JProgressBar enemyHP, JDialog optionDialog, JButton button, JLabel status) {
        this.bt = bt;
        this.playerHP = playerHP;
        this.enemyHP = enemyHP;
        this.optionDialog = optionDialog;
        this.button = button;
        this.status = status;
    }


}
