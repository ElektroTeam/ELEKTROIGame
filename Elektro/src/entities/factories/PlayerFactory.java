package entities.factories;


import entities.Entity;
import entities.player.Player;

/**
 * The factory to create the player.
 * Since we implemented singleton we can only create one player.
 * After creating the first player, the factory will return the same player when trying to create a new one.
 * @see EntityFactory
 */
public class PlayerFactory implements EntityFactory{
    /**
     * Create the player if it does not exist already.
     * @return the new (or existing) player.
     */
    @Override
    public Entity createEntity() {
        return Player.getInstance();
    }
}
