package items;

import game.GamePanel;
import utilities.UtilityTool;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Public class which represents the hearts in the UI.
 * @see VisualObject
 */
public class Heart extends VisualObject {
    /**
     * Public constructor.
     * @param gamePanel
     */
    public Heart(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        name = "Heart";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/visuals/heart/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/items/visuals/heart/heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/items/visuals/heart/heart_blank.png"));
            image = UtilityTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize());
            image2 = UtilityTool.scaleImage(image2, gamePanel.getTileSize(), gamePanel.getTileSize());
            image3 = UtilityTool.scaleImage(image3, gamePanel.getTileSize(), gamePanel.getTileSize());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
