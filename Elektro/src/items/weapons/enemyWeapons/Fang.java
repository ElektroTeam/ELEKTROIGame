package items.weapons.enemyWeapons;

import items.weapons.AttackItem;
/**
 * Public class which represents the fang weapon in the game.
 * @see AttackItem
 */
public class Fang extends AttackItem {
    /**
     * Public constructor with parameters.
     * @param baseDamage the fang base damage.
     * @param accuracy the fang accuracy.
     */
    public Fang(int baseDamage, int accuracy) {
        super(baseDamage, accuracy);
    }
    /**
     * Get the fang description.
     * @return the fang description.
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
     * @return the fang's low attack damage.
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
     * @return the fang's special attack damage.
     */
    @Override
    public int specialAttack() {
        if(hitOrNot()){
            return ((getBaseDamage()*2)+10);
        }
        return 0;
    }
}
