package entities.enemies;


import entities.Entity;
import enums.AttackSpeed;
import items.weapons.AttackItem;
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
    public Infant(int HP, AttackSpeed speed, AttackItem weapon) {
        super(HP, speed, weapon);
        setName("Infant");
    }
}
