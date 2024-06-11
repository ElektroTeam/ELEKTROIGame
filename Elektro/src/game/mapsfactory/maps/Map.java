package game.mapsfactory.maps;

import entities.Entity;
import game.levelsfactory.levels.Level;
import items.Item;

import java.util.ArrayList;
/**
 * Abstract class for maps.
 */
public abstract class Map {
    private String mapName;
    private ArrayList<Entity> sentinels;
    private ArrayList<Entity> crystals;
    private ArrayList<Entity> infants;
    private ArrayList<Entity> wolves;
    private Entity boss;
    private ArrayList<Item> items;
    private boolean nextMap;
    /**
     * Public constructor to create the map.
     * @param mapName the name of the map.
     */
    public Map(String mapName) {
        this.mapName = mapName;
        sentinels = new ArrayList<>();
        crystals = new ArrayList<>();
        infants = new ArrayList<>();
        wolves = new ArrayList<>();
        items = new ArrayList<>();
        nextMap = false;
    }
    /**
     * Load the map.
     * This method loads the entities, items, and player spawn.
     */
    public void loadMap(){
        this.setNextMap(false);
        loadEntities();
        loadItems();
        loadPlayerSpawn();
        //System.out.println("Loaded map: "+mapName);
    }
    public void loadEntities(){
        //System.out.println("Loaded entities.");
    }
    public void loadItems(){
        //System.out.println("Loaded items.");
    }
    public void loadPlayerSpawn(){
        //System.out.println("Loaded player spawn.");
    }
    public void playMap(Level level){
        //System.out.println("playing map '"+this.mapName+"'");
        this.nextMap = true;
    }
    /**
     * Get the name of the map.
     * @return the name of the map.
     */
    public String getMapName() {return mapName;}
    /**
     * Set the name of the map.
     * @param mapName the name of the map.
     */
    public void setMapName(String mapName) {this.mapName = mapName;}
    /**
     * Get the arraylist of sentinels.
     * @return the list of sentinels.
     */
    public ArrayList<Entity> getSentinels() {return sentinels;}
    /**
     * Set the arraylist of sentinels.
     * @param sentinels the list of sentinels.
     */
    public void setSentinels(ArrayList<Entity> sentinels) {this.sentinels = sentinels;}
    /**
     * Get the arraylist of crystals.
     * @return the list of crystals.
     */
    public ArrayList<Entity> getCrystals() {return crystals;}
    /**
     * Set the arraylist of crystals.
     * @param crystals the list of crystals.
     */
    public void setCrystals(ArrayList<Entity> crystals) {this.crystals = crystals;}
    /**
     * Get the arraylist of infants.
     * @return the list of infants.
     */
    public ArrayList<Entity> getInfants() {return infants;}
    /**
     * Set the arraylist of infants.
     * @param infants the list of infants.
     */
    public void setInfants(ArrayList<Entity> infants) {this.infants = infants;}
    /**
     * Get the arraylist of wolves.
     * @return the list of wolves.
     */
    public ArrayList<Entity> getWolves() {return wolves;}
    /**
     * Set the arraylist of wolves.
     * @param wolves the list of wolves.
     */
    public void setWolves(ArrayList<Entity> wolves) {this.wolves = wolves;}
    /**
     * Validate if the next map is unlocked.
     * @return whether the next map is unlocked or not.
     */
    public boolean isNextMap() {return nextMap;}
    /**
     * Set the unlocked status of the next map.
     * @param nextMap whether the next map is unlocked or not.
     */
    public void setNextMap(boolean nextMap) {this.nextMap = nextMap;}
    /**
     * Get the arraylist of items.
     * @return the list of items.
     */
    public ArrayList<Item> getItems() {return items;}
    /**
     * Set the arraylist of items.
     * @param items the arraylist of items.
     */
    public void setItems(ArrayList<Item> items) {this.items = items;}
    /**
     * Get the boss entity.
     * @return the boss entity.
     */
    public Entity getBoss() {return boss;}
    /**
     * Set the boss entity.
     * @param boss the boss entity.
     */
    public void setBoss(Entity boss) {this.boss = boss;}
}
