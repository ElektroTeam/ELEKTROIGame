package items;

import game.GamePanel;
import utilities.UtilityTool;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Public class which represents the strawberries in the game.
 * @see VisualObject
 */
public class BerryObject extends VisualObject {
    /**
     * Public constructor.
     * @param gamePanel
     */
    public BerryObject(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        name = "Strawberry";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/visuals/berry.png"));
            image = UtilityTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize());
            worldX = 29 * gamePanel.getTileSize();
            worldY = 4 * gamePanel.getTileSize();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
