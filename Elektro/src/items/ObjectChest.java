package items;

import game.GamePanel;
import game.UtilityTool;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectChest extends SuperObject{
    private GamePanel gamePanel;
    public ObjectChest(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        name = "Chest";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
            UtilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
