package items.weapons.weaponsFactories;

import items.weapons.AttackItem;
import items.weapons.playerWeapons.Bat;

/**
 * The factory to create new bat weapon.
 * @see WeaponFactory
 */
public class BatFactory implements WeaponFactory {
    /**
     * Create the bat.
     * @return the new bat.
     */
    @Override
    public AttackItem createWeapon() {
        return new Bat(10,8);
    }
}
