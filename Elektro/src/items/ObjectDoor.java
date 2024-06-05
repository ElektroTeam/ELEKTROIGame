package items;

import game.GamePanel;
import game.UtilityTool;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectDoor extends SuperObject{
    private GamePanel gamePanel;
    public ObjectDoor(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        name = "Door";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
            UtilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e){
            e.printStackTrace();
        }
        colission = true;
    }
}
