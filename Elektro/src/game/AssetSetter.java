package game;

import entities.Ema;

public class AssetSetter {
    GamePanel gamePanel;
    public AssetSetter(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    public void setObject(){
        
    }
    public void setNPCs(){
        gamePanel.npcs[0] = new Ema(gamePanel);
        gamePanel.npcs[0].worldX = gamePanel.tileSize*21;
        gamePanel.npcs[0].worldY = gamePanel.tileSize*21;
    }
}
