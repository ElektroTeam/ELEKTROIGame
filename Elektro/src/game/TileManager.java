package game;

import entities.Bob;
import entities.Ema;
import entities.Entity;
import entities.Sentinel;
import enums.GameState;
import org.w3c.dom.css.Rect;
import utilities.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Manages the tiles in the game, including loading and drawing them.
 */
public class TileManager{
    private GamePanel gamePanel;
    protected Tile[] tiles;
    protected int mapTileNum[][];
    protected ArrayList<String> fileNames = new ArrayList<>();
    protected ArrayList<String> colissionStatus = new ArrayList<>();
    /**
     * Constructor for TileManager.
     * @param gamePanel the game panel
     * @param mapName   the name of the map
     */
    public TileManager(GamePanel gamePanel, String mapName){
        this.gamePanel = gamePanel;

        // Read tile data
        InputStream inputStream = getClass().getResourceAsStream("/maps/tiles.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        // Getting tile names and conlission info from file
        String line = "";
        try{
            while((line = bufferedReader.readLine())!=null){
                fileNames.add(line);
                colissionStatus.add(bufferedReader.readLine());
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        // Initialize the tile array based on the file size
        //System.out.println(fileNames.size());
        tiles = new Tile[fileNames.size()];
        getTileImage();
        // Get the max world col & row
        inputStream = getClass().getResourceAsStream("/maps/"+mapName+".txt");
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try{
            line = bufferedReader.readLine();
            String maxTile[] = line.split(" ");
            gamePanel.setMaxWorldCol(maxTile.length);
            gamePanel.setMaxWorldRow(maxTile.length);
            mapTileNum = new int[gamePanel.getMaxWorldCol()][gamePanel.getMaxWorldRow()];
            bufferedReader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        loadMap("/maps/"+mapName+".txt");
    }
    /**
     * Loads tile images and sets their collision status.
     */
    public void getTileImage(){
        for(int i = 0; i < fileNames.size(); i++){
            String fileName;
            boolean colission;
            fileName = fileNames.get(i);
            if(colissionStatus.get(i).equals("true")){
                colission = true;
            } else {
                colission = false;
            }
            setup(i, fileName, colission);
        }
    }
    /**
     * Sets up a tile with its image and collision status.
     * @param index      the index of the tile
     * @param imageName  the name of the image file
     * @param colission  the collision status of the tile
     */
    public void setup(int index, String imageName, boolean colission){
        try {
            tiles[index] = new Tile();
            tiles[index].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName)));
            tiles[index].setImage(UtilityTool.scaleImage(tiles[index].getImage(), gamePanel.getTileSize(), gamePanel.getTileSize()));
            tiles[index].setColission(colission);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    /**
     * Loads the map data from a file.
     * @param filePath the path to the map file.
     */
    public void loadMap(String filePath){
        try {
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            int col = 0;
            int row = 0;
            while((col < gamePanel.getMaxWorldCol()) && (row < gamePanel.getMaxWorldRow())) {
                String line = bufferedReader.readLine();
                while(col < gamePanel.getMaxWorldCol()){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gamePanel.getMaxWorldCol()){
                    col = 0;
                    row++;
                }
            }
            bufferedReader.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * Draws the tiles on the screen.
     * @param g2d the graphics context
     */
    public void draw(Graphics2D g2d){
        try {
            int worldCol = 0;
            int worldRow = 0;
            while((worldCol<gamePanel.getMaxWorldCol()) && (worldRow<gamePanel.getMaxWorldRow())){
                int tileNum = mapTileNum[worldCol][worldRow];
                int worldX = worldCol*gamePanel.getTileSize();
                int worldY = worldRow*gamePanel.getTileSize();
                int screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().screenX;
                int screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().screenY;
                if((worldX+gamePanel.getTileSize()>(gamePanel.getPlayer().getWorldX()-gamePanel.getPlayer().screenX))&&(worldX-gamePanel.getTileSize()<(gamePanel.getPlayer().getWorldX()+gamePanel.getPlayer().screenX))&&(worldY+gamePanel.getTileSize()>(gamePanel.getPlayer().getWorldY()-gamePanel.getPlayer().screenY))&&(worldY-gamePanel.getTileSize()<(gamePanel.getPlayer().getWorldY()+gamePanel.getPlayer().screenY))){
                    g2d.drawImage(tiles[tileNum].getImage(), screenX, screenY, null);
                    // Check hitbox
                    //g2d.setStroke(new BasicStroke(1));
                    //g2d.drawRect(screenX, screenY, gamePanel.getTileSize(), gamePanel.getTileSize());
                }
                worldCol++;
                if(worldCol==gamePanel.getMaxWorldCol()){
                    worldCol = 0;
                    worldRow++;
                }
            }
            if(gamePanel.getKeyboardHandler().isDrawBattleAreas()){
                gamePanel.getCurrentMap().getStartBattleZone().forEach((k, v) ->{
                    if((v!=null)){
                        g2d.setColor(new Color(141, 122, 122, 116));
                        int screenX = v.x - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().screenX;
                        int screenY = v.y - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().screenY;
                        if((v.x+gamePanel.getTileSize()>(gamePanel.getPlayer().getWorldX()-gamePanel.getPlayer().screenX))&&(v.x-gamePanel.getTileSize()<(gamePanel.getPlayer().getWorldX()+gamePanel.getPlayer().screenX))&&(v.y+gamePanel.getTileSize()>(gamePanel.getPlayer().getWorldY()-gamePanel.getPlayer().screenY))&&(v.y-gamePanel.getTileSize()<(gamePanel.getPlayer().getWorldY()+gamePanel.getPlayer().screenY))){
                            g2d.fillRect(screenX, screenY, v.width, v.height);
                        }
                    }
                });
            }
            // Draw final cinematic
            if(gamePanel.getGameState() == GameState.FINAL_CINEMATIC_STATE){
                gamePanel.getNpcs()[3].setSpriteNum(3);
                gamePanel.getNpcs()[3].setWorldY(gamePanel.getTileSize()*9);
                gamePanel.getNpcs()[4].setSpriteNum(3);
                gamePanel.getNpcs()[4].setWorldY(gamePanel.getTileSize()*9);
                gamePanel.getNpcs()[5].setSpriteNum(4);
                gamePanel.getNpcs()[5].setWorldY(gamePanel.getTileSize()*9);
                gamePanel.getNpcs()[2].setDirection("down");
                gamePanel.getNpcs()[2].setSpeed(2);
                gamePanel.getNpcs()[2].setMoving(true);
            }
            // Draw cave cinematic
            if(gamePanel.getCurrentMap().getMapName().equals("cave")){

            } else {
                gamePanel.getNpcs()[2] = null;
                gamePanel.getNpcs()[3] = null;
                gamePanel.getNpcs()[4] = null;
                gamePanel.getNpcs()[5] = null;
            }
            // Draw mall cinematic
            if(gamePanel.getCurrentMap().getMapName().equals("mall")){

                if(gamePanel.getNpcs()[8]==null&&gamePanel.getUi().getCurrentCutsceneCode().equals("enter_bob")){
                    Entity bob = new Bob(gamePanel);
                    bob.setDirection("down");
                    bob.setMoving(true);
                    bob.setWorldX(gamePanel.getTileSize()*17);
                    bob.setWorldY(gamePanel.getTileSize()*27);
                    gamePanel.getNpcs()[8] = bob;
                    Entity sentinel = new Sentinel(gamePanel);
                    sentinel.setDirection("down");
                    sentinel.setMoving(true);
                    sentinel.setWorldX(gamePanel.getTileSize()*16);
                    sentinel.setWorldY(gamePanel.getTileSize()*28);
                    gamePanel.getNpcs()[7] = sentinel;
                    sentinel = new Sentinel(gamePanel);
                    sentinel.setDirection("down");
                    sentinel.setMoving(true);
                    sentinel.setWorldX(gamePanel.getTileSize()*18);
                    sentinel.setWorldY(gamePanel.getTileSize()*28);
                    gamePanel.getNpcs()[6] = sentinel;
                }
            } else {
                gamePanel.getNpcs()[6] = null;
                gamePanel.getNpcs()[7] = null;
                gamePanel.getNpcs()[8] = null;
                gamePanel.getNpcs()[9] = null;
            }
            // Draw mall names
            if(gamePanel.getCurrentMap().getMapName().equals("mall")){
                String text;
                int x, y;
                // Draw mall name
                text = "GALLERIES";
                x = (gamePanel.getTileSize()*8)+20;
                y = gamePanel.getTileSize()*8;
                g2d.setFont(g2d.getFont().deriveFont(55F));
                g2d.setColor(Color.WHITE);
                int screenX = x - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().screenX;
                int screenY = y - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().screenY;
                g2d.drawString(text, screenX, screenY);
                text = "CACAO \nCITY";
                x = gamePanel.getTileSize()*21;
                y = gamePanel.getTileSize()*17;
                screenX = x - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().screenX;
                screenY = y - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().screenY;
                g2d.setFont(g2d.getFont().deriveFont(24F));
                g2d.setColor(Color.YELLOW);
                gamePanel.getUi().drawMultipleLines(text, screenX, screenY);
                text = "MANUEL \nFARMACY";
                x = gamePanel.getTileSize()*34;
                screenX = x - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().screenX;
                screenY = y - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().screenY;
                g2d.setColor(Color.BLACK);
                gamePanel.getUi().drawMultipleLines(text, screenX, screenY);
                text = "WC \nWONALDS";
                x = gamePanel.getTileSize()*12;
                screenX = x - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().screenX;
                g2d.setColor(Color.WHITE);
                g2d.drawString(text, screenX, screenY);
                text = "SIARS";
                x = gamePanel.getTileSize()*10;
                y = gamePanel.getTileSize()*39-10;
                screenX = x - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().screenX;
                screenY = y - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().screenY;
                g2d.drawString(text, screenX, screenY);
            }
            if(gamePanel.isDeveloperMode()){

                if(gamePanel.getCurrentMap().getFinishedMapArea()!=null){
                    g2d.setColor(new Color(245, 101, 101, 116));
                    int screenX = gamePanel.getCurrentMap().getFinishedMapArea().x - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().screenX;
                    int screenY = gamePanel.getCurrentMap().getFinishedMapArea().y - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().screenY;
                    if((gamePanel.getCurrentMap().getFinishedMapArea().x+gamePanel.getTileSize()>(gamePanel.getPlayer().getWorldX()-gamePanel.getPlayer().screenX))&&(gamePanel.getCurrentMap().getFinishedMapArea().x-gamePanel.getTileSize()<(gamePanel.getPlayer().getWorldX()+gamePanel.getPlayer().screenX))&&(gamePanel.getCurrentMap().getFinishedMapArea().y+gamePanel.getTileSize()>(gamePanel.getPlayer().getWorldY()-gamePanel.getPlayer().screenY))&&(gamePanel.getCurrentMap().getFinishedMapArea().y-gamePanel.getTileSize()<(gamePanel.getPlayer().getWorldY()+gamePanel.getPlayer().screenY))){
                        // Check hitbox
                        //g2d.setStroke(new BasicStroke(1));
                        //g2d.drawRect(screenX, screenY, gamePanel.getTileSize(), gamePanel.getTileSize());
                        g2d.fillRect(screenX, screenY, gamePanel.getCurrentMap().getFinishedMapArea().width, gamePanel.getCurrentMap().getFinishedMapArea().height);
                    }

                }
            }
        } catch(NullPointerException e){
            // Do something later
        }
    }

    public GamePanel getGamePanel() {return gamePanel;}
    public void setGamePanel(GamePanel gamePanel) {this.gamePanel = gamePanel;}
    public Tile[] getTiles() {return tiles;}
    public void setTiles(Tile[] tiles) {this.tiles = tiles;}
    public int[][] getMapTileNum() {return mapTileNum;}
    public void setMapTileNum(int[][] mapTileNum) {this.mapTileNum = mapTileNum;}
    public ArrayList<String> getFileNames() {return fileNames;}
    public void setFileNames(ArrayList<String> fileNames) {this.fileNames = fileNames;}
    public ArrayList<String> getColissionStatus() {return colissionStatus;}
    public void setColissionStatus(ArrayList<String> colissionStatus) {this.colissionStatus = colissionStatus;}
}
