package items.consumables;
import items.Item;

/**
 * Abstract class for healing items which implements the Item interface.
 * @see Item
 */
public abstract class HealerItem implements Item {
    private int healing;
    /**
     * Public constructor with parameters.
     * @param healing the amount of HP the item heals.
     */
    public HealerItem(int healing) {
        this.healing = healing;
    }
    /**
     * Use the item.
     */
    @Override
    public void useItem() {}
    /**
     * Get the amount of HP the item heals.
     * @return the amount of HP the item heals.
     */
    public int getHealing() {
        return healing;
    }
    /**
     * Set the amount of HP the item heals.
     * @param healing the amount of HP the item heals.
     */
    public void setHealing(int healing) {this.healing = healing;}
    /**
     * Get the item description.
     * @return the item description.
     */
    @Override
    public String getDescription() {return null;}
}
