package entities.factories;


import entities.Entity;
import entities.enemies.Infant;
import enums.AttackSpeed;
import items.weapons.AttackItem;
import items.weapons.weaponsFactories.FangFactory;
import items.weapons.weaponsFactories.WeaponFactory;

/**
 * The factory to create new infant enemies.
 * @see EntityFactory
 */
public class InfantFactory implements EntityFactory{
    private final WeaponFactory wF = new FangFactory();
    private final AttackItem weapon = wF.createWeapon();
    /**
     * Create the infant.
     * @return the new infant.
     */
    @Override
    public Entity createEntity() {
        return new Infant(80, AttackSpeed.FAST, weapon);
    }
}
