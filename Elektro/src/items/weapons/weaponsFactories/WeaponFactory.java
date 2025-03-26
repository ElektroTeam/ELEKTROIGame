package items.weapons.weaponsFactories;

import items.weapons.AttackItem;
/**
 * Public interface for AttackItem factory.
 */
public interface WeaponFactory {
    /**
     * Create the AttackItem.
     * @return the new AttackItem.
     */
    AttackItem createWeapon();
}
