package game;

import entities.Entity;
import game.sound.enums.SoundType;
import items.Heart;
import items.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GamePanel gamePanel;
    Graphics2D g2d;
    Font arial_40, arial_80B, purisaBold;
    BufferedImage heart_full, heart_half, heart_blank;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int subState = 0;
    public Entity npc;
    public int charIndex = 0;
    public String combinedText = "";

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B =  new Font("Arial", Font.PLAIN, 80);
        try{
            InputStream inputStream = getClass().getResourceAsStream("/fonts/PurisaBold.ttf");
            purisaBold = Font.createFont(Font.TRUETYPE_FONT, inputStream);
        } catch (FontFormatException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        SuperObject heart = new Heart(gamePanel);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

    }
    public void draw(Graphics2D g2d){
        this.g2d = g2d;
        //g2d.setFont(purisaBold);
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);
        // Title state
        if(gamePanel.gameState == gamePanel.titleState) {
            drawTitleScreen();
        } else if(gamePanel.gameState == gamePanel.playState){
            if(gamePanel.developerMode){
                drawDeveloperModeWindow();
            }
            drawPlayerLife();
        } else if (gamePanel.gameState == gamePanel.pauseState){
            if(gamePanel.developerMode){
                drawDeveloperModeWindow();
            }
            drawPlayerLife();
            drawPauseScreen();
        } else if(gamePanel.gameState == gamePanel.dialogueState){
            if(gamePanel.developerMode){
                drawDeveloperModeWindow();
            }
            drawPlayerLife();
            drawDialogueScreen();
        } else if(gamePanel.gameState == gamePanel.optionsState){
            if(gamePanel.developerMode){
                drawDeveloperModeWindow();
            }
            drawPlayerLife();
            drawOptionsScreen();
        } else if(gamePanel.gameState == gamePanel.gameOverState){
            if(gamePanel.developerMode){
                drawDeveloperModeWindow();
            }
            drawPlayerLife();
            drawGameOverScreen();
        }
    }
    public void drawGameOverScreen(){
        g2d.setColor(new Color(0, 0, 0, 150));
        g2d.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 110F));
        String text;
        int x, y;
        text = "Game Over";
        // Shadow
        g2d.setColor(Color.BLACK);
        x = getXforCenteredText(text);
        y = gamePanel.tileSize*4;
        g2d.drawString(text, x, y);
        // Main text
        g2d.setColor(Color.WHITE);
        g2d.drawString(text, x-4, y-4);
        // Retry
        g2d.setFont(g2d.getFont().deriveFont(50F));
        text = "Retry";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize*4;
        g2d.drawString(text, x, y);
        if(commandNum == 0){
            g2d.drawString(">", x-40, y);
        }
        // Back to the title screen
        text = "Quit";
        x = getXforCenteredText(text);
        y += 55;
        g2d.drawString(text, x, y);
        if(commandNum == 1){
            g2d.drawString(">", x-40, y);
        }
    }
    public void drawPlayerLife(){
        int x = gamePanel.tileSize/2;
        int y = gamePanel.tileSize/2;
        int i = 0;
        // Draw blank heart
        while(i < gamePanel.player.maxLife/2){
            g2d.drawImage(heart_blank, x, y, null);
            i++;
            x += gamePanel.tileSize;
        }
        // Reset
        x = gamePanel.tileSize/2;
        y = gamePanel.tileSize/2;
        i = 0;
        // Draw current life
        while(i < gamePanel.player.life){
            g2d.drawImage(heart_half, x, y, null);
            i++;
            if(i < gamePanel.player.life){
                g2d.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gamePanel.tileSize;
        }
    }
    public void drawTitleScreen(){
        g2d.setColor(new Color(0, 0, 0));
        g2d.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);
        // Title name
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 56F));
        String text = "Elektro 1";
        int x, y;
        x = getXforCenteredText(text);
        y = gamePanel.tileSize*3;
        // Shadow
        g2d.setColor(Color.GRAY);
        g2d.drawString(text, x+5, y+5);
        // game.Main color
        g2d.setColor(Color.WHITE);
        g2d.drawString(text, x, y);
        // Logo
        x = (gamePanel.screenWidth/2) - (gamePanel.tileSize*3)/2;
        y += gamePanel.tileSize;
        g2d.drawImage(gamePanel.logo, x, y, gamePanel.tileSize*3, gamePanel.tileSize*3, null);
        // Menu
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 48F));
        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize*4;
        g2d.drawString(text, x, y);
        if(commandNum==0){
            g2d.drawString(">", x-gamePanel.tileSize, y);
        }
        text = "LOAD GAME";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize;
        g2d.drawString(text, x, y);
        if(commandNum==1){
            g2d.drawString(">", x-gamePanel.tileSize, y);
        }
        text = "QUIT";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize;
        g2d.drawString(text, x, y);
        if(commandNum==2){
            g2d.drawString(">", x-gamePanel.tileSize, y);
        }
    }
    public void drawPauseScreen(){
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(70F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gamePanel.screenHeight/2;
        g2d.drawString(text, x, y);
    }
    public void drawDialogueScreen(){
        int x, y, width, height;
        x = gamePanel.tileSize*2;
        y = gamePanel.tileSize/2;
        width =gamePanel.screenWidth - (gamePanel.tileSize*4);
        height = gamePanel.tileSize*5;
        drawSubWindow(x, y, width, height);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 16F));
        x += gamePanel.tileSize;
        y += gamePanel.tileSize;
        if(npc.dialogues[npc.dialogueSet][npc.dialogueIndex]!=null){
            //currentDialogue = npc.dialogues[npc.dialogueSet][npc.dialogueIndex];
            char characters[] = npc.dialogues[npc.dialogueSet][npc.dialogueIndex].toCharArray();
            if(charIndex < characters.length){
                String s = String.valueOf(characters[charIndex]);
                combinedText = combinedText + s;
                currentDialogue = combinedText;
                charIndex++;
            }
            if(gamePanel.keyboardHandler.enterPressed){
                charIndex = 0;
                combinedText = "";
                if(gamePanel.gameState == gamePanel.dialogueState){
                    npc.dialogueIndex++;
                    gamePanel.keyboardHandler.enterPressed = false;
                }
            }
        } else {
            npc.dialogueIndex = 0;
            if(gamePanel.gameState == gamePanel.dialogueState){
                gamePanel.gameState = gamePanel.playState;
            }
        }
        drawMultipleLines(currentDialogue, x, y);

    }
    public void drawSubWindow(int x, int y, int width, int height){
        Color color = new Color(0, 0, 0, 210);
        g2d.setColor(color);
        g2d.fillRoundRect(x, y, width, height, 35, 35);
        color = new Color(255, 255, 255);
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRoundRect(x+5, y+5, width-10, height-10, 35, 35);
    }
    public void drawOptionsScreen(){
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(32F));
        // Sub window
        int frameX = gamePanel.tileSize*6;
        int frameY = gamePanel.tileSize;
        int frameWidth = gamePanel.tileSize*8;
        int frameHeight = gamePanel.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        switch (subState){
            case 0:
                optionsTop(frameX, frameY);
                break;
            case 1:
                optionsFullScreenNotification(frameX, frameY);
                break;
            case 2:
                optionsControl(frameX, frameY);
                break;
            case 3:
                optionsEndGameConfirmation(frameX, frameY);
                break;
        }
        gamePanel.keyboardHandler.enterPressed = false;

    }
    public void optionsTop(int frameX, int frameY){
        int textX;
        int textY;
        // Title
        String text = "Options";
        textX = getXforCenteredText(text);
        textY = frameY + gamePanel.tileSize;
        g2d.drawString(text, textX, textY);
        g2d.setFont(g2d.getFont().deriveFont(24F));
        // Full screen on/off
        text = "Full screen";
        textX  = frameX + gamePanel.tileSize;
        textY += gamePanel.tileSize*2;
        g2d.drawString(text, textX, textY);
        if(commandNum == 0){
            g2d.drawString(">", textX-25, textY);
            if(gamePanel.keyboardHandler.enterPressed){
                if(gamePanel.fullScreenOn){
                    gamePanel.fullScreenOn = false;
                } else {
                    gamePanel.fullScreenOn = true;
                }
                subState = 1;
            }
        }
        // Music
        text = "Music";
        textY += gamePanel.tileSize;
        g2d.drawString(text, textX, textY);
        if(commandNum == 1){
            g2d.drawString(">", textX-25, textY);
        }
        // Sound effects
        text = "SE";
        textY += gamePanel.tileSize;
        g2d.drawString(text, textX, textY);
        if(commandNum == 2){
            g2d.drawString(">", textX-25, textY);
        }
        // Controls
        text = "Controls";
        textY += gamePanel.tileSize;
        g2d.drawString(text, textX, textY);
        if(commandNum == 3){
            g2d.drawString(">", textX-25, textY);
            if(gamePanel.keyboardHandler.enterPressed){
                subState = 2;
                commandNum = 0;
            }
        }
        // End game
        text = "End game";
        textY += gamePanel.tileSize;
        g2d.drawString(text, textX, textY);
        if(commandNum == 4){
            g2d.drawString(">", textX-25, textY);
            if(gamePanel.keyboardHandler.enterPressed){
                subState = 3;
                commandNum = 0;
            }
        }
        // Back
        text = "Back";
        textY += gamePanel.tileSize*2;
        g2d.drawString(text, textX, textY);
        if(commandNum == 5){
            g2d.drawString(">", textX-25, textY);
            if(gamePanel.keyboardHandler.enterPressed){
                gamePanel.gameState = gamePanel.playState;
                commandNum = 0; 
            }
        }

        // Full screen checkbox
        textX = frameX + (int) (gamePanel.tileSize*4.5);
        textY = frameY + gamePanel.tileSize*2 + 24;
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(textX, textY, 24, 24);
        if(gamePanel.fullScreenOn){
            g2d.fillRect(textX, textY, 24, 24);
        }
        // Music volume
        textY += gamePanel.tileSize;
        g2d.drawRect(textX, textY, 120, 24); // 120 / 5  = 24
        int volumeWidth = 24*gamePanel.musicManager.getVolumeScale();
        g2d.fillRect(textX, textY, volumeWidth, 24);
        // Sound effects
        textY += gamePanel.tileSize;
        g2d.drawRect(textX, textY, 120, 24);
        volumeWidth = 24*gamePanel.soundEffectManager.getVolumeScale();
        g2d.fillRect(textX, textY, volumeWidth, 24);

    }
    public void optionsFullScreenNotification(int frameX, int frameY){
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(20F));
        String text = "This change will be \napplied after restarting \nthe game.";
        int textX = frameX + gamePanel.tileSize;
        int textY = frameY + gamePanel.tileSize*3;
        drawMultipleLines(text, textX, textY);
        // Back
        text = "Back";
        textY = frameY + gamePanel.tileSize*9;
        g2d.drawString(text, textX, textY);
        if(commandNum == 0){
            g2d.drawString(">", textX-25, textY);
            if(gamePanel.keyboardHandler.enterPressed){
                subState = 0;
            }
        }
    }
    public void optionsControl(int frameX, int frameY){
        String text;
        int textX, textY;
        // Title
        text = "Controls";
        textX = getXforCenteredText(text);
        textY = frameY + gamePanel.tileSize;
        g2d.drawString(text, textX, textY);
        g2d.setFont(g2d.getFont().deriveFont(24F));
        // Move
        text = "Move";
        textX = frameX + gamePanel.tileSize;
        textY += gamePanel.tileSize;
        g2d.drawString(text, textX, textY);
        // Attack
        text = "Attack";
        textY += gamePanel.tileSize;
        g2d.drawString(text, textX, textY);
/*        // Pause
        text = "Pause";
        textY += gamePanel.tileSize;
        g2d.drawString(text, textX, textY);*/
        // Options
        text = "Options";
        textY += gamePanel.tileSize;
        g2d.drawString(text, textX, textY);
        // Others
        text = "Others...";
        textY += gamePanel.tileSize;
        g2d.drawString(text, textX, textY);

        // Move keys
        text = "WASD";
        textX = frameX + gamePanel.tileSize*5;
        textY = frameY + gamePanel.tileSize*2;
        g2d.drawString(text, textX, textY);
        // Attack key
        text = "ENTER";
        textY += gamePanel.tileSize;
        g2d.drawString(text, textX, textY);
        // Pause
/*      text = "ESC";
        textY += gamePanel.tileSize;
        g2d.drawString(text, textX, textY);*/
        // Options
        text = "ESC";
        textY += gamePanel.tileSize;
        g2d.drawString(text, textX, textY);

        // Back
        text = "Back";
        textX = frameX + gamePanel.tileSize;
        textY = frameY + gamePanel.tileSize*9;
        g2d.drawString(text, textX, textY);
        if(commandNum == 0){
            g2d.drawString(">", textX-25, textY);
            if(gamePanel.keyboardHandler.enterPressed){
                subState = 0;
                commandNum = 3;
            }
        }
    }
    public void optionsEndGameConfirmation(int frameX, int frameY){
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(20F));
        String text = "Do you want to \nquit the game and return\n to the title screen?";
        int textX = frameX + gamePanel.tileSize;
        int textY = frameY + gamePanel.tileSize*3;
        drawMultipleLines(text, textX, textY);
        // Yes
        text = "Yes";
        textX = getXforCenteredText(text);
        textY += gamePanel.tileSize*3;
        g2d.drawString(text, textX, textY);
        if(commandNum == 0){
            g2d.drawString(">", textX-25, textY);
            if(gamePanel.keyboardHandler.enterPressed){
                subState = 0;
                gamePanel.gameState = gamePanel.titleState;
                //gamePanel.music.stop();
                gamePanel.stopMusic();
            }
        }
        // No
        text = "No";
        textX = getXforCenteredText(text);
        textY += gamePanel.tileSize;
        g2d.drawString(text, textX, textY);
        if(commandNum == 1){
            g2d.drawString(">", textX-25, textY);
            if(gamePanel.keyboardHandler.enterPressed){
                subState = 0;
                commandNum = 4;
            }
        }
    }
    public void drawDeveloperModeWindow(){
        int x, y;
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));
        g2d.setColor(Color.WHITE);
        String text = "DEVELOPER MODE";
        x = getXforCenteredText(text);
        y = 20;
        g2d.drawString(text, x, y);
        x = 10;
        y = 400;
        int lineHeight = 20;
        g2d.drawString("Player X: "+(gamePanel.player.worldX/gamePanel.tileSize)+" | Player Y: "+(gamePanel.player.worldY/gamePanel.tileSize), x, y);
        y += lineHeight;
        g2d.drawString("Ema X: "+(gamePanel.npcs[0].worldX/gamePanel.tileSize)+ " | Ema Y: "+(gamePanel.npcs[0].worldY/gamePanel.tileSize), x, y);
        y += lineHeight;
    }
    public void drawMultipleLines(String text, int textX, int textY){
        for(String line : text.split("\n")){
            g2d.drawString(line, textX, textY);
            textY += 40;
        }
    }
    public int getXforCenteredText(String text){
        int length = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        return (gamePanel.screenWidth/2) - (length/2);

    }
}
