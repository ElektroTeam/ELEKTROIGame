package items.weapons.playerWeapons;

import items.weapons.AttackItem;
/**
 * Public class which represents the electric bat weapon in the game.
 * @see AttackItem
 */
public class ElectricBat extends AttackItem {
    public ElectricBat(int baseDamage, int accuracy) {
        super(baseDamage, accuracy);
    }
    /**
     * Get the electric bat description.
     * @return the electric bat description.
     */
    @Override
    public String getDescription() {
        return ("An electric bat, useful in a dystopian world\n"+
                ("Damage: "+getBaseDamage()));
    }
    /**
     * Use the item to attack the adversary.
     */
    @Override
    public void useItem() {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("\t\t\t\t\t\t --- Electric Bat ---");
        System.out.println("(1) Shock: An electric swing with poor charge: " +getBaseDamage()+"\n"+
                "(2) Shock Wave: the electric current is your friend and make possible a powerful attack: " +getBaseDamage()*2+"\n"+
                "What will you do?");
    }
    /**
     * Get the low attack damage.
     * @return the electric bat's low attack damage.
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
     * @return the electric bat's special attack damage.
     */
    @Override
    public int specialAttack() {
        if(hitOrNot()){
            return (getBaseDamage()*2);
        }
        return 0;
    }
}
