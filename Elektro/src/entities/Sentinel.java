package entities;

import enums.GameState;
import game.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Sentinel extends Entity{
    public Sentinel(GamePanel gamePanel) {
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
        up = setup("/entities/sentinel/sentinel-back1.png");
        up1 = setup("/entities/sentinel/sentinel-back2.png");
        up2 = setup("/entities/sentinel/sentinel-back3.png");
        down = setup("/entities/sentinel/sentinel-front1.png");
        down1 = setup("/entities/sentinel/sentinel-front2.png");
        down2 = setup("/entities/sentinel/sentinel-front3.png");
        left = setup("/entities/sentinel/sentinel-left1.png");
        left1 = setup("/entities/sentinel/sentinel-left2.png");
        left2 = setup("/entities/sentinel/sentinel-left3.png");
        right = setup("/entities/sentinel/sentinel-right1.png");
        right1 = setup("/entities/sentinel/sentinel-right2.png");
        right2 = setup("/entities/sentinel/sentinel-right3.png");
        defeated1 = setup("/entities/sentinel/sentinel-defeated1.png");
        defeated2 = setup("/entities/sentinel/sentinel-defeated2.png");
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
        if((worldY/gamePanel.getTileSize())==32){
            moving = false;
            spriteNum = 0;
        }
    }
    public void caveCinematic(){
        if(gamePanel.getUi().getCurrentCutsceneCode().equals("enter_battle_mode_cave")){
            moving = true;
            if((worldY/gamePanel.getTileSize())==13){
                moving = false;
                spriteNum = 0;
            }
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
