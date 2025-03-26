package items.weapons.enemyWeapons;

import items.weapons.AttackItem;
/**
 * Public class which represents the skewer weapon in the game.
 * @see AttackItem
 */
public class Skewer extends AttackItem {
    /**
     * Public constructor with parameters.
     * @param baseDamage the skewer base damage.
     * @param accuracy the skewer accuracy.
     */
    public Skewer(int baseDamage, int accuracy) {
        super(baseDamage, accuracy);
    }

    @Override
    public String lowAttackInfo() {
        return null;
    }

    @Override
    public String specialAttackInfo() {
        return null;
    }

    @Override
    public String nameWeapon() {
        return null;
    }

    /**
     * Get the skewer description.
     * @return the skewer description.
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
     * @return the skewer's low attack damage.
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
     * @return the skewer's special attack damage.
     */
    @Override
    public int specialAttack() {
        int multi = 0;
        for (int i = 0; i < 3; i++){
            float random = (int) (Math.random()*2+1);
            if (random == 1){
                multi++;
            }
        }
        return getBaseDamage()*multi;
    }
}
