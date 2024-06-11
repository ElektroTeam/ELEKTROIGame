package items.consumables.healingsFactory;

import items.consumables.*;
import items.consumables.gameHealings.Bandage;
/**
 * The factory to create bandages.
 * @see HealingFactory
 */
public class BandageFactory implements HealingFactory {
    /**
     * Create a new bandage.
     * @return the new bandage.
     */
    @Override
    public HealerItem createHealing() {
        return new Bandage(25);
    }
}
