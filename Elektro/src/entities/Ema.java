package entities;

import game.GamePanel;
import utilities.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Ema extends Entity{
    public Ema(GamePanel gamePanel) {
        super(gamePanel);
        direction = "down";
        speed = 1;
        getEntityImage();
    }
    public void getEntityImage(){
        up1 = setup("/entities/ema/boy_up_1.png");
        up2 = setup("/entities/ema/boy_up_2.png");
        down1 = setup("/entities/ema/boy_down_1.png");
        down2 = setup("/entities/ema/boy_down_2.png");
        left1 = setup("/entities/ema/boy_left_1.png");
        left2 = setup("/entities/ema/boy_left_2.png");
        right1 = setup("/entities/ema/boy_right_1.png");
        right2 = setup("/entities/ema/boy_right_2.png");
    }
    public void setAction(){
        actionLockCounter++;
        if(actionLockCounter==120){
            Random random = new Random();
            int i = random.nextInt(100)+1; // Number from 1 to 100
            if(i <= 25){
                direction = "up";
            } else if((i < 25) && (i <= 50)){
                direction = "down";
            } else if ((i > 50) && (i <= 75)){
                direction = "left";
            } else if((i > 75) && (i <= 100)){
                direction = "right";
            }
            actionLockCounter = 0;
        }

    }
}
