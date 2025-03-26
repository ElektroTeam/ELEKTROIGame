package items.weapons.weaponsFactories;

import items.weapons.AttackItem;
import items.weapons.playerWeapons.VoltCannon;

/**
 * The factory to create new volt cannon weapon.
 * @see WeaponFactory
 */
public class VoltCannonFactory implements WeaponFactory {
    /**
     * Create the volt cannon.
     * @return the new volt cannon.
     */
    @Override
    public AttackItem createWeapon() {
        return new VoltCannon(30,8);
    }
}
