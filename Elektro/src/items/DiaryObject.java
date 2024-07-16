package items;

import game.GamePanel;
import utilities.UtilityTool;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Public class which represents the diary in the game.
 * @see VisualObject
 */
public class DiaryObject extends VisualObject {
    /**
     * Public constructor.
     * @param gamePanel
     */
    public DiaryObject(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        name = "Diary";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/visuals/diary.png"));
            image = UtilityTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize());
            worldX = 15 * gamePanel.getTileSize();
            worldY = 12 * gamePanel.getTileSize();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
