package items;

import game.GamePanel;
import utilities.UtilityTool;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Public class which represents the electric gun in the game.
 * @see VisualObject
 */
public class ElectricGunObject extends VisualObject {
    /**
     * Public constructor.
     * @param gamePanel
     */
    public ElectricGunObject(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        name = "Electric gun";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/visuals/electric_gun.png"));
            image = UtilityTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize());
            worldX = 36 * gamePanel.getTileSize();
            worldY = 33 * gamePanel.getTileSize();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
