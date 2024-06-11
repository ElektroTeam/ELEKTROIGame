package entities.factories;

import entities.enemies.Wolf;
import enums.Velocity;
import entities.Entity;
import items.weapons.AttackItem;
import items.weapons.weaponsFactories.ClawFactory;
import items.weapons.weaponsFactories.WeaponFactory;

/**
 * The factory to create new wolf enemies.
 * @see EntityFactory
 */
public class WolfFactory implements EntityFactory{
    private WeaponFactory wF = new ClawFactory();
    AttackItem weapon = wF.createWeapon();
    /**
     * Create the wolf.
     * @return the new wolf.
     */
    @Override
    public Entity createEntity() {
        return new Wolf(120, Velocity.SLOW,weapon);
    }
}
