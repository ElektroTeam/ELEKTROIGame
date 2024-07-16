package items.weapons.playerWeapons;

import items.weapons.AttackItem;
/**
 * Public class which represents the volt cannon weapon in the game.
 * @see AttackItem
 */
public class VoltCannon extends AttackItem {
    private final int newDamage = 18;
    /**
     * Public constructor with parameters.
     * @param baseDamage the volt cannon's base damage.
     * @param accuracy the volt cannon's accuracy.
     */
    public VoltCannon(int baseDamage, int accuracy) {
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
     * Get the volt cannon description.
     * @return the volt cannon description.
     */
    @Override
    public String getDescription() {
        return "The most powerful wepon in this world, created with a single mission... Kill";
    }
    /**
     * Use the item to attack the adversary.
     */
    @Override
    public void useItem() {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("\t\t\t\t\t\t --- Volt Canion ---");
    }
    /**
     * Get the low attack damage.
     * @return the volt cannon's low attack damage.
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
     * @return the volt cannon's special attack damage.
     */
    @Override
    public int specialAttack() {
        int attackDamage = (int) (getBaseDamage()*1.5);
        if(hitOrNot()){
            setBaseDamage(newDamage);
            return attackDamage;
        }
        return 0;
    }
}
