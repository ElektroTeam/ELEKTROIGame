package items.consumables.healingsFactory;

import items.consumables.*;
/**
 * Public interface for healing factory.
 */
public interface HealingFactory {
    /**
     * Create a new healing item.
     * @return the new healing item.
     */
    HealerItem createHealing();
}
