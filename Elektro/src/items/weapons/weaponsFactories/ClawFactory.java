package items.weapons.weaponsFactories;

import items.weapons.AttackItem;
import items.weapons.enemyWeapons.Claw;
/**
 * The factory to create new claw weapon.
 * @see WeaponFactory
 */
public class ClawFactory implements WeaponFactory {
    /**
     * Create the claw.
     * @return the new claw.
     */
    @Override
    public AttackItem createWeapon() {
        return new Claw(20,6);
    }
}
