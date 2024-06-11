package items.consumables.healingsFactory;


import items.consumables.HealerItem;
import items.consumables.gameHealings.Kit;
/**
 * The factory to create med kits.
 * @see HealingFactory
 */
public class KitFactory implements HealingFactory{
    /**
     * Create a new med kit.
     * @return the new med kit.
     */
    @Override
    public HealerItem createHealing() {
        return new Kit(50);
    }
}
