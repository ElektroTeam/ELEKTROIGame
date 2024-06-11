package game.mapsfactory.maps;

import game.levelsfactory.levels.Level;
import music.manager.AudioLoader;
import music.enums.AudioFile;
import utilities.Cutscenes;
/**
 * Public class map.
 * @see Map
 */
public class ParkingLotMap extends Map{
    /**
     * Public constructor.
     */
    public ParkingLotMap() {
        super("parking lot");
    }
    /**
     * Load the map.
     * This method loads the entities, items, and player spawn.
     */
    @Override
    public void loadMap(){
        super.setNextMap(false);
        loadEntities();
        loadItems();
        loadPlayerSpawn();
        //System.out.println("Loaded map: "+super.getMapName());
    }
    @Override
    public void loadEntities(){
        //System.out.println("Loaded entities.");
    }
    @Override
    public void loadItems(){
        //System.out.println("Loaded items.");
    }
    @Override
    public void loadPlayerSpawn(){
        //System.out.println("Loaded player spawn.");
    }
    @Override
    public void playMap(Level level){
        AudioLoader.playSound(AudioFile.PARKING_LOT);
        /* MAP'S LOGIC
        * We display a cutscene of the player leaving the mall through the main exit, now in the parking lot.
        * We could add the name of the mall (Galleries).
        * The player can walk around the parking lot, could interact with some cars I guess.
        * Once he exits the parking lot, we have two options:
        *   1. We display a cutscene of the player walking through the dessert and then enters the forest.
        *   2. We re-use the dessert map so the player can walk and then enter the forest.
        * Then load the next map.
        * */
        Cutscenes.displayConcreteCutscene(level, "parking_lot");
        this.setNextMap(true);
        AudioLoader.stopSound(AudioFile.PARKING_LOT);
    }
}
