package game.mapsfactory.maps;

import entities.player.Player;
import game.levelsfactory.levels.Level;
import music.manager.AudioLoader;
import music.enums.AudioFile;
import utilities.Cutscenes;
/**
 * Public class map.
 * @see Map
 */
public class HouseMap extends Map {
    /**
     * Public constructor.
     */
    public HouseMap() {
        super("house");
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
        AudioLoader.stopSound(AudioFile.MENU);
        AudioLoader.playSound(AudioFile.HOUSE);
        //In case the user wants to replay the level after completed
        Player.getInstance().setHP(100);
        /* MAP'S LOGIC
        * We display the first cutscenes.
        * The player spawns in his bedroom.
        * Can walk around the house (his bedroom, living room and kitchen; not parents' bedroom).
        * Once he exits his house from the backdoor, he'll find Sarah's body, the diary and a bat.
        * After that, he can leave the house through the gate/door next to the house which leads to the neighborhood (then load dessert map).
        * */
        Cutscenes.displayConcreteCutscene(level, "house");
        this.setNextMap(true);
        AudioLoader.stopSound(AudioFile.HOUSE);
    }
}
