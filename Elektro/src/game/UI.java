package game;

import java.awt.*;
import java.text.DecimalFormat;

public class UI {
    GamePanel gamePanel;
    Graphics2D g2d;
    Font arial_40, arial_80B;
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

    }
    public void showMessage(String message) {
        this.message = message;
        messageOn = true;
    }
    public void draw(Graphics2D g2d){
        this.g2d = g2d;
        g2d.setFont(arial_40);
        g2d.setColor(Color.WHITE);
        if(gamePanel.gameState == gamePanel.playState){
            // Do playstate stuff later
        } else if (gamePanel.gameState == gamePanel.pauseState){
            drawPauseScreen();
        }
    }
    public void drawPauseScreen(){
        String text = "PAUSED";
        int x, y;
        y = gamePanel.screenHeight/2;
        x = getXforCenteredText(text);
        g2d.drawString(text, x, y);
    }
    public int getXforCenteredText(String text){
        int length = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        return (gamePanel.screenWidth/2) - (length/2);

    }
}
