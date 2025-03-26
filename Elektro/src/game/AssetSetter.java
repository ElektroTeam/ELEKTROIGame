package game;

import items.*;

/**
 * Public class to set objects and npcs.
 */
public class AssetSetter {
    private GamePanel gamePanel;
    /**
     * Public constructor.
     * @param gamePanel
     */
    public AssetSetter(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    /**
     * Set the objects in the game.
     * This method sets the objects that will be used in all levels.
     */
    public void setObjects(){
        VisualObject object = null;
        gamePanel.getObjects().put("house_map", new MapObject(gamePanel));
        gamePanel.getObjects().put("origen_diary", new DiaryObject(gamePanel));
        gamePanel.getObjects().put("origen_bat", new BatObject(gamePanel));
        gamePanel.getObjects().put("mall_electricbat", new ElectricBatObject(gamePanel));
        gamePanel.getObjects().put("forest_berry_1", new BerryObject(gamePanel));
        object = new BerryObject(gamePanel);
        object.setWorldX(gamePanel.getTileSize()*17);
        object.setWorldY(gamePanel.getTileSize()*4);
        gamePanel.getObjects().put("forest_berry_2", object);
        object = new BerryObject(gamePanel);
        object.setWorldX(gamePanel.getTileSize()*22);
        object.setWorldY(gamePanel.getTileSize()*20);
        gamePanel.getObjects().put("forest_berry_3", object);
        object = new BerryObject(gamePanel);
        object.setWorldX(gamePanel.getTileSize()*20);
        object.setWorldY(gamePanel.getTileSize()*32);
        gamePanel.getObjects().put("forest_berry_4", object);
        object = new BerryObject(gamePanel);
        object.setWorldX(gamePanel.getTileSize()*33);
        object.setWorldY(gamePanel.getTileSize()*38);
        gamePanel.getObjects().put("forest_berry_5", object);
        object = new BerryObject(gamePanel);
        object.setWorldX(gamePanel.getTileSize()*45);
        object.setWorldY(gamePanel.getTileSize()*48);
        gamePanel.getObjects().put("forest_berry_6", object);
        gamePanel.getObjects().put("cave_electricgun", new ElectricGunObject(gamePanel));
        object = new MedkitObject(gamePanel);
        object.setWorldX(gamePanel.getTileSize()*22);
        object.setWorldY(gamePanel.getTileSize()*37);
        gamePanel.getObjects().put("parkinglot_medkit_1", object);
        object = new MedkitObject(gamePanel);
        object.setWorldX(gamePanel.getTileSize()*12);
        object.setWorldY(gamePanel.getTileSize()*32);
        gamePanel.getObjects().put("parkinglot_medkit_2", object);
        object = new MedkitObject(gamePanel);
        object.setWorldX(gamePanel.getTileSize()*41);
        object.setWorldY(gamePanel.getTileSize()*18);
        gamePanel.getObjects().put("mall_medkit_1", object);
        object = new MedkitObject(gamePanel);
        object.setWorldX(gamePanel.getTileSize()*32);
        object.setWorldY(gamePanel.getTileSize()*23);
        gamePanel.getObjects().put("mall_medkit_2", object);
        object = new BandageObject(gamePanel);
        object.setWorldX(gamePanel.getTileSize()*11);
        object.setWorldY(gamePanel.getTileSize()*27);
        gamePanel.getObjects().put("house_bandage_1", object);
    }

    /**
     * Set the NPCs in the game.
     */
    public void setNPCs(){
        //gamePanel.npcs[0] = new Ema(gamePanel);
    }
}
