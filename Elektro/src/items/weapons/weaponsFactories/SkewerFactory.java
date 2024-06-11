package items.weapons.weaponsFactories;

import items.weapons.AttackItem;
import items.weapons.enemyWeapons.Skewer;

/**
 * The factory to create new skewer weapon.
 * @see WeaponFactory
 */
public class SkewerFactory implements WeaponFactory {
    /**
     * Create the skewer.
     * @return the new skewer.
     */
    @Override
    public AttackItem createWeapon() {
        return new Skewer(10,8);
    }
}
