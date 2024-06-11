package items.weapons.playerWeapons;

import items.weapons.AttackItem;
/**
 * Public class which represents the electric gun weapon in the game.
 * @see AttackItem
 */
public class ElectricGun extends AttackItem {
    /**
     * Public constructor with parameters.
     * @param baseDamage the electric gun's base damage.
     * @param accuracy the electric gun's accuracy.
     */
    public ElectricGun(int baseDamage, int accuracy) {
        super(baseDamage, accuracy);
    }
    /**
     * Get the electric gun description.
     * @return the electric gun description.
     */
    @Override
    public String getDescription() {
        return "A powerful wepon capable of defeat anyone. Careful with the recoil\n" +
                ("Damage: " + getBaseDamage());
    }
    /**
     * Use the item to attack the adversary.
     */
    @Override
    public void useItem() {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("\t\t\t\t\t\t --- Electric gun ---");
        System.out.println("(1) Golden bullet: Hold the gun in two hands to secure the shot: " +getBaseDamage()+"\n"+
                "(2) Rapid Fire: Fires a bust of bullets but your accuracy is not the best: " +getBaseDamage()+"-"+(getBaseDamage()*6)+"\n"+
                "What will you do?");
    }
    /**
     * Get the low attack damage.
     * @return the electric gun's low attack damage.
     */
    @Override
    public int lowAttack() {
        return getBaseDamage();
    }
    /**
     * Get the special attack damage.
     * @return the electric gun's special attack damage.
     */
    @Override
    public int specialAttack() {
        int multi = 0;
        for (int i=0; i<7;i++){
            boolean prob = hitOrNot();
            if (prob){
                multi++;
            }
        }
        System.out.println("You hit "+multi+" bullets");
        return getBaseDamage()*multi;
    }
}
