package items.weapons;

/**
 * Interface for attack method related actions.
 */
public interface AttackMethod {
    /**
     * Get the low attack damage.
     * @return the integer of the damage that caused the low attack.
     */
    public int lowAttack();
    /**

     * Special attack with more complexity
     * @return the integer of the damage that caused the special attack.
     */
    public int specialAttack();
}