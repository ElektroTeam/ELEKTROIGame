package battle.battleGUI.actions.heal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopUpHeal implements ActionListener {
    private JDialog heal;
    public PopUpHeal(JDialog heal) {
        this.heal = heal;
    }

    /**
     * This method sets the bounds and visibility of the fight panel
     * making it visible on the screen.
     * @param e the action event triggered by the user's interaction
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        heal.setBounds(700,350,150,100);
        heal.setVisible(true);
    }
}
