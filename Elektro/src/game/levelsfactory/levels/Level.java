package game.levelsfactory.levels;

import entities.Entity;
import game.cutscene.Cutscene;
import game.mapsfactory.maps.Map;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Public abstract class for levels.
 * Levels have maps, not maps have levels.
 */
public abstract class Level {
    private String title;
    private ArrayList<Cutscene> cutscenes;
    private ArrayList<Map> maps;
    private Entity finalBoss;
    private boolean unlocked;
    private boolean nextLevel;
    private Vector<Vector<Float>> playerSpawn;
    /**
     * Public constructor without parameters.
     */
    public Level(){
        cutscenes = new ArrayList<>();
        maps = new ArrayList<>();
        unlocked = false;
        title = "";
        finalBoss = null;
        playerSpawn = null;
    }
    /**
     * Public constructor with parameters.
     * @param cutscenes the list of cinematics.
     * @param title the title of the level.
     * @param maps the list of maps to be played in the level.
     * @param finalBoss the final boss of the level.
     * @param unlocked boolean if the level is unlocked or not.
     * @param playerSpawn the location where the player spawns.
     */
    public Level(ArrayList<Cutscene> cutscenes, String title, ArrayList<Map> maps, Entity finalBoss, boolean unlocked, Vector<Vector<Float>> playerSpawn) {
        this.cutscenes = cutscenes;
        this.title = title;
        this.maps = maps;
        this.finalBoss = finalBoss;
        this.unlocked = unlocked;
        this.playerSpawn = playerSpawn;
    }
    /**
     * Load the maps to be played.
     */
    public void loadMaps(){
        System.out.println("Loading maps...");
        for(Map map: maps){
            map.loadMap();
        }
    }
    /**
     * Play the level.
     * The method loops through the list of maps and plays each map.
     */
    public void play() {
        System.out.println("Playing level '" + this.title + "'");
        for (Map map : maps) {
            while (!map.isNextMap()) {
                map.playMap(this);
            }
        }
        System.out.println("You've finished the level " + title + ", congrats!");
    }
    /**
     * Get the cutscene arraylist.
     * @return the arraylist of cutscenes.
     */
    public ArrayList<Cutscene> getCutscenes() {return cutscenes;}
    /**
     * Set the cutscene arraylist
     * @param cutscenes the arraylist of cutscenes.
     */
    public void setCutscenes(ArrayList<Cutscene> cutscenes) {this.cutscenes = cutscenes;}

    /**
     * Get the level title.
     * @return the title of the level.
     */
    public String getTitle() {return title;}
    /**
     * Set the level title.
     * @param title the title of the level.
     */
    public void setTitle(String title) {this.title = title;}
    /**
     * Get the map arraylist.
     * @return the arraylist of maps.
     */
    public ArrayList<Map> getMaps() {return maps;}
    /**
     * Set the map arraylist.
     * @param maps the arraylist of maps.
     */
    public void setMaps(ArrayList<Map> maps) {this.maps = maps;}
    /**
     * Get the final boss.
     * @return the entity object of the final boss.
     */
    public Entity getFinalBoss() {return finalBoss;}
    /**
     * Set the final boss.
     * @param finalBoss the entity of the boss.
     */
    public void setFinalBoss(Entity finalBoss) {this.finalBoss = finalBoss;}
    /**
     * Validate if the level is unlocked
     * @return whether the level is unlocked or not.
     */
    public boolean isUnlocked() {return unlocked;}
    /**
     * Set the unlocked status of the level.
     * @param unlocked whether the level is unlocked or not.
     */
    public void setUnlocked(boolean unlocked) {this.unlocked = unlocked;}
    /**
     * Get the player spawn location.
     * @return the player spawn location.
     */
    public Vector<Vector<Float>> getPlayerSpawn() {return playerSpawn;}
    /**
     * Set the player spawn location.
     * @param playerSpawn the player spawn location.
     */
    public void setPlayerSpawn(Vector<Vector<Float>> playerSpawn) {this.playerSpawn = playerSpawn;}
    /**
     * Validate if the next level is available.
     * @return whether the player can go to the next level or not.
     */
    public boolean isNextLevel(){return nextLevel;}
    /**
     * Set the next level status.
     * @param nextLevel whether the player can go to the next level or not.
     */
    public void setNextLevel(boolean nextLevel) {this.nextLevel = nextLevel;}
}
