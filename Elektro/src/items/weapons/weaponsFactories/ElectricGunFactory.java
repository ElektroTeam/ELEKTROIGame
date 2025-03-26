package items.weapons.weaponsFactories;

import items.weapons.AttackItem;
import items.weapons.playerWeapons.ElectricGun;

/**
 * The factory to create new electric gun weapon.
 * @see WeaponFactory
 */
public class ElectricGunFactory implements WeaponFactory {
    /**
     * Create the electric gun.
     * @return the new electric gun.
     */
    @Override
    public AttackItem createWeapon() {
        return new ElectricGun(14,6);
    }
}
