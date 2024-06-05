package levels;

import entities.Entity;
import game.GamePanel;

public class ColissionChecker {
    private GamePanel gamePanel;
    public ColissionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public void checkTile(Entity entity){
        int entityLeftWorldX = (entity.worldX+entity.solidArea.x);
        int entityRightWorldX = (entity.worldX+entity.solidArea.x+entity.solidArea.width);
        int entityTopWorldY = (entity.worldY+entity.solidArea.y);
        int entityBottomWorldY = (entity.worldY+entity.solidArea.y+entity.solidArea.height);

        int entityLeftCol = (entityLeftWorldX/gamePanel.tileSize);
        int entityRightCol = (entityRightWorldX/gamePanel.tileSize);
        int entityTopRow = (entityTopWorldY/gamePanel.tileSize);
        int entityBottomRow = (entityBottomWorldY/gamePanel.tileSize);

        int tileNum1, tileNum2;
        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY-entity.speed)/(gamePanel.tileSize);
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                if(gamePanel.tileManager.tiles[tileNum1].colission==true||gamePanel.tileManager.tiles[tileNum2].colission==true){
                    entity.collissionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityTopWorldY+entity.speed)/(gamePanel.tileSize);
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(gamePanel.tileManager.tiles[tileNum1].colission==true||gamePanel.tileManager.tiles[tileNum2].colission==true){
                    entity.collissionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX-entity.speed)/(gamePanel.tileSize);
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if(gamePanel.tileManager.tiles[tileNum1].colission==true||gamePanel.tileManager.tiles[tileNum2].colission==true){
                    entity.collissionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX+entity.speed)/(gamePanel.tileSize);
                tileNum1 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(gamePanel.tileManager.tiles[tileNum1].colission==true||gamePanel.tileManager.tiles[tileNum2].colission==true){
                    entity.collissionOn = true;
                }
                break;
        }
    }
    public int checkObject(Entity entity, boolean player){
        int index = 999;
        for (int i = 0; i < gamePanel.objects.length; i++){
            if(gamePanel.objects[i]!=null){
                // Get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                // Get the object's solid area position
                gamePanel.objects[i].solidArea.x = gamePanel.objects[i].worldX + gamePanel.objects[i].solidArea.x;
                gamePanel.objects[i].solidArea.y = gamePanel.objects[i].worldY + gamePanel.objects[i].solidArea.y;

                switch(entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gamePanel.objects[i].solidArea)){
                            if(gamePanel.objects[i].colission == true){
                                entity.collissionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gamePanel.objects[i].solidArea)){
                            if(gamePanel.objects[i].colission == true){
                                entity.collissionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gamePanel.objects[i].solidArea)){
                            if(gamePanel.objects[i].colission == true){
                                entity.collissionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gamePanel.objects[i].solidArea)){
                            if(gamePanel.objects[i].colission == true){
                                entity.collissionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gamePanel.objects[i].solidArea.x = entity.solidAreaDefaultX;
                gamePanel.objects[i].solidArea.y = entity.solidAreaDefaultY;
            }
        }
        return index;
    }
}
