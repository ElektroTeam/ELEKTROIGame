package entities.player;
import entities.Entity;
import game.GamePanel;
import game.KeyboardHandler;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends Entity {
    private KeyboardHandler keyboardHandler;
    public final int screenX;
    public final int screenY;
    public int standCounter;
    public Player(GamePanel gamePanel, KeyboardHandler keyboardHandler) {
        super(gamePanel);
        this.keyboardHandler = keyboardHandler;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        screenX = (gamePanel.screenWidth/2)-(gamePanel.tileSize/2);
        screenY = (gamePanel.screenHeight/2)-(gamePanel.tileSize/2);
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        // Default spawn position
        // When looking in the map file, use [y][2x] or [line][column]
        worldX = gamePanel.tileSize*42;
        worldY = gamePanel.tileSize*27;
        speed = 3;
        direction = "down";
        maxLife = 6;
        life = maxLife;
    }
    @Override
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
            // Check  npc colission
            int npcIndex = gamePanel.colissionChecker.checkEntity(this, gamePanel.npcs);
            interactNPC(npcIndex);
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
        }
    }
    public void interactNPC(int index){
        if(index!=999){
            if(gamePanel.keyboardHandler.enterPressed){
                //System.out.println("Enter pressed, entering dialogue mode");
                gamePanel.gameState = gamePanel.dialogueState;
                gamePanel.npcs[index].speak();
            }
        }
        gamePanel.keyboardHandler.enterPressed = false;
    }
    @Override
    public void draw(Graphics2D g2d){
        BufferedImage image = null;
        if((worldX+gamePanel.tileSize>(worldX-gamePanel.player.screenX))&&(worldX-gamePanel.tileSize<(worldX+gamePanel.player.screenX))&&(worldY+gamePanel.tileSize>(worldY-gamePanel.player.screenY))&&(worldY-gamePanel.tileSize<(worldY+gamePanel.player.screenY))){
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
            //System.out.println("Screen X: "+screenX+" | Screen Y:"+screenY);
            //System.out.println("World X: "+worldX+" | World Y: "+worldY);
            g2d.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            // Troubleshoot the solid area
            //System.out.println(gamePanel.player.screenX+" - " + solidArea.x + " - " + gamePanel.player.screenY + " - "+ solidArea.y);
            //g2d.drawRect(screenX+solidArea.x, screenY+solidArea.y, solidArea.width, solidArea.height);
        }
    }
    public void getPlayerImage(){

        up1 = setup("/entities/player/ray-back2.png");
        up2 = setup("/entities/player/ray-back3.png");
        down1 = setup("/entities/player/ray-front2.png");
        down2 = setup("/entities/player/ray-front3.png");
        left1 = setup("/entities/player/ray-left2.png");
        left2 = setup("/entities/player/ray-left3.png");
        right1 = setup("/entities/player/ray-right2.png");
        right2 = setup("/entities/player/ray-right3.png");
    }
}
