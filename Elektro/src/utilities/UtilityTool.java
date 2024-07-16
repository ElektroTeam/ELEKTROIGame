package utilities;

import game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UtilityTool {
    public static BufferedImage scaleImage(BufferedImage image, int newWidth, int newHeight) {
        BufferedImage newImage = new BufferedImage(newWidth, newHeight, image.getType());
        Graphics2D g2d = newImage.createGraphics();
        g2d.drawImage(image, 0, 0, newWidth, newHeight, null);
        return newImage;
    }
    public static BufferedImage setup(String imagePath, GamePanel gamePanel) {
        BufferedImage image = null;
        try{
            image = ImageIO.read(UtilityTool.class.getResourceAsStream(imagePath));
            image = UtilityTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize());
        } catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }
}
