package battle.battleGUI.actions.bag;

import battle.Battle;
import items.weapons.AttackItem;
import items.weapons.playerWeapons.Bat;
import items.weapons.playerWeapons.ElectricBat;
import items.weapons.playerWeapons.ElectricGun;

import javax.swing.*;

/**
 * Update class that manage all the weapons in bag
 */
public class BagUpdate {
    /**
     * Search the weapon in bag
     * @param battle which battle we are monitoring
     * @param weapon name of the weapon
     * @return position of the weapon in the bag
     */
    public static int weaponchoose(Battle battle, String weapon){
        int index = -1;
        for (AttackItem a: battle.getPlayer().getWeaponsBag()){
            if (a instanceof Bat && weapon == "Bat"){
                index = battle.getPlayer().getWeaponsBag().indexOf(a);
            }else if (a instanceof ElectricBat && weapon == "Electric Bat"){
                index = battle.getPlayer().getWeaponsBag().indexOf(a);
            } else if (a instanceof ElectricGun && weapon == "Electric gun") {
                index = battle.getPlayer().getWeaponsBag().indexOf(a);
            }
        }
        return index;
    }

    /**
     * Search weapons in bag and put the name on buttons
     * @param weapon1 button of weapon 1
     * @param weapon2 button of weapon 2
     * @param weapon3 button of weapon 3
     * @param battle which battle we are monitoring
     */
    public static void weaponsInBag(JButton weapon1, JButton weapon2, JButton weapon3 , Battle battle){
        for (AttackItem a : battle.getPlayer().getWeaponsBag()){
            if (a instanceof Bat ){
                weapon1.setText("Bat");
            } else if (a instanceof ElectricBat){
                weapon2.setText("Electric Bat");
            } else if (a instanceof ElectricGun) {
                weapon3.setText("Electric gun");
            }
        }

    }
}
