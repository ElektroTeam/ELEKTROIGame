package game.mapsfactory.maps;

import game.GamePanel;
import game.TileManager;
import game.levelsfactory.levels.Level;

import java.awt.*;

/**
 * Public class map.
 * @see Map
 */
public class ParkingLotMap extends Map{
    /**
     * Public constructor.
     */
    public ParkingLotMap(GamePanel gamePanel) {
        super(gamePanel, "parkinglot");
        tileManager = new TileManager(gamePanel, "parkinglot");
        defaultXSpawn = 24;
        defaultYSpawn = 7;
        /*defaultXFinished = 12;
        defaultYFinished = 26;*/
        finishedMapArea = new Rectangle(gamePanel.getTileSize()*25, (gamePanel.getTileSize()*42)-10, gamePanel.getTileSize()*5, gamePanel.getTileSize()*1);
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
        //System.out.println("Loaded map: "+super.getMapName());
    }

}
