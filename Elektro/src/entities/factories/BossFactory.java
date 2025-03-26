package entities.factories;

import entities.Entity;
import entities.enemies.Boss;
import enums.AttackSpeed;
import items.weapons.AttackItem;
import items.weapons.weaponsFactories.VoltCannonFactory;
import items.weapons.weaponsFactories.WeaponFactory;

/**
 * The factory to create bosses for level endings.
 * @see EntityFactory
 */
public class BossFactory implements EntityFactory{
    /**
     * Create the boss.
     * @return the new boss.
     */
    WeaponFactory wf = new VoltCannonFactory();
    AttackItem weapon = wf.createWeapon();
    @Override
    public Entity createEntity() {
        return new Boss(150, AttackSpeed.STANDAR,weapon);
    }
}
