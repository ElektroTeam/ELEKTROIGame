package items.weapons.weaponsFactories;

import items.weapons.AttackItem;
import items.weapons.enemyWeapons.Fang;

/**
 * The factory to create new fang weapon.
 * @see WeaponFactory
 */
public class FangFactory implements WeaponFactory {
    /**
     * Create the fang.
     * @return the new fang.
     */
    @Override
    public AttackItem createWeapon() {
        return new Fang(12,7);
    }
}
