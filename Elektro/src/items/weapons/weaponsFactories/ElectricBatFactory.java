package items.weapons.weaponsFactories;

import items.weapons.AttackItem;
import items.weapons.playerWeapons.ElectricBat;

/**
 * The factory to create new electric bat weapon.
 * @see WeaponFactory
 */
public class ElectricBatFactory implements WeaponFactory {
    /**
     * Create the electric bat.
     * @return the new electric bat.
     */
    @Override
    public AttackItem createWeapon() {
        return new ElectricBat(18,8);
    }
}
