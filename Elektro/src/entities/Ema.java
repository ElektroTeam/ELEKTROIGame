package entities;

import enums.GameState;
import game.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Ema extends Entity{
    public Ema(GamePanel gamePanel) {
        super(gamePanel);
        setDefaultValues();
        getEntityImage();
        setDialogue();
        dialogueSet = -1;
    }
    public void setDefaultValues() {
        direction = "down";
        speed = 1;
        worldX = gamePanel.getTileSize()*18;
        worldY = gamePanel.getTileSize()*5;
    }
    public void getEntityImage(){
        up = setup("/entities/ema/ema-back1.png");
        up1 = setup("/entities/ema/ema-back2.png");
        up2 = setup("/entities/ema/ema-back3.png");
        down = setup("/entities/ema/ema-front1.png");
        down1 = setup("/entities/ema/ema-front2.png");
        down2 = setup("/entities/ema/ema-front3.png");
        left = setup("/entities/ema/ema-left1.png");
        left1 = setup("/entities/ema/ema-left2.png");
        left2 = setup("/entities/ema/ema-left3.png");
        right = setup("/entities/ema/ema-right1.png");
        right1 = setup("/entities/ema/ema-right2.png");
        right2 = setup("/entities/ema/ema-right3.png");
    }
    public void setDialogue(){

    }
    public void setAction(){
        /*actionLockCounter++;
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
        }*/
        actionLockCounter++;
        if(actionLockCounter==120) {

        }
        if(gamePanel.getGameState() == GameState.FINAL_CINEMATIC_STATE){
            finalCinematic();
        }
    }
    public void finalCinematic(){
        if((worldY/gamePanel.getTileSize())==13){
            moving = false;
            spriteNum = 0;
        }
    }
    @Override
    public void speak() {
        facePlayer();
        startDialogue(this, dialogueSet);
        dialogueSet++;
        if(dialogues[dialogueSet][0]==null){
            // Start over again
            dialogueSet = 0;
            // Repeat the last dialogue set
            // dialogueSet--;
        }
    }
}
