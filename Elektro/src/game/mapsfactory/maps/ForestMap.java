package game.mapsfactory.maps;

import entities.Entity;
import entities.factories.EntityFactory;
import entities.factories.WolfFactory;
import game.GamePanel;
import game.TileManager;
import game.levelsfactory.levels.Level;

import java.awt.*;
import java.util.HashMap;

/**
 * Public class map.
 * @see Map
 */
public class ForestMap extends Map {
    /**
     * Public constructor.
     */
    public ForestMap(GamePanel gamePanel) {
        super(gamePanel, "forest");
        tileManager = new TileManager(gamePanel, "forest");
        defaultXSpawn = 26;
        defaultYSpawn = 3;
        /*defaultXFinished = 8;
        defaultYFinished = 32;*/
        finishedMapArea = new Rectangle(gamePanel.getTileSize()*34, gamePanel.getTileSize()*46, gamePanel.getTileSize()*2, gamePanel.getTileSize()*1);
        loadEntities();
        setBattleBackground("/backgrounds/wolf_forest.png");
    }
    /**
     * Load the map.
     * This method loads the entities, items, and player spawn.
     */
    @Override
    public void loadMap() {
        super.setNextMap(false);
        loadEntities();
        loadItems();
        loadPlayerSpawn();
        tileManager.draw(gamePanel.getG2d());
        resetBattleZones();
        displayIntroCinematic = true;
        //System.out.println("Loaded map: " + super.getMapName());
    }
    /**
     * Reset the battle zones.
     */
    @Override
    public void resetBattleZones(){
        startBattleZone = new HashMap<>();
        startBattleZone.put(1, new Rectangle(gamePanel.getTileSize()*18, gamePanel.getTileSize()*6, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(2, new Rectangle(gamePanel.getTileSize()*7, gamePanel.getTileSize()*10, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(3, new Rectangle(gamePanel.getTileSize()*4, gamePanel.getTileSize()*19, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(4, new Rectangle(gamePanel.getTileSize()*10, gamePanel.getTileSize()*19, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(5, new Rectangle(gamePanel.getTileSize()*9, gamePanel.getTileSize()*25, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(6, new Rectangle(gamePanel.getTileSize()*24, gamePanel.getTileSize()*24, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(7, new Rectangle(gamePanel.getTileSize()*30, gamePanel.getTileSize()*27, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(8, new Rectangle(gamePanel.getTileSize()*37, gamePanel.getTileSize()*32, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(9, new Rectangle(gamePanel.getTileSize()*19, gamePanel.getTileSize()*37, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(10, new Rectangle(gamePanel.getTileSize()*5, gamePanel.getTileSize()*36, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(11, new Rectangle(gamePanel.getTileSize()*26, gamePanel.getTileSize()*9, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(12, new Rectangle(gamePanel.getTileSize()*32, gamePanel.getTileSize()*11, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(13, new Rectangle(gamePanel.getTileSize()*44, gamePanel.getTileSize()*9, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
    }
    /**
     * Load the entities.
     */
    @Override
    public void loadEntities() {
        Entity wolf;
        EntityFactory eF;
        eF = new WolfFactory();
        wolf = eF.createEntity();
        setEnemy(wolf);
    }
}