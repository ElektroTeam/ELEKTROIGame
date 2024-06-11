package entities.enemies;


import enums.Velocity;
import items.weapons.AttackItem;
import entities.Entity;

/**
 * Public class which represents the wolf enemy in the game.
 */
public class Wolf extends Entity {
    /**
     * Public constructor with parameters.
     * @param HP the health points of the wolf.
     * @param speed the speed of the wolf.
     * @param weapon the weapon that the wolf uses.
     */
    public Wolf(int HP, Velocity speed, AttackItem weapon) {
        super(HP, speed, weapon);
        setName("Wolf");
    }
}
