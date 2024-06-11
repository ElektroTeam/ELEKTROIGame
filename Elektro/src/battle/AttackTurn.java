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
    public void gameplay(Optional<Boolean> obligatory, Optional<Boolean> consecutive);
    /**
     * Escape attempt during the game.
     * @return the StateOfBattle corresponding.
     */
    public StateOfBattle ranAttempt();
    /**
     * Fight between player and enemy.
     */
    public void fight();
    /**
     * How the player attack.
     */
    public void playerAttack();
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
}