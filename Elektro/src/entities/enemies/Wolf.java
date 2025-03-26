package entities.enemies;


import entities.Entity;
import enums.AttackSpeed;
import items.weapons.AttackItem;

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
    public Wolf(int HP, AttackSpeed speed, AttackItem weapon) {
        super(HP, speed, weapon);
        setName("Wolf");
    }
}
