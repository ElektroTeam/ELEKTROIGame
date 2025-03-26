package battle.battleGUI.actions.heal;

import battle.Battle;
import utilities.UpdateBattle;


import javax.swing.*;
import java.awt.event.ActionEvent;
/**
 * Class that represent an action depends on healing items
 * @see ItemHealing
 */
public class KitHealing extends ItemHealing {
    /**
     * public constructor of the action
     * @param bt battle which wants to monitor
     * @param playerHP HP bar of player
     * @param enemyHP HP bar of enemy
     * @param optionDialog JDialog with healing options
     * @param button Kit button we want to update
     * @param status message in battle GUI
     */
    public KitHealing(Battle bt, JProgressBar playerHP, JProgressBar enemyHP, JDialog optionDialog, JButton button, JLabel status) {
        super(bt, playerHP, enemyHP, optionDialog, button, status);
    }
    /**
     * we verify if the player HP increase, if that is the case we show a message
     * and update the amount of items in bag
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int originalHP = bt.getPlayer().getHP();

        if (bt.getPlayer().getHealingsItems().isEmpty()){
            UpdateBattle.eventState("You don't have this item",status);
        }else {
            bt.kitHealing();
            if (bt.getPlayer().getHP() > originalHP){
                UpdateBattle.eventState("You recover some HP",status);
            }else {
                UpdateBattle.eventState("HP is already 100",status);
            }
        }
        String message = "Kit: "+ bt.cantKit();
        UpdateBattle.updateGame(playerHP,enemyHP,bt);
        UpdateBattle.updateBag(bt,button,message);
        optionDialog.dispose();
    }
}
