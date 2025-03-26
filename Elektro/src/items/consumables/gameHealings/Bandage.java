package items.consumables.gameHealings;
import items.consumables.*;
/**
 * Public class which represents the bandages in the game.
 * @see HealerItem
 */
public class Bandage extends HealerItem {
    /**
     * Public constructor with parameters.
     * @param healing the amount of HP the item heals.
     */
    public Bandage(int healing) {
        super(healing);
    }
    /**
     * Get the item description.
     * @return the item description.
     */
    @Override
    public String getDescription() {
        return ("Bandages: healing "+getHealing()+"HP");
    }

}
