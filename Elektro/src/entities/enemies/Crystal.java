package entities.enemies;


import entities.Entity;
import enums.AttackSpeed;
import items.weapons.AttackItem;

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
    public Crystal(int HP, AttackSpeed speed, AttackItem weapon) {
        super(HP, speed, weapon);
        setName("Crystal");
    }
}
