package items;

/**
 * Interface for item related actions.
 */
public interface Item {
    /**
     * Get the item description.
     * @return the description of the item.
     */
    String getDescription();
    /**
     * Use the item.
     */
    void useItem();
}
