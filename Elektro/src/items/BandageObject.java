package items;

import game.GamePanel;
import utilities.UtilityTool;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Public class which represents the bandages in the game.
 * @see VisualObject
 */
public class BandageObject extends VisualObject {
    /**
     * Public constructor.
     * @param gamePanel
     */
    public BandageObject(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        name = "Bandage";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/visuals/bandage.png"));
            image = UtilityTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize());
            worldX = 8 * gamePanel.getTileSize();
            worldY = 26 * gamePanel.getTileSize();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
