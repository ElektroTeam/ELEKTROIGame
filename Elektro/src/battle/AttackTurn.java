package battle;
import enums.StateOfBattle;
import java.util.Optional;
/**
 * Interface with all the method used in a turn.
 */
public interface AttackTurn {
    /**
     * The gameplay
     */
    public void gameplay();
    /**
     * Escape attempt during the game.
     * @return the StateOfBattle corresponding.
     */
    public StateOfBattle ranAttempt();
    /**
     * Fight between player and enemy.
     * @param attack option of the attack
     * @return final damage
     */
    public int fight(int attack);
    /**
     * How the player attack.
     * @param attack which attack is going to do
     * @return damage of the player
     */
    public int playerAttack(int attack);
    /**
     * How the enemy attack.
     */
    public void enemyAttack();
    /**
     * How the food heals the player.
     */
    public void foodHealing();
    /**
     * How the bandage heals the player.
     */
    public void bandageHealing();
    /**
     * How the kit heals the player.
     */
    public void kitHealing();

    /**
     * change actual weapon with one of the bag
     * @param index position of weapon in the bag
     */
    public void changeWeapon(int index);
}