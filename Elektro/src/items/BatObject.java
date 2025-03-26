package items;

import game.GamePanel;
import utilities.UtilityTool;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Public class which represents the bat in the game.
 */
public class BatObject extends VisualObject {
    /**
     * Public constructor.
     * @param gamePanel
     */
    public BatObject(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        name = "Bat";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/visuals/bat.png"));
            image = UtilityTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize());
            worldX = 11 * gamePanel.getTileSize();
            worldY = 26 * gamePanel.getTileSize();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
