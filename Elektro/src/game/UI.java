package game;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UI {
    GamePanel gamePanel;
    Graphics2D g2d;
    Font arial_40, arial_80B, purisaBold;
    public String currentDialogue = "";
    public int commandNum = 0;

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


    }
    public void draw(Graphics2D g2d){
        this.g2d = g2d;
        g2d.setFont(purisaBold);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);
        // Title state
        if(gamePanel.gameState == gamePanel.titleState) {
            drawTitleScreen();
        } else if(gamePanel.gameState == gamePanel.playState){

        } else if (gamePanel.gameState == gamePanel.pauseState){
            drawPauseScreen();
        } else if(gamePanel.gameState == gamePanel.dialogueState){
            drawDialogueScreen();
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
        // Main color
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
        for(String line: currentDialogue.split("\n")){
            g2d.drawString(line, x, y);
            y += 40;
        }

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
    public int getXforCenteredText(String text){
        int length = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        return (gamePanel.screenWidth/2) - (length/2);

    }
}
