package game.mapsfactory.maps;


import entities.factories.CrystalFactory;
import entities.factories.EntityFactory;
import game.GamePanel;
import game.TileManager;
import game.levelsfactory.levels.Level;



import entities.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Optional;
/**
 * Public class map.
 * @see Map
 */
public class DesertMap extends Map {
    private Entity clone;
    /**
     * Public constructor.
     */
    public DesertMap(GamePanel gamePanel) {
        super(gamePanel, "desert");
        tileManager = new TileManager(gamePanel, "desert");
        defaultXSpawn = 23;
        defaultYSpawn = 3;
        /*defaultXFinished = 19;
        defaultYFinished = 43;*/
        finishedMapArea = new Rectangle(gamePanel.getTileSize()*30, (gamePanel.getTileSize()*49)-10, gamePanel.getTileSize()*4, gamePanel.getTileSize()*1);
        loadEntities();
        setBattleBackground("/backgrounds/crystal_desert.png");

    }
    /**
     * Reset the battle zones.
     */
    @Override
    public void resetBattleZones(){
        startBattleZone = new HashMap<>();
        startBattleZone.put(1, new Rectangle(gamePanel.getTileSize()*15, gamePanel.getTileSize()*8, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(2, new Rectangle(gamePanel.getTileSize()*30, gamePanel.getTileSize()*9, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(3, new Rectangle(gamePanel.getTileSize()*36, gamePanel.getTileSize()*18, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(4, new Rectangle(gamePanel.getTileSize()*6, gamePanel.getTileSize()*7, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(5, new Rectangle(gamePanel.getTileSize()*21, gamePanel.getTileSize()*12, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(6, new Rectangle(gamePanel.getTileSize()*41, gamePanel.getTileSize()*10, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(7, new Rectangle(gamePanel.getTileSize()*28, gamePanel.getTileSize()*21, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(8, new Rectangle(gamePanel.getTileSize()*14, gamePanel.getTileSize()*18, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(9, new Rectangle(gamePanel.getTileSize()*6, gamePanel.getTileSize()*24, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(10, new Rectangle(gamePanel.getTileSize()*7, gamePanel.getTileSize()*34, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(11, new Rectangle(gamePanel.getTileSize()*16, gamePanel.getTileSize()*39, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(12, new Rectangle(gamePanel.getTileSize()*23, gamePanel.getTileSize()*33, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(13, new Rectangle(gamePanel.getTileSize()*35, gamePanel.getTileSize()*38, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(14, new Rectangle(gamePanel.getTileSize()*28, gamePanel.getTileSize()*38, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
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
    /**
     * Load the entities.
     */

    @Override
    public void loadEntities(){
        Entity enemy;
        EntityFactory eF;
        eF = new CrystalFactory();
        enemy = eF.createEntity();
        setEnemy(enemy);
        //System.out.println("Loaded entities.");*/
    }

}
