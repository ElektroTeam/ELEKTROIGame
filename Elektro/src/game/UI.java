package game;

import items.ObjectKey;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gamePanel;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    double playTime;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B =  new Font("Arial", Font.PLAIN, 80);
        ObjectKey key = new ObjectKey(gamePanel);
        keyImage = key.image;
    }
    public void showMessage(String message) {
        this.message = message;
        messageOn = true;
    }
    public void draw(Graphics2D g2d){
        if(gameFinished){
            g2d.setFont(arial_40);
            g2d.setColor(Color.WHITE);
            String text;
            int textLength;
            int x;
            int y;
            text = "You found the treasure!";
            textLength = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
            x = (gamePanel.screenWidth/2) - (textLength/2);
            y = (gamePanel.screenHeight/2) - (gamePanel.tileSize*3);
            g2d.drawString(text, x, y);

            text = "Your time is: "+ decimalFormat.format(playTime)+"s!";
            textLength = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
            x = (gamePanel.screenWidth/2) - (textLength/2);
            y = (gamePanel.screenHeight/2) - (gamePanel.tileSize*4);
            g2d.drawString(text, x, y);

            g2d.setFont(arial_80B);
            g2d.setColor(Color.YELLOW);
            text = "Congratulations";
            textLength = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
            x = (gamePanel.screenWidth/2) - (textLength/2);
            y = (gamePanel.screenHeight/2) - (gamePanel.tileSize*2);
            g2d.drawString(text, x, y);
            gamePanel.gameThread = null;

        } else {
            g2d.setFont(arial_40);
            g2d.setColor(Color.WHITE);
            // draw images and draw strings work kind of different with coordinates
            g2d.drawImage(keyImage, gamePanel.tileSize/2, gamePanel.tileSize/2, gamePanel.tileSize, gamePanel.tileSize, null);
            g2d.drawString("x "+gamePanel.player.hasKey, 74, 65);
            // Time
            playTime += (double)1/60;
            g2d.drawString("Time : "+decimalFormat.format(playTime), gamePanel.tileSize*10, 65);
            // Messsage
            if(messageOn){
                g2d.setFont(g2d.getFont().deriveFont(30F));
                g2d.drawString(message, gamePanel.tileSize/2, gamePanel.tileSize*5);
                messageCounter++;
                if(messageCounter > 120){
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }

    }
}
