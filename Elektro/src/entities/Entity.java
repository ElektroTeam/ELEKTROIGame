package entities;

import enums.Velocity;
import items.consumables.HealerItem;
import items.weapons.AttackItem;

import java.util.ArrayList;
/**
 * Abstract class which represents an entity in the game.
 */
public abstract class Entity {
    private String name;
    private int HP;
    private Velocity speed;
    private AttackItem weapon;
    private ArrayList<HealerItem> healingsItems;
    /**
     * Public constructor with parameters.
     * @param HP the health points of the entity.
     * @param speed the entity speed.
     * @param weapon the weapon the entity uses.
     */
    public Entity(int HP, Velocity speed, AttackItem weapon) {
        this.HP = HP;
        this.speed = speed;
        this.weapon = weapon;
        healingsItems = new ArrayList<>();
    }
    /**
     * Get the name of the entity.
     * @return the name of the entity.
     */
    public String getName() {return name;}
    /**
     * Set the name of the entity.
     * @param name the new name of the entity.
     */
    public void setName(String name) {this.name = name;}
    /**
     * Get the health points of the entity.
     * @return the health points of the entity.
     */
    public int getHP() {return HP;}
    /**
     * Set the health points of the entity.
     * @param HP the new health points of the entity.
     */
    public void setHP(int HP) {this.HP = HP;}
    /**
     * Get the speed of the entity.
     * @return the speed of the entity.
     */
    public Velocity getSpeed() {return speed;}
    /**
     * Set the speed of the entity.
     * @param speed the new speed of the entity.
     */
    public void setSpeed(Velocity speed) {this.speed = speed;}
    /**
     * Get the weapon of the entity.
     * @return the weapon of the entity.
     */
    public AttackItem getWeapon() {return weapon;}
    /**
     * Set the weapon of the entity.
     * @param weapon the new weapon of the entity.
     */
    public void setWeapon(AttackItem weapon) {this.weapon = weapon;}
    /**
     * Gets the list of healing items of the entity.
     * @return the list of healing items of the entity.
     */
    public ArrayList<HealerItem> getHealingsItems() {return healingsItems;}
    /**
     * Set the list of healing items of the entity.
     * @param healingsItems the new list of healing items of the entity.
     */
    public void setHealingsItems(ArrayList<HealerItem> healingsItems) {this.healingsItems = healingsItems;}
}