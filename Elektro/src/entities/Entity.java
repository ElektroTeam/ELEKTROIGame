package entities;

import game.GamePanel;
import utilities.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Abstract class which represents an entity in the game.
 */
public abstract class Entity {
/*    private String name;
    private int HP;
    private AttackItem weapon;
    private ArrayList<HealerItem> healingsItems;*/
    protected GamePanel gamePanel;
    public int worldX, worldY;
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collissionOn = false;
    public int actionLockCounter = 0;
    String dialogues[] = new String[20];
    int dialogueIndex = 0;

    public Entity(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    public void setAction(){}
    public void speak(){
        if(dialogues[dialogueIndex]==null){
            dialogueIndex=0;
        }
        gamePanel.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
        switch (gamePanel.player.direction){
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;

        }
    }
    public void update(){
        setAction();
        collissionOn = false;
        gamePanel.colissionChecker.checkTile(this);
        gamePanel.colissionChecker.checkObject(this, false);
        gamePanel.colissionChecker.checkPlayer(this);
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
    }
    public void draw(Graphics2D g2d){
        BufferedImage image = null;
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
        if((worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX)&&(worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX)&&(worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY)&&(worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY)){
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
            //g2d.drawRect(worldX+solidArea.x, worldY+solidArea.y, solidArea.width, solidArea.height);
        }

    }
    public BufferedImage setup(String imagePath){
        BufferedImage image = null;
        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            image = UtilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }
}