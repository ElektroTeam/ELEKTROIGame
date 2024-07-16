package items;

import game.GamePanel;
import utilities.UtilityTool;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Public class which represents the med kits in the game.
 */
public class MedkitObject extends VisualObject {
    /**
     * Public constructor.
     * @param gamePanel
     */
    public MedkitObject(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        name = "Med kit";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/visuals/medkit.png"));
            image = UtilityTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize());
            worldX = 8 * gamePanel.getTileSize();
            worldY = 26 * gamePanel.getTileSize();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
