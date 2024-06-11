package entities.player;


import enums.Velocity;
import items.weapons.AttackItem;
import entities.Entity;
/**
 * Singleton class for the Player entity.
 * We use singleton because there will be only one player.
 * @see Entity
 */
public class Player extends Entity {
    private static Player instance;
    /**
     * Private constructor with parameters.
     * @param HP the health points of the player.
     * @param speed the player speed.
     * @param weapon the weapon that the player uses.
     */
    private Player(int HP, Velocity speed, AttackItem weapon) {
        super(HP, speed, weapon);
    }
    /**
     * Get the player instance.
     * If the instance is null, create a new one.
     * @return the instance of Player.
     */
    public static Player getInstance(){
        if (instance == null) {
            instance = new Player(100, Velocity.STANDAR, null);
        }
        return instance;
    }
}

