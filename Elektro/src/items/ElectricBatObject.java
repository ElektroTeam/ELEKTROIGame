package items;

import game.GamePanel;
import utilities.UtilityTool;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Public class which represents the electric bat in the game.
 * @see VisualObject
 */
public class ElectricBatObject extends VisualObject {
    /**
     * Public constructor.
     * @param gamePanel
     */
    public ElectricBatObject(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        name = "Electric bat";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/visuals/electric_bat.png"));
            image = UtilityTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize());
            worldX = 19 * gamePanel.getTileSize();
            worldY = 34 * gamePanel.getTileSize();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
