package items.weapons;

import items.Item;

/**
 * Public class which represents an attack item (weapon) in the game.
 * @see AttackMethod
 * @see Item
 */
public abstract class AttackItem implements AttackMethod, Item {
    private int accuracy;
    private int baseDamage;
    /**
     * Public constructor with parameters.
     * @param baseDamage the base damage of the weapon.
     * @param accuracy the accuracy of the weapon.
     */
    public AttackItem(int baseDamage, int accuracy) {
        this.baseDamage = baseDamage;
        this.accuracy = accuracy;
    }
    /**
     * Determinate if the weapon reached the adversary through probability.
     * @return whether the weapon reached the adversary or not.
     */
    public boolean hitOrNot() {
        float random = (int) (Math.random()*10+1);
        if (accuracy >= random){
            return true;
        }else{
            return false;
        }
    }
    //        public AttackItem setmove(int option){
//            switch (option) {
//                case 1:
//                    AttackItem.this.setBaseDamage(lowAttack());
//                    break;
//                case 2:
//                    AttackItem.this.setBaseDamage(specialAttack());
//                    break;
//            }
//
//            return this;
//        }
    /**
     * Get the weapon accuracy.
     * @return the weapon accuracy.
     */
    public int getAccuracy() {return accuracy;}
    /**
     * Set the weapon accuracy.
     * @param accuracy the new accuracy.
     */
    public void setAccuracy(int accuracy) {this.accuracy = accuracy;}
    /**
     * Get the base damage to the weapon.
     * @return the weapon base damage.
     */
    public int getBaseDamage() {return baseDamage;}
    /**
     * Set the weapon base damage.
     * @param baseDamage the new base damage.
     */
    public void setBaseDamage(int baseDamage) {this.baseDamage = baseDamage;}
}


