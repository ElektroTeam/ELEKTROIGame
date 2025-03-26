package entities;

import enums.AttackSpeed;
import enums.GameState;
import game.GamePanel;
import items.consumables.HealerItem;
import items.weapons.AttackItem;
import utilities.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Abstract class which represents an entity in the game.
 */
public abstract class Entity {
    protected String name;
    protected int HP;
    protected AttackSpeed attackSpeed;
    protected AttackItem weapon;
    protected ArrayList<HealerItem> healingsItems;
    protected ArrayList<AttackItem> weaponsBag;
    protected GamePanel gamePanel;
    protected int worldX, worldY;
    protected int speed;
    protected BufferedImage up, up1, up2, down, down1, down2, left, left1, left2, right, right1, right2, defeated1, defeated2;
    protected String direction;
    protected int spriteCounter = 0;
    protected int spriteNum = 0;
    protected Rectangle solidArea;
    protected int solidAreaDefaultX, solidAreaDefaultY;
    protected boolean collissionOn = false, moving = true;
    protected int actionLockCounter = 0;
    protected String dialogues[][] = new String[20][20];
    protected int dialogueSet = 0;
    protected int dialogueIndex = 0;
    protected int maxLife;
    protected int life;
    /**
     * Public constructor with parameters.
     * @param HP the health points of the entity.
     * @param attackSpeed the entity speed.
     * @param weapon the weapon the entity uses.
     */
    public Entity(int HP, AttackSpeed attackSpeed, AttackItem weapon, GamePanel gamePanel) {
        this.HP = HP;
        this.attackSpeed = attackSpeed;
        this.weapon = weapon;
        healingsItems = new ArrayList<>();
        weaponsBag = new ArrayList<>();
        this.gamePanel = gamePanel;
        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public Entity(int HP, AttackSpeed attackSpeed, AttackItem weapon){
        this.HP = HP;
        this.attackSpeed = attackSpeed;
        this.weapon = weapon;
        healingsItems = new ArrayList<>();
        weaponsBag = new ArrayList<>();
    }


    public Entity(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    public void setAction(){}
    public void speak(){

    }
    public void startDialogue(Entity entity, int setNum){
        gamePanel.setGameState(GameState.DIALOGUE_STATE);
        gamePanel.getUi().setNpc(entity);
        dialogueSet = setNum;
    }
    public void facePlayer(){
        switch (gamePanel.getPlayer().direction){
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
        gamePanel.getCollisionChecker().checkTile(this);
        gamePanel.getCollisionChecker().checkObject(this, false);
        gamePanel.getCollisionChecker().checkPlayer(this);
        if(moving){
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
                if(spriteNum==1||spriteNum==0){
                    spriteNum = 2;
                } else if(spriteNum==2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2d){
        BufferedImage image = null;
        int screenX = worldX - gamePanel.getPlayer().worldX + gamePanel.getPlayer().screenX;
        int screenY = worldY - gamePanel.getPlayer().worldY + gamePanel.getPlayer().screenY;
        if((worldX + gamePanel.getTileSize() > gamePanel.getPlayer().worldX - gamePanel.getPlayer().screenX)&&(worldX - gamePanel.getTileSize() < gamePanel.getPlayer().worldX + gamePanel.getPlayer().screenX)&&(worldY + gamePanel.getTileSize() > gamePanel.getPlayer().worldY - gamePanel.getPlayer().screenY)&&(worldY - gamePanel.getTileSize() < gamePanel.getPlayer().worldY + gamePanel.getPlayer().screenY)){
            switch(direction){
                case "up":
                    if(spriteNum==0){
                        image = up;
                    } else if(spriteNum==1){
                        image = up1;
                    } else if (spriteNum==2) {
                        image = up2;
                    }
                    break;
                case "down":
                    if(spriteNum==0){
                        image = down;
                    } else if(spriteNum==1){
                        image = down1;
                    } else if (spriteNum==2) {
                        image = down2;
                    }
                    break;
                case "left":
                    if(spriteNum==0){
                        image = left;
                    } else if(spriteNum==1){
                        image = left1;
                    } else if (spriteNum==2) {
                        image = left2;
                    }
                    break;
                case "right":
                    if(spriteNum==0){
                        image = right;
                    } else if(spriteNum==1){
                        image = right1;
                    } else if (spriteNum==2) {
                        image = right2;
                    }
                    break;
            }
            if(spriteNum==3){
                image = defeated1;
            } else if(spriteNum==4){
                image = defeated2;
            }
            //System.out.println("Screen X: "+screenX+" | Screen Y:"+screenY);
            //System.out.println("World X: "+worldX+" | World Y: "+worldY);
            g2d.drawImage(image, screenX, screenY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
            // Troubleshoot the solid area
            //System.out.println(gamePanel.getPlayer().screenX+" - " + solidArea.x + " - " + gamePanel.getPlayer().screenY + " - "+ solidArea.y);
            g2d.setColor(Color.WHITE);
            g2d.setStroke(new BasicStroke(6));
            //g2d.drawRect(screenX, screenY, solidArea.width, solidArea.height);
        }

    }
    public BufferedImage setup(String imagePath){
        BufferedImage image = null;
        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            image = UtilityTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize());
        } catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }

    public GamePanel getGamePanel() {return gamePanel;}
    public void setGamePanel(GamePanel gamePanel) {this.gamePanel = gamePanel;}
    public int getWorldX() {return worldX;}
    public void setWorldX(int worldX) {this.worldX = worldX;}
    public int getWorldY() {return worldY;}
    public void setWorldY(int worldY) {this.worldY = worldY;}
    public int getSpeed() {return speed;}
    public void setSpeed(int speed) {this.speed = speed;}
    public BufferedImage getUp() {return up;}
    public void setUp(BufferedImage up) {this.up = up;}
    public BufferedImage getUp1() {return up1;}
    public void setUp1(BufferedImage up1) {this.up1 = up1;}
    public BufferedImage getUp2() {return up2;}
    public void setUp2(BufferedImage up2) {this.up2 = up2;}
    public BufferedImage getDown() {return down;}
    public void setDown(BufferedImage down) {this.down = down;}
    public BufferedImage getDown1() {return down1;}
    public void setDown1(BufferedImage down1) {this.down1 = down1;}
    public BufferedImage getDown2() {return down2;}
    public void setDown2(BufferedImage down2) {this.down2 = down2;}
    public BufferedImage getLeft() {return left;}
    public void setLeft(BufferedImage left) {this.left = left;}
    public BufferedImage getLeft1() {return left1;}
    public void setLeft1(BufferedImage left1) {this.left1 = left1;}
    public BufferedImage getLeft2() {return left2;}
    public void setLeft2(BufferedImage left2) {this.left2 = left2;}
    public BufferedImage getRight() {return right;}
    public void setRight(BufferedImage right) {this.right = right;}
    public BufferedImage getRight1() {return right1;}
    public void setRight1(BufferedImage right1) {this.right1 = right1;}
    public BufferedImage getRight2() {return right2;}
    public void setRight2(BufferedImage right2) {this.right2 = right2;}
    public String getDirection() {return direction;}
    public void setDirection(String direction) {this.direction = direction;}
    public int getSpriteCounter() {return spriteCounter;}
    public void setSpriteCounter(int spriteCounter) {this.spriteCounter = spriteCounter;}
    public int getSpriteNum() {return spriteNum;}
    public void setSpriteNum(int spriteNum) {this.spriteNum = spriteNum;}
    public Rectangle getSolidArea() {return solidArea;}
    public void setSolidArea(Rectangle solidArea) {this.solidArea = solidArea;}
    public int getSolidAreaDefaultX() {return solidAreaDefaultX;}
    public void setSolidAreaDefaultX(int solidAreaDefaultX) {this.solidAreaDefaultX = solidAreaDefaultX;}
    public int getSolidAreaDefaultY() {return solidAreaDefaultY;}
    public void setSolidAreaDefaultY(int solidAreaDefaultY) {this.solidAreaDefaultY = solidAreaDefaultY;}
    public boolean isCollissionOn() {return collissionOn;}
    public void setCollissionOn(boolean collissionOn) {this.collissionOn = collissionOn;}
    public int getActionLockCounter() {return actionLockCounter;}
    public void setActionLockCounter(int actionLockCounter) {this.actionLockCounter = actionLockCounter;}
    public String[][] getDialogues() {return dialogues;}
    public void setDialogues(String[][] dialogues) {this.dialogues = dialogues;}
    public int getDialogueSet() {return dialogueSet;}
    public void setDialogueSet(int dialogueSet) {this.dialogueSet = dialogueSet;}
    public int getDialogueIndex() {return dialogueIndex;}
    public void setDialogueIndex(int dialogueIndex) {this.dialogueIndex = dialogueIndex;}
    public int getMaxLife() {return maxLife;}
    public void setMaxLife(int maxLife) {this.maxLife = maxLife;}
    public int getLife() {return life;}
    public void setLife(int life) {this.life = life;}
    public boolean isMoving() {return moving;}
    public void setMoving(boolean moving) {this.moving = moving;}
    /**
     * Get the name of the entity.
     * @return the name of the entity.
     */
    public String getName() {return name;}
    /**
     * Set the name of the entity.
     * @param name the new name of the entity.
     */
    public void setName(String name) {this.name = name;}
    /**
     * Get the health points of the entity.
     * @return the health points of the entity.
     */
    public int getHP() {return HP;}
    /**
     * Set the health points of the entity.
     * @param HP the new health points of the entity.
     */
    public void setHP(int HP) {this.HP = HP;}
    /**
     * Get the speed of the entity.
     * @return the speed of the entity.
     */
    public AttackSpeed getAttackSpeed() {return attackSpeed;}
    /**
     * Set the speed of the entity.
     * @param speed the new speed of the entity.
     */
    public void setAttackSpeed(AttackSpeed speed) {this.attackSpeed = speed;}
    /**
     * Get the weapon of the entity.
     * @return the weapon of the entity.
     */
    public AttackItem getWeapon() {return weapon;}
    /**
     * Set the weapon of the entity.
     * @param weapon the new weapon of the entity.
     */
    public void setWeapon(AttackItem weapon) {this.weapon = weapon;}
    /**
     * Gets the list of healing items of the entity.
     * @return the list of healing items of the entity.
     */
    public ArrayList<HealerItem> getHealingsItems() {return healingsItems;}
    /**
     * Set the list of healing items of the entity.
     * @param healingsItems the new list of healing items of the entity.
     */
    public void setHealingsItems(ArrayList<HealerItem> healingsItems) {this.healingsItems = healingsItems;}
    public ArrayList<AttackItem> getWeaponsBag() {
        return weaponsBag;
    }
    public void setWeaponsBag(ArrayList<AttackItem> weaponsBag) {
        this.weaponsBag = weaponsBag;
    }
}