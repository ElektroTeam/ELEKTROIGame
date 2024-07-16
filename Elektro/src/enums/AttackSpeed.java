package enums;

/**
 * The velocity values.
 */
public enum AttackSpeed {
    SLOW(25), STANDAR(50), FAST(75);

    private final int value;

    /**
     * Public constructor for the value which will be final.
     * @param newValue the new value for the velocity.
     */
    AttackSpeed(final int newValue){
        value = newValue;
    }
    /**
     * Get the velocity value.
      * @return the value of velocity.
     */
    public int getValue(){
        return value;
    }

}
