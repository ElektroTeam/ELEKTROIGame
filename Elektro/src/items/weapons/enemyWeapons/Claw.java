package items.weapons.enemyWeapons;

import items.weapons.AttackItem;
/**
 * Represents a claw as an AttackItem.
 * @see AttackItem
 */
public class Claw extends AttackItem {
    /**
     * Public constructor with parameters.
     * @param baseDamage the claw base damage.
     * @param accuracy the claw accuracy.
     */
    public Claw(int baseDamage, int accuracy) {
        super(baseDamage, accuracy);
    }
    /**
     * Get the claw description.
     * @return the claw description.
     */
    @Override
    public String getDescription() {
        // Currently there will be no enemy weapon descriptions.
        return null;
    }
    /**
     * Use the item to attack the adversary.
     */
    @Override
    public void useItem() {
        // Currently there will be no enemy weapon usage.
    }
    /**
     * Get the low attack damage.
     * @return the claw's low attack damage.
     */
    @Override
    public int lowAttack() {
        if(hitOrNot()){
            return getBaseDamage();
        }
        return 0;
    }
    /**
     * Get the special attack damage.
     * @return the claw's special attack damage.
     */
    @Override
    public int specialAttack() {
        if(hitOrNot()){
            return ((getBaseDamage()*2)+15);
        }
        return 0;
    }
}

