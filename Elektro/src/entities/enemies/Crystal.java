package entities.enemies;


import enums.Velocity;
import items.weapons.AttackItem;
import entities.Entity;

/**
 * Public class which represents the crystal enemy in the game.
 * @see Entity
 */
public class Crystal extends Entity {
    /**
     * Public constructor with parameters.
     * @param HP the health points of the crystal.
     * @param speed the speed of the crystal.
     * @param weapon the weapon that the crystal uses.
     */
    public Crystal(int HP, Velocity speed, AttackItem weapon) {
        super(HP, speed, weapon);
        setName("Crystal");
    }
}
