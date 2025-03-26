package game.mapsfactory.maps;

import game.GamePanel;
import game.TileManager;

import java.awt.*;

/**
 * Public class map.
 * @see Map
 */
public class OrigenMap extends Map {
    /**
     * Public constructor.
     */
    public OrigenMap(GamePanel gamePanel) {
        super(gamePanel, "origen");
        tileManager = new TileManager(gamePanel, "origen");
        defaultXSpawn = 11;
        defaultYSpawn = 13;
        /*defaultXFinished = 22;
        defaultYFinished = 28;*/
        finishedMapArea = new Rectangle(gamePanel.getTileSize()*23, (gamePanel.getTileSize()*49)-10, gamePanel.getTileSize()*3, gamePanel.getTileSize()*1);
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
        tileManager.draw(gamePanel.getG2d());
        displayIntroCinematic = true;
        gamePanel.getCollisionChecker().setLockedNeighborHouseDialogue(false);
        //System.out.println("Loaded map: "+super.getMapName());
    }
}
