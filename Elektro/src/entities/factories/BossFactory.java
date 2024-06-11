package entities.factories;

import entities.Entity;
import entities.enemies.Boss;
import enums.Velocity;

/**
 * The factory to create bosses for level endings.
 * @see EntityFactory
 */
public class BossFactory implements EntityFactory{
    /**
     * Create the boss.
     * @return the new boss.
     */
    @Override
    public Entity createEntity() {
        return new Boss(150, Velocity.STANDAR,null);
    }
}
