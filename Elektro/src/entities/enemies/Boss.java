package entities.enemies;

import entities.Entity;
import enums.AttackSpeed;
import items.weapons.AttackItem;

/**
 * Public class which represents the boss for the ending of levels.
 * @see Entity
 */
public class Boss extends Entity {
    /**
     * Public constructor with parameters.
     * @param HP the health points of the boss.
     * @param speed the speed of the boss.
     * @param weapon the weapon that the boss uses.
     */
    public Boss(int HP, AttackSpeed speed, AttackItem weapon) {
        super(HP, speed, weapon);
    }
}
