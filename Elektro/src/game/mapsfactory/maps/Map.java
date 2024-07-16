package game.mapsfactory.maps;

import entities.Entity;
import game.GamePanel;
import game.TileManager;
import game.levelsfactory.levels.Level;
import items.Item;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Abstract class for maps.
 */
public abstract class Map {
    protected String mapName;
    protected Entity enemy;
    protected ArrayList<Entity> multipleEnemies;
    protected Entity boss;
    protected ArrayList<Item> items;
    protected boolean nextMap;
    protected GamePanel gamePanel;
    protected TileManager tileManager;
    protected BufferedImage worldMap;
    protected boolean miniMapOn =  false;
    protected int defaultXSpawn, defaultYSpawn;
    protected Rectangle finishedMapArea;
    //protected Rectangle[] startBattleZone = new Rectangle[20];
    protected HashMap<Integer, Rectangle> startBattleZone = new HashMap<>();
    protected boolean displayIntroCinematic = true;
    //public int defaultXFinished, defaultYFinished;
    protected String battleBackground;
    //in case the map has more than one battle (just cave for now)
    protected ArrayList<String> multipleBattleBackgrounds;
    /**
     * Public constructor.
     * @param gamePanel the game panel.
     * @param mapName the name of the map.
     */
    public Map(GamePanel gamePanel, String mapName) {
        this.gamePanel = gamePanel;
        this.mapName = mapName;
        //dont put the enemy because we alredy have a function
        multipleEnemies = new ArrayList<>();
        multipleBattleBackgrounds = new ArrayList<>();
        tileManager = new TileManager(gamePanel, mapName);
        items = new ArrayList<>();
        nextMap = false;
        createWorldMap();
    }
    /**
     * Create the world map.
     */
    public void createWorldMap(){
        worldMap = new BufferedImage(gamePanel.getTileSize()*gamePanel.getMaxWorldRow(), gamePanel.getTileSize()*gamePanel.getMaxWorldRow(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = worldMap.createGraphics();
        int col = 0;
        int row = 0;
        while(col < gamePanel.getMaxWorldCol() && row < gamePanel.getMaxWorldRow()){
            int tileNum = tileManager.getMapTileNum()[col][row];
            int x = gamePanel.getTileSize()*col;
            int y = gamePanel.getTileSize()*row;
            g2d.drawImage(tileManager.getTiles()[tileNum].getImage(), x, y, null);
            col++;
            if(col == gamePanel.getMaxWorldCol()){
                col = 0;
                row++;
            }
        }
    }
    /**
     * Draw the map in full screen.
     * @param g2d the Graphics in 2d to draw.
     */
    public void drawFullMapScreen(Graphics2D g2d){
        // Background
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight());
        // Draw map
        // Changed from 700 to 500
        int width = 500;
        int height = 500;
        int x = gamePanel.getScreenWidth()/2 - width/2;
        int y = gamePanel.getScreenHeight()/2 - height/2;
        g2d.drawImage(worldMap, x, y, width, height, null);
        // Draw player
        double scale = (double)(gamePanel.getTileSize()*gamePanel.getMaxWorldCol())/width;
        int entityX = (int)(x + gamePanel.getPlayer().getWorldX()/scale);
        int entityY = (int)(y + gamePanel.getPlayer().getWorldY()/scale);
        int entitySize = (int)(gamePanel.getTileSize()/scale);
        g2d.drawImage(gamePanel.getPlayer().getDown(), entityX, entityY, entitySize, entitySize, null);
        // Options
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));
        g2d.setColor(Color.WHITE);
        g2d.drawString("(ESC) CLOSE MAP", 50, 560);
        // Dev mode
        if(gamePanel.isDeveloperMode()){
            for(Entity entity: gamePanel.getNpcs()){
                if(entity!=null){
                    entityX = (int)(x+entity.getWorldX()/scale);
                    entityY = (int)(y+entity.getWorldY()/scale);
                    g2d.drawImage(entity.getDown(), entityX, entityY, entitySize, entitySize, null);
                }
            }
            startBattleZone.forEach((k, v) ->{
                if((v!=null)){
                    g2d.setColor(new Color(142, 215, 106, 169));
                    g2d.fillRect((int)(x+v.x/scale), (int)(y+v.y/scale), (int)(v.width/scale), (int)(v.height/scale));
                }
            });
            gamePanel.getObjects().forEach((k, v) -> {
                String[] keySplit = k.split("_");
                if(keySplit[0].toLowerCase().equals(mapName)){
                    if(!v.isCollected()){
                        g2d.drawImage(v.getImage(), (int)(x+v.getWorldX()/scale), (int)(y+v.getWorldY()/scale), entitySize, entitySize, null);
                    }
                }
            });
            if(gamePanel.getCurrentMap().finishedMapArea!=null){
                entityX = (int)(x+gamePanel.getCurrentMap().finishedMapArea.x/scale);
                entityY = (int)(y+gamePanel.getCurrentMap().finishedMapArea.y/scale);
                g2d.setColor(new Color(215, 106, 106, 169));
                g2d.fillRect(entityX, entityY, (int)(gamePanel.getCurrentMap().finishedMapArea.width/scale), (int)(gamePanel.getCurrentMap().finishedMapArea.height/scale));
            }
        }
    }

    /**
     * Reset the battle zones.
     */
    public void resetBattleZones(){}
    /**
     * Load the map.
     * This method loads the entities, items, and player spawn.
     */
    public void loadMap(){
        this.setNextMap(false);
        loadEntities();
        loadItems();
        loadPlayerSpawn();
        gamePanel.getPlayer().setDefaultPositions();
        gamePanel.getPlayer().setDefaultValues();
        displayIntroCinematic = true;
        //System.out.println("Loaded map: "+mapName);
    }
    /**
     * Load the entities.
     */
    public void loadEntities(){
        //System.out.println("Loaded entities.");
    }
    /**
     * Load the items.
     */
    public void loadItems(){
        //System.out.println("Loaded items.");
    }
    /**
     * Load the player spawn.
     */
    public void loadPlayerSpawn(){
        //System.out.println("Loaded player spawn.");
    }
    /**
     * Get the name of the map.
     * @return the name of the map.
     */
    public String getMapName() {return mapName;}
    /**
     * Set the name of the map.
     * @param mapName the name of the map.
     */
    public void setMapName(String mapName) {this.mapName = mapName;}
    /**
     * Get the arraylist of sentinels.
     * @return the list of sentinels.
     */
    //public ArrayList<Entity> getSentinels() {return sentinels;}
    /**
     * Set the arraylist of sentinels.
     * @param sentinels the list of sentinels.
     */
    //public void setSentinels(ArrayList<Entity> sentinels) {this.sentinels = sentinels;}
    /**
     * Get the arraylist of crystals.
     * @return the list of crystals.
     */
    //public ArrayList<Entity> getCrystals() {return crystals;}
    /**
     * Set the arraylist of crystals.
     * @param crystals the list of crystals.
     */
    //public void setCrystals(ArrayList<Entity> crystals) {this.crystals = crystals;}
    /**
     * Get the arraylist of infants.
     * @return the list of infants.
     */
    //public ArrayList<Entity> getInfants() {return infants;}
    /**
     * Set the arraylist of infants.
     * @param infants the list of infants.
     */
    //public void setInfants(ArrayList<Entity> infants) {this.infants = infants;}
    /**
     * Get the arraylist of wolves.
     * @return the list of wolves.
     */
    //public ArrayList<Entity> getWolves() {return wolves;}
    /**
     * Set the arraylist of wolves.
     * @param wolves the list of wolves.
     */
    //public void setWolves(ArrayList<Entity> wolves) {this.wolves = wolves;}
    /**
     * Validate if the next map is unlocked.
     * @return whether the next map is unlocked or not.
     */
    public boolean isNextMap() {return nextMap;}
    /**
     * Set the unlocked status of the next map.
     * @param nextMap whether the next map is unlocked or not.
     */
    public void setNextMap(boolean nextMap) {this.nextMap = nextMap;}
    /**
     * Get the arraylist of items.
     * @return the list of items.
     */
    public ArrayList<Item> getItems() {return items;}
    /**
     * Set the arraylist of items.
     * @param items the arraylist of items.
     */
    public void setItems(ArrayList<Item> items) {this.items = items;}
    /**
     * Get the boss entity.
     * @return the boss entity.
     */
    public Entity getBoss() {return boss;}
    /**
     * Set the boss entity.
     * @param boss the boss entity.
     */
    public void setBoss(Entity boss) {this.boss = boss;}
    /**
     * Get the world map.
     * @return the world map image.
     */
    public BufferedImage getWorldMap() {
        return worldMap;
    }
    /**
     * Set the world map.
     * @param worldMap the world map image.
     */
    public void setWorldMap(BufferedImage worldMap) {
        this.worldMap = worldMap;
    }
    /**
     * Check if minimap is on.
     * @return whether minimap is activated or not.
     */
    public boolean isMiniMapOn() {
        return miniMapOn;
    }

    /**
     * Set whether minimap is on or not.
     * @param miniMapOn if minimap is on.
     */
    public void setMiniMapOn(boolean miniMapOn) {
        this.miniMapOn = miniMapOn;
    }
    /**
     * Get the default X spawn location.
     * @return
     */
    public int getDefaultXSpawn() {
        return defaultXSpawn;
    }
    /**
     * Set the default X spawn location.
     * @param defaultXSpawn
     */
    public void setDefaultXSpawn(int defaultXSpawn) {
        this.defaultXSpawn = defaultXSpawn;
    }
    /**
     * Get the default Y spawn location.
     * @return
     */
    public int getDefaultYSpawn() {return defaultYSpawn;}
    /**
     * Set the default Y spawn location.
     * @param defaultYSpawn
     */
    public void setDefaultYSpawn(int defaultYSpawn) {
        this.defaultYSpawn = defaultYSpawn;
    }
    /**
     * Get the area to finish the map.
     * @return
     */
    public Rectangle getFinishedMapArea() {return finishedMapArea;}
    /**
     * Set the area to finish the map.
     * @param finishedMapArea
     */
    public void setFinishedMapArea(Rectangle finishedMapArea) {this.finishedMapArea = finishedMapArea;}
    /**
     * Get the rectangles to start a battle.
     * @return
     */
    public HashMap<Integer, Rectangle> getStartBattleZone(){return startBattleZone;}
    /**
     * Set the rectangles to start a battle.
     * @param startBattleZone
     */
    public void setStartBattleZone(HashMap startBattleZone){this.startBattleZone = startBattleZone;}
    /**
     * Check if the intro cinematic has been displayed.
     * @return
     */
    public boolean isDisplayIntroCinematic() {return displayIntroCinematic;}
    /**
     * Set whether the intro cinematic has been displayed or not.
     * @param displayIntroCinematic
     */
    public void setDisplayIntroCinematic(boolean displayIntroCinematic) {this.displayIntroCinematic = displayIntroCinematic;}
    /**
     * Get the tile manager.
     * @return
     */
    public TileManager getTileManager(){return tileManager;}
    /**
     * Set the tile manager.
     * @param tileManager
     */
    public void setTileManager(TileManager tileManager){this.tileManager = tileManager;}
    /**
     * Get the battle background.
     * @return
     */
    public String getBattleBackground() {return battleBackground;}
    /**
     * Set the battle background
     * @param battleBackground
     */
    public void setBattleBackground(String battleBackground) {this.battleBackground = battleBackground;}
    /**
     * Get the map enemy.
     * @return
     */
    public Entity getEnemy() {return enemy;}
    /**
     * Set the map enemy.
     * @param enemy
     */
    public void setEnemy(Entity enemy) {this.enemy = enemy;}
    /**
     * Get the arraylist of enemies (if applies)-
     * @return
     */
    public ArrayList<Entity> getMultipleEnemies() {return multipleEnemies;}
    /**
     * Set the arraylist of multiple enemies.
     * @param multipleEnemies
     */
    public void setMultipleEnemies(ArrayList<Entity> multipleEnemies) {this.multipleEnemies = multipleEnemies;}
    /**
     * Get the multiple battle backgrounds.
     * @return
     */
    public ArrayList<String> getMultipleBattleBackgrounds() {return multipleBattleBackgrounds;}
    /**
     * Set the multiple battle backgrounds.
     * @param multipleBattleBackgrounds
     */
    public void setMultipleBattleBackgrounds(ArrayList<String> multipleBattleBackgrounds) {this.multipleBattleBackgrounds = multipleBattleBackgrounds;}
}
