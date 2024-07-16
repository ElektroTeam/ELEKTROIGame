package items.consumables.healingsFactory;

import items.consumables.*;
import items.consumables.gameHealings.Food;
/**
 * The factory to create food items.
 * @see HealingFactory
 */
public class FoodFactory implements HealingFactory {
    /**
     * Create a new food item.
     * @return the new food item.
     */
    @Override
    public HealerItem createHealing() {
        return new Food(10);
    }
}
