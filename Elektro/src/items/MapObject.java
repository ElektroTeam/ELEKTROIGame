package items;

import game.GamePanel;
import utilities.UtilityTool;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Public class which represents the map in the game.
 * @see VisualObject
 */
public class MapObject extends VisualObject {
    /**
     * Public constructor.
     * @param gamePanel
     */
    public MapObject(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        name = "Map";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/visuals/map.png"));
            image = UtilityTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize());
            worldX = 25 * gamePanel.getTileSize();
            worldY = 28 * gamePanel.getTileSize();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
