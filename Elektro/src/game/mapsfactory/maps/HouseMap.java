package game.mapsfactory.maps;

import game.GamePanel;
import game.TileManager;

import java.awt.*;

/**
 * Public class map.
 * @see Map
 */
public class HouseMap extends Map {
    /**
     * Public constructor.
     */
    public HouseMap(GamePanel gamePanel) {
        super(gamePanel, "house");
        tileManager = new TileManager(gamePanel, "house");
        defaultXSpawn = 10;
        defaultYSpawn = 13;
        /*defaultXFinished = 22;
        defaultYFinished = 28;*/
        finishedMapArea = new Rectangle(gamePanel.getTileSize()*18, (gamePanel.getTileSize()*33)-10, gamePanel.getTileSize()*2, gamePanel.getTileSize()*1);
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
        resetBattleZones();
        displayIntroCinematic = true;
        gamePanel.getCollisionChecker().setLockedBathroomDialogue(false);
        gamePanel.getCollisionChecker().setLockedMomBedroomDialogue(false);
        //System.out.println("Loaded map: "+super.getMapName());
    }
}
