package entities.enemies;


import enums.Velocity;
import items.weapons.AttackItem;
import entities.Entity;
/**
 * Public class which represents the infant enemy in the game.
 * @see Entity
 */
public class Infant extends Entity {
    /**
     * Public constructor with parameters.
     * @param HP the health points of the infant.
     * @param speed the speed of the infant.
     * @param weapon the weapon that the infant uses.
     */
    public Infant(int HP, Velocity speed, AttackItem weapon) {
        super(HP, speed, weapon);
        setName("Infant");
    }
}
