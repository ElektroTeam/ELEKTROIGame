package items;

import game.GamePanel;
import game.UtilityTool;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectKey extends SuperObject{
    private GamePanel gamePanel;
    public ObjectKey(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        name = "Key";
        try{
           image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
            UtilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
