package entities;

import enums.GameState;
import game.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Bob extends Entity{
    public Bob(GamePanel gamePanel) {
        super(gamePanel);
        setDefaultValues();
        getEntityImage();
        setDialogue();
        dialogueSet = -1;
    }
    public void setDefaultValues() {
        direction = "down";
        speed = 2;
        worldX = gamePanel.getTileSize()*18;
        worldY = gamePanel.getTileSize()*5;
    }
    public void getEntityImage(){
        up = setup("/entities/bob/bob-back1.png");
        up1 = setup("/entities/bob/bob-back2.png");
        up2 = setup("/entities/bob/bob-back3.png");
        down = setup("/entities/bob/bob-front1.png");
        down1 = setup("/entities/bob/bob-front2.png");
        down2 = setup("/entities/bob/bob-front3.png");
        left = setup("/entities/bob/bob-left1.png");
        left1 = setup("/entities/bob/bob-left2.png");
        left2 = setup("/entities/bob/bob-left3.png");
        right = setup("/entities/bob/bob-right1.png");
        right1 = setup("/entities/bob/bob-right2.png");
        right2 = setup("/entities/bob/bob-right3.png");
        defeated1 = setup("/entities/bob/bob-defeated1.png");
    }
    public void setDialogue(){

    }
    public void setAction(){
        actionLockCounter++;
        if(actionLockCounter==120) {

        }
        if(gamePanel.getGameState() == GameState.MALL_CINEMATIC_STATE){
            mallCinematic();
        } else if(gamePanel.getGameState() == GameState.CAVE_CINEMATIC_STATE){
            caveCinematic();
        }
    }
    public void mallCinematic(){
        if((worldY/gamePanel.getTileSize())==33){
            moving = false;
            spriteNum = 0;
        }
    }
    public void caveCinematic() {

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
