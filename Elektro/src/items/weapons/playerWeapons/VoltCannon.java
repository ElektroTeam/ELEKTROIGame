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
    /**
     * Get the volt cannon description.
     * @return the volt cannon description.
     */
    @Override
    public String getDescription() {
        return "The most powerful wepon in this world, created with a single mission... Kill\n"
                + ("Damage: "+getBaseDamage());
    }
    /**
     * Use the item to attack the adversary.
     */
    @Override
    public void useItem() {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("\t\t\t\t\t\t --- Volt Canion ---");
        System.out.println("(1) Blast: Shot a powerful volt wave with an explosive damage "+getBaseDamage()+"\n"+
                "(2) Volt Warhead: Lunch a destructive volt wave capable of disintegrate almost anything, but your wepon breaks down "+ (int) (getBaseDamage()*1.5) +"\n"+
                "What will you do?");
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
