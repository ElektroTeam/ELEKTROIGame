package entities.factories;


import entities.Entity;
import entities.enemies.Crystal;
import enums.AttackSpeed;
import items.weapons.AttackItem;
import items.weapons.weaponsFactories.SkewerFactory;
import items.weapons.weaponsFactories.WeaponFactory;
/**
 * The factory to create new crystal enemies.
 * @see EntityFactory
 */
public class CrystalFactory implements EntityFactory{
    WeaponFactory wF = new SkewerFactory();
    AttackItem weapon = wF.createWeapon();
    /**
     * Create the crystal.
     * @return the new crystal.
     */
    @Override
    public Entity createEntity() {
        return new Crystal(70, AttackSpeed.FAST,weapon);
    }
}
