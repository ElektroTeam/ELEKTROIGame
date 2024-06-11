package items.consumables.gameHealings;
import items.consumables.*;

/**
 * Public class which represents the food in the game.
 * @see HealerItem
 */
public class Food extends HealerItem {
    /**
     * Public constructor with parameters.
     * @param healing the amount of HP the item heals.
     */
    public Food(int healing) {
        super(healing);
    }
    /**
     * Get the item description.
     * @return the item description.
     */
    @Override
    public String getDescription() {return ("Food: healing "+getHealing()+"HP");}

}
