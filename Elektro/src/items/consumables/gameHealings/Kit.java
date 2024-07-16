package items.consumables.gameHealings;
import items.consumables.*;
/**
 * Public class which represents the med kits in the game.
 * @see HealerItem
 */
public class Kit extends HealerItem {
    /**
     * Public constructor with parameters.
     * @param healing the amount of HP the item heals.
     */
    public Kit(int healing) {
        super(healing);
    }
    /**
     * Get the item description.
     * @return the item description.
     */
    @Override
    public String getDescription() {
        return ("Med kit: healing "+getHealing()+"HP");
    }

}
