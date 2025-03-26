package entities.enemies;


import entities.Entity;
import enums.AttackSpeed;
import items.weapons.AttackItem;

/**
 * Public class which represents the sentinel enemy in the game.
 */
public class Sentinel extends Entity {
    /**
     * Public constructor with parameters.
     * @param HP the health points of the sentinel.
     * @param speed the speed of the sentinel.
     * @param weapon the weapon that the sentinel uses.
     */
    public Sentinel(int HP, AttackSpeed speed, AttackItem weapon) {
        super(HP, speed, weapon);
        setName("Sentinel");
    }
}
