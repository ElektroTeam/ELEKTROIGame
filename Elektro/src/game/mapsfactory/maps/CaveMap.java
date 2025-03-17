package game.mapsfactory.maps;
import entities.Bob;
import entities.Ema;
import entities.Entity;
import entities.Sentinel;
import entities.factories.*;
import game.GamePanel;
import game.TileManager;
import items.weapons.AttackItem;
import items.weapons.weaponsFactories.VoltCannonFactory;
import items.weapons.weaponsFactories.WeaponFactory;

import java.awt.*;
import java.util.HashMap;

/**
 * Public class map.
 * @see Map
 */
public class CaveMap extends Map{
    /**
     * Public constructor.
     */
    public CaveMap(GamePanel gamePanel) {
        super(gamePanel, "cave");
        tileManager = new TileManager(gamePanel, "cave");
        defaultXSpawn = 33;
        defaultYSpawn = 7;
        /*defaultXFinished = 21;
        defaultYFinished = 28;*/
        //finishedMapArea = new Rectangle(gamePanel.getTileSize()*21, gamePanel.getTileSize()*28, gamePanel.getTileSize()*3, gamePanel.getTileSize()*2);
        loadEntities();
        //index 0
        getMultipleBattleBackgrounds().add("/backgrounds/crystal_cave.png");
        //index 1
        getMultipleBattleBackgrounds().add("/backgrounds/sentinel_cave.png");
        //index 2
        getMultipleBattleBackgrounds().add("/backgrounds/infant_cave.png");
        //index 3
        getMultipleBattleBackgrounds().add("./backgrounds/boss_cave.png");
    }
    /**
     * Reset the battle zones.
     */
    @Override
    public void resetBattleZones(){
        startBattleZone = new HashMap<>();
        startBattleZone.put(1, new Rectangle(gamePanel.getTileSize()*32, gamePanel.getTileSize()*12, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(2, new Rectangle(gamePanel.getTileSize()*40, gamePanel.getTileSize()*13, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(3, new Rectangle(gamePanel.getTileSize()*40, gamePanel.getTileSize()*22, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(4, new Rectangle(gamePanel.getTileSize()*40, gamePanel.getTileSize()*37, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(5, new Rectangle(gamePanel.getTileSize()*29, gamePanel.getTileSize()*37, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(6, new Rectangle(gamePanel.getTileSize()*19, gamePanel.getTileSize()*38, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(7, new Rectangle(gamePanel.getTileSize()*8, gamePanel.getTileSize()*32, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(8, new Rectangle(gamePanel.getTileSize()*19, gamePanel.getTileSize()*20, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
        startBattleZone.put(9, new Rectangle(gamePanel.getTileSize()*8, gamePanel.getTileSize()*20, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3));
    }

    /**
     * Load the map.
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
        gamePanel.getUi().setFinishedCaveCinematic(false);
        gamePanel.getUi().setFinishedFinalCinematic(false);
        gamePanel.getCollisionChecker().setCaveCinematic(false);
        gamePanel.getUi().setOpacity(0.0f);
        //System.out.println("Loaded map: "+super.getMapName());
    }
    /**
     * Load the entities.
     */
    @Override
    public void loadEntities(){
        Entity enemy;
        EntityFactory eF;
        eF = new InfantFactory();
        enemy = eF.createEntity();
        setEnemy(enemy);
        eF = new SentinelFactory();
        enemy = eF.createEntity();
        getMultipleEnemies().add(enemy);
        eF = new BossFactory();
        enemy = eF.createEntity();
        WeaponFactory wF;
        wF = new VoltCannonFactory();
        AttackItem weapon = wF.createWeapon();
        enemy.setWeapon(weapon);
        //enemy.setName("Bob");
        setBoss(enemy);
        //System.out.println("Loaded entities.");*/

        if((gamePanel.getNpcs()[2]==null&&!gamePanel.getUi().isFinishedCaveCinematic())){
            Entity entity = new Ema(gamePanel);
            entity.setDirection("down");
            entity.setWorldX(gamePanel.getTileSize()*20);
            entity.setWorldY(gamePanel.getTileSize()*10);
            entity.setMoving(false);
            entity.setSpriteNum(0);
            gamePanel.getNpcs()[2] = entity;
            entity = new Bob(gamePanel);
            entity.setDirection("down");
            entity.setWorldX(gamePanel.getTileSize()*20);
            entity.setWorldY(gamePanel.getTileSize()*12);
            entity.setMoving(false);
            entity.setSpriteNum(0);
            gamePanel.getNpcs()[3] = entity;
            entity = new Sentinel(gamePanel);
            entity.setDirection("down");
            entity.setWorldX(gamePanel.getTileSize()*19);
            entity.setWorldY(gamePanel.getTileSize()*10);
            entity.setMoving(false);
            entity.setSpriteNum(0);
            gamePanel.getNpcs()[4] = entity;
            entity = new Sentinel(gamePanel);
            entity.setDirection("down");
            entity.setWorldX(gamePanel.getTileSize()*21);
            entity.setWorldY(gamePanel.getTileSize()*10);
            entity.setMoving(false);
            entity.setSpriteNum(0);
            gamePanel.getNpcs()[5] = entity;
        }
    }
}

