package entities;

import game.GamePanel;
import game.KeyboardHandler;
import game.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyboardHandler keyboardHandler;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    public int standCounter;
    public Player(GamePanel gamePanel, KeyboardHandler keyboardHandler) {
        this.gamePanel = gamePanel;
        this.keyboardHandler = keyboardHandler;
        screenX = (gamePanel.screenWidth/2)-(gamePanel.tileSize/2);
        screenY = (gamePanel.screenHeight/2)-(gamePanel.tileSize/2);
        solidArea = new Rectangle(8, 14, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        // Default spawn position
        // When looking in the map file, use [y][2x] or [line][column]
        worldX = gamePanel.tileSize*10;
        worldY = gamePanel.tileSize*30;
        speed = 4;
        direction = "down";
    }
    public void update(){
        if(keyboardHandler.upPressed||keyboardHandler.downPressed||keyboardHandler.leftPressed||keyboardHandler.rightPressed){
            if(keyboardHandler.upPressed){
                direction = "up";
            } else if(keyboardHandler.downPressed){
                direction = "down";
            } else if(keyboardHandler.leftPressed){
                direction = "left";
            } else if(keyboardHandler.rightPressed){
                direction = "right";
            }
            // Check tile colission
            collissionOn = false;
            gamePanel.colissionChecker.checkTile(this);
            // Check object colission
            int objectIndex = gamePanel.colissionChecker.checkObject(this, true);
            pickUpObject(objectIndex);
            // If colission is false, player can move
            if(collissionOn==false){
                switch (direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            spriteCounter++;
            if(spriteCounter>12){
                if(spriteNum==1){
                    spriteNum = 2;
                } else if(spriteNum==2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            standCounter++;
            if(standCounter==20){
                spriteNum = 1;
                standCounter = 0;
            }
        }
    }
    public void pickUpObject(int index){
        if(index!=999){
            String objectName = gamePanel.objects[index].name;
            switch (objectName){
                case "Key":
                    gamePanel.playSoundEffect(1);
                    hasKey++;
                    gamePanel.objects[index] = null;
                    gamePanel.ui.showMessage("You've found a key!");
                    break;
                case "Door":
                    gamePanel.playSoundEffect(3);
                    if(hasKey>0){
                        gamePanel.objects[index] = null;
                        hasKey--;
                        gamePanel.ui.showMessage("You've opened a door!");
                    } else {
                        gamePanel.ui.showMessage("You need a key!");
                    }
                    break;
                case "Boots":
                    gamePanel.playSoundEffect(2);
                    speed += 1;
                    gamePanel.objects[index] = null;
                    gamePanel.ui.showMessage("Speed up!");
                    break;
                case "Chest":
                    gamePanel.ui.gameFinished = true;
                    gamePanel.stopMusic();
                    gamePanel.playSoundEffect(4);
                    break;
            }
        }
    }
    public void draw(Graphics2D g2d){
        // Paint the components
/*        g2d.setColor(Color.WHITE);
        g2d.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);*/
        BufferedImage image = null;
        switch(direction){
            case "up":
                if(spriteNum==1){
                    image = up1;
                }
                if (spriteNum==2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum==1){
                    image = down1;
                }
                if (spriteNum==2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum==1){
                    image = left1;
                }
                if (spriteNum==2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum==1){
                    image = right1;
                }
                if (spriteNum==2) {
                    image = right2;
                }
                break;
        }
        g2d.drawImage(image, screenX, screenY, null);
        // Troubleshoot the solid area
        //g2d.drawRect(screenX+solidArea.x, screenY+solidArea.y, solidArea.width, solidArea.height);
    }
    public void getPlayerImage(){

        up1 = setup("ray-back2");
        up2 = setup("ray-back3");
        down1 = setup("ray-front2");
        down2 = setup("ray-front3");
        left1 = setup("ray-left2");
        left2 = setup("ray-left3");
        right1 = setup("ray-right2");
        right2 = setup("ray-right3");
    }
    public BufferedImage setup(String imageName){
        BufferedImage image = null;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/player/"+imageName+".png"));
            image = UtilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }
}
