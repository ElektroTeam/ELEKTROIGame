package battle.battleGUI.actions.bag;

import battle.Battle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action of Bag button
 */
public class ChangeWeapon implements ActionListener {
    private Battle battle;
    private JButton weaponButton;
    private JDialog bagOptions;
    private JLabel weaponInfo;
    /**
     * public constructor for the action
     * @param battle battle which wants to monitor
     * @param weaponInfo JLabel with weapon info
     * @param weaponButton JButton we do the action
     * @param bagOptions JDialog with the bag options
     */
    public ChangeWeapon(Battle battle, JButton weaponButton, JDialog bagOptions, JLabel weaponInfo) {
        this.battle = battle;
        this.weaponButton = weaponButton;
        this.bagOptions = bagOptions;
        this.weaponInfo = weaponInfo;
    }

    /**
     * we search the position of the weapon and change if is there
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String weapon = weaponButton.getText();
        int index = BagUpdate.weaponchoose(battle,weapon);
        battle.changeWeapon(index);
        bagOptions.dispose();
        weaponInfo.setText(battle.getPlayer().getWeapon().getDescription());
    }
}
