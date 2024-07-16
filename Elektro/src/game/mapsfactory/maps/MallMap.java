package game.mapsfactory.maps;


import entities.Ema;
import entities.Entity;
import entities.factories.BossFactory;
import entities.factories.EntityFactory;
import game.GamePanel;
import game.TileManager;

import java.awt.*;

/**
 * Public class map.
 * @see Map
 */
public class MallMap extends Map {
    /**
     * Public constructor.
     */
    public MallMap(GamePanel gamePanel) {
        super(gamePanel, "mall");
        tileManager = new TileManager(gamePanel, "mall");
        defaultXSpawn = 32;
        defaultYSpawn = 3;
        /*defaultXFinished = 12;
        defaultYFinished = 23;*/
        finishedMapArea = new Rectangle(gamePanel.getTileSize()*23, gamePanel.getTileSize()*48, gamePanel.getTileSize()*4, gamePanel.getTileSize()*1);
        loadEntities();
        setBattleBackground("res/backgrounds/boss_mall.png");
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
        gamePanel.getUi().setFinishedMallCinematic(false);
        gamePanel.getCollisionChecker().setMallCinematic(false);
        //System.out.println("Loaded map: "+super.getMapName());
    }
    /**
     * Load the entities.
     */
    @Override
    public void loadEntities(){
        Entity enemy;
        EntityFactory eF;
        eF = new BossFactory();
        enemy = eF.createEntity();
        setBoss(enemy);

        if(gamePanel.getNpcs()[9]==null&&!gamePanel.getUi().isFinishedMallCinematic()){
            Entity ema = new Ema(gamePanel);
            ema.setDirection("right");
            ema.setWorldX(gamePanel.getTileSize()*18);
            ema.setWorldY(gamePanel.getTileSize()*34);
            ema.setMoving(false);
            gamePanel.getNpcs()[9] = ema;
        }
    }
}
