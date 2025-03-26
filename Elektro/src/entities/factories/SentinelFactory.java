package entities.factories;

import entities.Entity;
import entities.enemies.Sentinel;
import enums.AttackSpeed;
import items.weapons.AttackItem;
import items.weapons.weaponsFactories.ElectricBatFactory;
import items.weapons.weaponsFactories.WeaponFactory;
/**
 * The factory to create new sentinel enemies.
 * @see EntityFactory
 */
public class SentinelFactory implements EntityFactory{
    WeaponFactory wF = new ElectricBatFactory();
    AttackItem weapon = wF.createWeapon();
    /**
     * Create the sentinel.
     * @return the new sentinel.
     */
    @Override
    public Entity createEntity() {
        return new Sentinel(100, AttackSpeed.STANDAR,weapon);
    }
}
