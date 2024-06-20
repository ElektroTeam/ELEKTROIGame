package game;


import entities.Entity;
import items.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Map1 {
    public String mapName;
    public ArrayList<Entity> sentinels;
    public ArrayList<Entity> crystals;
    public ArrayList<Entity> infants;
    public ArrayList<Entity> wolves;
    public Entity boss;
    public ArrayList<SuperObject> items;
    public boolean nextMap;
    private GamePanel gamePanel;
    public TileManager tileManager;
    public BufferedImage worldMap;
    public boolean miniMapOn =  false;

    public Map1(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tileManager = new TileManager(gamePanel, "house");
        mapName = "House";
        sentinels = new ArrayList<>();
        crystals = new ArrayList<>();
        infants = new ArrayList<>();
        wolves = new ArrayList<>();
        items = new ArrayList<>();
        nextMap = false;
        createWorldMap();
    }
    public void loadMap(){
        nextMap = false;
        loadEntities();
        loadItems();
        loadPlayerSpawn();
        //tileManager.loadMap("house");
        tileManager.draw(gamePanel.g2d);
        //System.out.println("Loaded map: "+mapName);
    }
    public void loadEntities(){
        //System.out.println("Loaded entities.");
    }
    public void loadItems(){
        //System.out.println("Loaded items.");
    }
    public void loadPlayerSpawn(){
        //System.out.println("Loaded player spawn.");
    }
    public void playMap(){
        //System.out.println("playing map '"+this.mapName+"'");
        this.nextMap = true;
    }
    public void createWorldMap(){
        int worldMapWidth = gamePanel.tileSize*gamePanel.maxWorldCol;
        int worldMapHeight = gamePanel.tileSize*gamePanel.maxWorldRow;
        worldMap = new BufferedImage(worldMapWidth, worldMapHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) worldMap.createGraphics();
        int col = 0, row = 0;
        while(col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow){
            int tileNum = tileManager.mapTileNum[col][row];
            int x = gamePanel.tileSize*col;
            int y = gamePanel.tileSize*row;
            g2d.drawImage(tileManager.tiles[tileNum].image, x, y, null);
            col++;
            if(col == gamePanel.maxWorldCol){
                col = 0;
                row++;
            }
        }
    }
    public void drawFullMapScreen(Graphics2D g2d){
        // Background color
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);
        // Draw map
        int width = 500;
        int height = 500;
        int x = gamePanel.screenWidth/2 - width/2;
        int y = gamePanel.screenHeight/2 - height/2;
        g2d.drawImage(worldMap, x, y, null);

    }
}