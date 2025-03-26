package battle.battleGUI.actions.bag;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopUpBag implements ActionListener {
    private JDialog bag;

    public PopUpBag(JDialog bag) {
        this.bag = bag;
    }
    /**
     * This method sets the bounds and visibility of the fight panel
     * making it visible on the screen.
     * @param e the action event triggered by the user's interaction
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        bag.setBounds(700,350,170,100);
        bag.setVisible(true);
    }
}
