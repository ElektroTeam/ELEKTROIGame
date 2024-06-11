package entities.enemies;


import enums.Velocity;
import items.weapons.AttackItem;
import entities.Entity;

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
    public Sentinel(int HP, Velocity speed, AttackItem weapon) {
        super(HP, speed, weapon);
        setName("Sentinel");
    }
}
