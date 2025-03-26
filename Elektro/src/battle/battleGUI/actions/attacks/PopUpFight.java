package battle.battleGUI.actions.attacks;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action of Fight button
 */
public class PopUpFight implements ActionListener {
    private JDialog fight;

    /**
     * public constructor for the action
     * @param fight JDialog which is going to show
     */
    public PopUpFight(javax.swing.JDialog fight) {
        this.fight = fight;
    }

    /**
     * This method sets the bounds and visibility of the fight panel
     * making it visible on the screen.
     * @param e the action event triggered by the user's interaction
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        fight.setBounds(500,300,500,200);
        fight.setVisible(true);


    }
}
