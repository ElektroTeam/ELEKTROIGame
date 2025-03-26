package items;

import game.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
/**
 * Public class which represents visual objects.
 */
public class VisualObject {
    protected GamePanel gamePanel;
    protected BufferedImage image, image2, image3;
    protected String name;
    protected boolean collision = false;
    protected int worldX, worldY;
    protected Rectangle solidArea = new Rectangle(0,0,48,48);
    protected int solidAreaDefaultX = 0;
    protected int solidAreaDefaultY = 0;
    protected boolean collected = false;
    /**
     * Draw the visual object.
     * @param g2d
     * @param gamePanel
     */
    public void draw(Graphics2D g2d, GamePanel gamePanel){
        this.gamePanel = gamePanel;
        int screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().screenX;
        int screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().screenY;
        if((worldX+gamePanel.getTileSize()>(gamePanel.getPlayer().getWorldX()-gamePanel.getPlayer().screenX))&&(worldX-gamePanel.getTileSize()<(gamePanel.getPlayer().getWorldX()+gamePanel.getPlayer().screenX))&&(worldY+gamePanel.getTileSize()>(gamePanel.getPlayer().getWorldY()-gamePanel.getPlayer().screenY))&&(worldY-gamePanel.getTileSize()<(gamePanel.getPlayer().getWorldY()+gamePanel.getPlayer().screenY))){
            g2d.drawImage(image, screenX, screenY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
        }
    }

    /**
     * Get the object image.
     * @return
     */
    public BufferedImage getImage() {return image;}
    /**
     * Get the object image 2.
     * @return
     */
    public BufferedImage getImage2() {return image2;}
    /**
     * Get the object image 3.
     * @return
     */
    public BufferedImage getImage3() {return image3;}
    /**
     * Get the object name.
     * @return
     */
    public String getName() {return name;}
    /**
     * Set the object name.
     * @param name
     */
    public void setName(String name) {this.name = name;}
    /**
     * Check if the object has collision.
     * @return
     */
    public boolean isCollision() {return collision;}
    /**
     * Set if the object has collision.
     * @param collision
     */
    public void setCollision(boolean collision) {this.collision = collision;}
    /**
     * Get the object world X.
     * @return
     */
    public int getWorldX() {return worldX;}
    /**
     * Set the object world X.
     * @param worldX
     */
    public void setWorldX(int worldX) {this.worldX = worldX;}
    /**
     * Get the object world Y.
     * @return
     */
    public int getWorldY() {return worldY;}
    /**
     * Set the object world Y.
     * @param worldY
     */
    public void setWorldY(int worldY) {this.worldY = worldY;}
    /**
     * Get the object solid area.
     * @return
     */
    public Rectangle getSolidArea() {return solidArea;}
    /**
     * Set the object solid area.
     * @param solidArea
     */
    public void setSolidArea(Rectangle solidArea) {this.solidArea = solidArea;}
    /**
     * Get the solid area default X.
     * @return
     */
    public int getSolidAreaDefaultX() {return solidAreaDefaultX;}
    /**
     * Set the solid area default X.
     * @param solidAreaDefaultX
     */
    public void setSolidAreaDefaultX(int solidAreaDefaultX) {this.solidAreaDefaultX = solidAreaDefaultX;}
    /**
     * Get the solid area default Y.
     * @return
     */
    public int getSolidAreaDefaultY() {return solidAreaDefaultY;}
    /**
     * Setthe solid area default Y.
     * @param solidAreaDefaultY
     */
    public void setSolidAreaDefaultY(int solidAreaDefaultY) {this.solidAreaDefaultY = solidAreaDefaultY;}
    /**
     * Check if the object has been collected.
     * @return
     */
    public boolean isCollected() {return collected;}
    /**
     * Set if the object has been collected.
     * @param collected
     */
    public void setCollected(boolean collected) {this.collected = collected;}
}
