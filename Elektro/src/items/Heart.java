package items;

import game.GamePanel;
import utilities.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Heart extends SuperObject{
    public GamePanel gamePanel;
    public Heart(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        name = "Heart";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/visuals/heart/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/items/visuals/heart/heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/items/visuals/heart/heart_blank.png"));
            image = UtilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
            image2 = UtilityTool.scaleImage(image2, gamePanel.tileSize, gamePanel.tileSize);
            image3 = UtilityTool.scaleImage(image3, gamePanel.tileSize, gamePanel.tileSize);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
