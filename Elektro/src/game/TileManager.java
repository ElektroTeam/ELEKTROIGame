package game;

import utilities.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TileManager{
    GamePanel gamePanel;
    Tile[] tiles;
    int mapTileNum[][];
    ArrayList<String> fileNames = new ArrayList<>();
    ArrayList<String> colissionStatus = new ArrayList<>();

    public TileManager(GamePanel gamePanel, String mapName){
        this.gamePanel = gamePanel;

        // Read tile data
        InputStream inputStream = getClass().getResourceAsStream("/maps/"+mapName+"_tiles.txt");
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
            gamePanel.maxWorldCol = maxTile.length;
            gamePanel.maxWorldRow = maxTile.length;
            mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
            bufferedReader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        loadMap("/maps/"+mapName+".txt");
    }
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
    public void setup(int index, String imageName, boolean colission){
        try {
            tiles[index] = new Tile();
            tiles[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName));
            tiles[index].image = UtilityTool.scaleImage(tiles[index].image, gamePanel.tileSize, gamePanel.tileSize);
            tiles[index].colission = colission;
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath){
        try {
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            int col = 0;
            int row = 0;
            while((col < gamePanel.maxWorldCol) && (row < gamePanel.maxWorldRow)) {
                String line = bufferedReader.readLine();
                while(col < gamePanel.maxWorldCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gamePanel.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            bufferedReader.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2d){
        int worldCol = 0;
        int worldRow = 0;
        while((worldCol<gamePanel.maxWorldCol) && (worldRow<gamePanel.maxWorldRow)){
            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol*gamePanel.tileSize;
            int worldY = worldRow*gamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
            if((worldX+gamePanel.tileSize>(gamePanel.player.worldX-gamePanel.player.screenX))&&(worldX-gamePanel.tileSize<(gamePanel.player.worldX+gamePanel.player.screenX))&&(worldY+gamePanel.tileSize>(gamePanel.player.worldY-gamePanel.player.screenY))&&(worldY-gamePanel.tileSize<(gamePanel.player.worldY+gamePanel.player.screenY))){
                g2d.drawImage(tiles[tileNum].image, screenX, screenY, null);
                // Check hitbox
                //g2d.drawRect(screenX, screenY, gamePanel.tileSize, gamePanel.tileSize);
            }
            worldCol++;
            if(worldCol==gamePanel.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
