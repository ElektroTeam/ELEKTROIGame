package items.weapons.playerWeapons;

import items.weapons.AttackItem;

/**
 * Public class which represents the bat weapon in the game.
 * @see AttackItem
 */
public class Bat extends AttackItem {
    /**
     * Public constructor with parameters.
     * @param baseDamage the bat's base damage.
     * @param accuracy the bat's accuracy.
     */
    public Bat(int baseDamage, int accuracy) {
        super(baseDamage, accuracy);
    }
    /**
     * Get the bat description.
     * @return the bat description.
     */
    @Override
    public String getDescription() {
        return ("Pretty bad weapon to use but at least you can defend yourself.\n" +
                ("Damage: " + getBaseDamage()));
    }
    /**
     * Use the item to attack the adversary.
     */
    @Override
    public void useItem() {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("\t\t\t\t\t\t\t --- Bat ---");
        System.out.println("(1) Swing: A basic attack where the most important thing is your hip: "+ getBaseDamage() +"\n" +
                           "(2) Home Run: take the better position to batting with all your strength: " + getBaseDamage()*2 + "\n"+
                           "What will you do?");
    }
    /**
     * Get the low attack damage.
     * @return the bat's low attack damage.
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
     * @return the bat's special attack damage.
     */
    @Override
    public int specialAttack() {
        if(hitOrNot()){
            return (getBaseDamage()*2);
        }
        return 0;
    }
}
