package game;

import entities.Entity;

import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Public class to validate collisions with items, NPCs, etc.
 */
public class CollisionChecker {
    private GamePanel gamePanel;
    private boolean mapFound = false, diaryFound = false, batFound = false, lockedMomBedroomDialogue = false, lockedBathroomDialogue = false, lockedNeighborHouseDialogue = false, mallCinematic = false, caveCinematic = false;
    private boolean batHintDisplayed = false;

    /**
     * Public constructor.
     * @param gamePanel
     */
    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * Check collission with tiles (blocks).
     * @param entity the entity to check if it collides with tiles.
     */
    public void checkTile(Entity entity){
        int entityLeftWorldX = (entity.getWorldX() + entity.getSolidArea().x);
        int entityRightWorldX = (entity.getWorldX() + entity.getSolidArea().x + entity.getSolidArea().width);
        int entityTopWorldY = (entity.getWorldY() + entity.getSolidArea().y);
        int entityBottomWorldY = (entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height);

        int entityLeftCol = (entityLeftWorldX / gamePanel.getTileSize());
        int entityRightCol = (entityRightWorldX / gamePanel.getTileSize());
        int entityTopRow = (entityTopWorldY / gamePanel.getTileSize());
        int entityBottomRow = (entityBottomWorldY / gamePanel.getTileSize());

        int tileNum1, tileNum2;
        try{
            switch(entity.getDirection()){
                case "up":
                    entityTopRow = (entityTopWorldY - entity.getSpeed()) / (gamePanel.getTileSize());
                    tileNum1 = gamePanel.getTileManager().mapTileNum[entityLeftCol][entityTopRow];
                    tileNum2 = gamePanel.getTileManager().mapTileNum[entityRightCol][entityTopRow];
                    if(gamePanel.getTileManager().tiles[tileNum1].isColission()||gamePanel.getTileManager().tiles[tileNum2].isColission()){
                        entity.setCollissionOn(true);
                    }
                    break;
                case "down":
                    entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / (gamePanel.getTileSize());
                    tileNum1 = gamePanel.getTileManager().mapTileNum[entityLeftCol][entityBottomRow];
                    tileNum2 = gamePanel.getTileManager().mapTileNum[entityRightCol][entityBottomRow];
                    if(gamePanel.getTileManager().tiles[tileNum1].isColission()||gamePanel.getTileManager().tiles[tileNum2].isColission()){
                        entity.setCollissionOn(true);
                    }
                    break;
                case "left":
                    entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / (gamePanel.getTileSize());
                    tileNum1 = gamePanel.getTileManager().mapTileNum[entityLeftCol][entityTopRow];
                    tileNum2 = gamePanel.getTileManager().mapTileNum[entityLeftCol][entityBottomRow];
                    if(gamePanel.getTileManager().tiles[tileNum1].isColission()||gamePanel.getTileManager().tiles[tileNum2].isColission()){
                        entity.setCollissionOn(true);
                    }
                    break;
                case "right":
                    entityRightCol = (entityRightWorldX + entity.getSpeed()) / (gamePanel.getTileSize());
                    tileNum1 = gamePanel.getTileManager().mapTileNum[entityRightCol][entityTopRow];
                    tileNum2 = gamePanel.getTileManager().mapTileNum[entityRightCol][entityBottomRow];
                    if(gamePanel.getTileManager().tiles[tileNum1].isColission()||gamePanel.getTileManager().tiles[tileNum2].isColission()){
                        entity.setCollissionOn(true);
                    }
                    break;
            }
        } catch(IndexOutOfBoundsException e){
            entity.setCollissionOn(true);
        }
    }
    /**
     * Check collision with objects.
     * @param entity the entity to check the collision.
     * @param player whether is player or not.
     * @return
     */
    public String checkObject(Entity entity, boolean player){
        AtomicReference<String> key = new AtomicReference<>("");
        gamePanel.getObjects().forEach((k, v) -> {
            // Get entity's solid area position
            entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
            entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
            v.getSolidArea().x = v.getWorldX() + v.getSolidArea().x;
            v.getSolidArea().y = v.getWorldY() + v.getSolidArea().y;
            switch (entity.getDirection()) {
                case "up":
                    entity.getSolidArea().y -= entity.getSpeed();
                    if (entity.getSolidArea().intersects(v.getSolidArea())) {
                        if (v.isCollision()) {
                            entity.setCollissionOn(true);
                        }
                        if (player) {
                            key.set(k);
                        }
                    }
                    break;
                case "down":
                    entity.getSolidArea().y += entity.getSpeed();
                    if (entity.getSolidArea().intersects(v.getSolidArea())) {
                        if (v.isCollision()) {
                            entity.setCollissionOn(true);
                        }
                        if (player) {
                            key.set(k);
                        }
                    }
                    break;
                case "left":
                    entity.getSolidArea().x -= entity.getSpeed();
                    if (entity.getSolidArea().intersects(v.getSolidArea())) {
                        if (v.isCollision()) {
                            entity.setCollissionOn(true);
                        }
                        if (player) {
                            key.set(k);
                        }
                    }
                    break;
                case "right":
                    entity.getSolidArea().x += entity.getSpeed();
                    if (entity.getSolidArea().intersects(v.getSolidArea())) {
                        if (v.isCollision()) {
                            entity.setCollissionOn(true);
                        }
                        if (player) {
                            key.set(k);
                        }
                    }
                    break;
            }
            entity.getSolidArea().x = entity.getSolidAreaDefaultX();
            entity.getSolidArea().y = entity.getSolidAreaDefaultY();
            v.getSolidArea().x = entity.getSolidAreaDefaultX();
            v.getSolidArea().y = entity.getSolidAreaDefaultY();
        });
        return key.get();
    }
    /**
     * Check collision between entities.
     * @param entity the entity to check if it collides.
     * @param targets the targets to check the collision.
     */
    public void checkEntity(Entity entity, Entity[] targets){
        for (int i = 0; i < targets.length; i++){
            if(targets[i]!=null){
                // Get entity's solid area position
                entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
                entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
                // Get the object's solid area position
                targets[i].getSolidArea().x = targets[i].getWorldX() + targets[i].getSolidArea().x;
                targets[i].getSolidArea().y = targets[i].getWorldY() + targets[i].getSolidArea().y;
                switch(entity.getDirection()){
                    case "up":
                        entity.getSolidArea().y -= entity.getSpeed();
                        if(entity.getSolidArea().intersects(targets[i].getSolidArea())){
                            entity.setCollissionOn(true);
                        }
                        break;
                    case "down":
                        entity.getSolidArea().y += entity.getSpeed();
                        if(entity.getSolidArea().intersects(targets[i].getSolidArea())){
                            entity.setCollissionOn(true);
                        }
                        break;
                    case "left":
                        entity.getSolidArea().x -= entity.getSpeed();
                        if(entity.getSolidArea().intersects(targets[i].getSolidArea())){
                            entity.setCollissionOn(true);
                        }
                        break;
                    case "right":
                        entity.getSolidArea().x += entity.getSpeed();
                        if(entity.getSolidArea().intersects(targets[i].getSolidArea())){
                            entity.setCollissionOn(true);
                        }
                        break;
                }
                entity.getSolidArea().x = entity.getSolidAreaDefaultX();
                entity.getSolidArea().y = entity.getSolidAreaDefaultY();
                targets[i].getSolidArea().x = entity.getSolidAreaDefaultX();
                targets[i].getSolidArea().y = entity.getSolidAreaDefaultY();
            }
        }
    }
    /**
     * Check if the entity interacts with other targets.
     * @param entity the entity to check if it collides.
     * @param targets the targets to check the collision.
     * @return the NPC index.
     */
    public int checkEntityInteraction(Entity entity, Entity[] targets){
        int index = 999;
        for (int i = 0; i < targets.length; i++){
            if(targets[i]!=null){
                // Get entity's solid area position
                entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
                entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
                // Get the object's solid area position
                targets[i].getSolidArea().x = targets[i].getWorldX() + targets[i].getSolidArea().x;
                targets[i].getSolidArea().y = targets[i].getWorldY() + targets[i].getSolidArea().y;

                switch(entity.getDirection()){
                    case "up":
                        entity.getSolidArea().y -= entity.getSpeed();
                        if(entity.getSolidArea().intersects(targets[i].getSolidArea())){
                            entity.setCollissionOn(true);
                            index = i;
                        }
                        break;
                    case "down":
                        entity.getSolidArea().y += entity.getSpeed();
                        if(entity.getSolidArea().intersects(targets[i].getSolidArea())){
                            entity.setCollissionOn(true);
                            index = i;
                        }
                        break;
                    case "left":
                        entity.getSolidArea().x -= entity.getSpeed();
                        if(entity.getSolidArea().intersects(targets[i].getSolidArea())){
                            entity.setCollissionOn(true);
                            index = i;
                        }
                        break;
                    case "right":
                        entity.getSolidArea().x += entity.getSpeed();
                        if(entity.getSolidArea().intersects(targets[i].getSolidArea())){
                            entity.setCollissionOn(true);
                            index = i;
                        }
                        break;
                }
                entity.getSolidArea().x = entity.getSolidAreaDefaultX();
                entity.getSolidArea().y = entity.getSolidAreaDefaultY();
                targets[i].getSolidArea().x = entity.getSolidAreaDefaultX();
                targets[i].getSolidArea().y = entity.getSolidAreaDefaultY();
            }
        }
        return index;
    }
    /**
     * Check the collision between an NPC and the player.
     * @param entity the entity to check the collision.
     */
    public void checkPlayer(Entity entity){
        // Get entity's solid area position
        entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
        entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
        // Get the object's solid area position
        gamePanel.getPlayer().getSolidArea().x = gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getSolidArea().x;
        gamePanel.getPlayer().getSolidArea().y = gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getSolidArea().y;

        switch(entity.getDirection()){
            case "up":
                entity.getSolidArea().y -= entity.getSpeed();
                if(entity.getSolidArea().intersects(gamePanel.getPlayer().getSolidArea())){
                    entity.setCollissionOn(true);
                }
                break;
            case "down":
                entity.getSolidArea().y += entity.getSpeed();
                if(entity.getSolidArea().intersects(gamePanel.getPlayer().getSolidArea())){
                    entity.setCollissionOn(true);
                }
                break;
            case "left":
                entity.getSolidArea().x -= entity.getSpeed();
                if(entity.getSolidArea().intersects(gamePanel.getPlayer().getSolidArea())){
                    entity.setCollissionOn(true);
                }
                break;
            case "right":
                entity.getSolidArea().x += entity.getSpeed();
                if(entity.getSolidArea().intersects(gamePanel.getPlayer().getSolidArea())){
                    entity.setCollissionOn(true);
                }
                break;
        }
        entity.getSolidArea().x = entity.getSolidAreaDefaultX();
        entity.getSolidArea().y = entity.getSolidAreaDefaultY();
        gamePanel.getPlayer().getSolidArea().x = entity.getSolidAreaDefaultX();
        gamePanel.getPlayer().getSolidArea().y = entity.getSolidAreaDefaultY();
    }
    /**
     * Check if the player reached the end of the map.
     * @return whether the player reached the finished map area or not.
     */
    public boolean checkFinishedMap(){
        // Get entity's solid area position
        gamePanel.getPlayer().getSolidArea().x = gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getSolidArea().x;
        gamePanel.getPlayer().getSolidArea().y = gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getSolidArea().y;
        // Get the object's solid area position
        if(!gamePanel.getCurrentMap().getMapName().equals("cave")){
            if(gamePanel.getCurrentMap().getFinishedMapArea().intersects(gamePanel.getPlayer().getSolidArea())){
                if(gamePanel.getCurrentMap().getMapName().equals("origen")){
                    if(gamePanel.getPlayer().isBatFound()){
                        return true;
                    } else {
                        if(!batHintDisplayed){
                            gamePanel.getUi().cutsceneMode("must_find_bat_first");
                            batHintDisplayed = true;
                        }
                    }
                } else {
                    return true;
                }
            }
        }
        gamePanel.getPlayer().getSolidArea().x = gamePanel.getPlayer().getSolidAreaDefaultX();
        gamePanel.getPlayer().getSolidArea().y = gamePanel.getPlayer().getSolidAreaDefaultY();
        return false;
    }
    /**
     * Check if the player intersects a battle area.
     * @return whether the player intersects a battle area or not.
     */
    public boolean checkBattleArea(){
        AtomicReference<Integer> key = new AtomicReference<>(999);
        gamePanel.getPlayer().getSolidArea().x = gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getSolidArea().x;
        gamePanel.getPlayer().getSolidArea().y = gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getSolidArea().y;
        gamePanel.getCurrentMap().getStartBattleZone().forEach((k, v) -> {
            if(gamePanel.getPlayer().getSolidArea().intersects(v)){
                key.set(k);
            }
        });
        gamePanel.getPlayer().getSolidArea().x = gamePanel.getPlayer().getSolidAreaDefaultX();
        gamePanel.getPlayer().getSolidArea().y = gamePanel.getPlayer().getSolidAreaDefaultY();
        if(key.get()!=999){
            gamePanel.getCurrentMap().getStartBattleZone().remove(key.get());
            return true;
        }
        return false;
    }
    /**
     * Check if the player is colliding with the mom's door.
     * This is used to display a dialogue about the locked door.
     * @return
     */
    public boolean checkLockedMomBedroom(){
        if(gamePanel.getCurrentMap().getMapName().equals("house")&&(!lockedMomBedroomDialogue)){
            Rectangle rectangle = new Rectangle(gamePanel.getTileSize()*22, (gamePanel.getTileSize()*21)+10, gamePanel.getTileSize()*1, gamePanel.getTileSize()*1);

            gamePanel.getPlayer().getSolidArea().x = gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getSolidArea().x;
            gamePanel.getPlayer().getSolidArea().y = gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getSolidArea().y;
            // Get the object's solid area position
            if(rectangle.intersects(gamePanel.getPlayer().getSolidArea())){
                lockedMomBedroomDialogue = true;
                return true;
            }
            gamePanel.getPlayer().getSolidArea().x = gamePanel.getPlayer().getSolidAreaDefaultX();
            gamePanel.getPlayer().getSolidArea().y = gamePanel.getPlayer().getSolidAreaDefaultY();
            return false;
        }
        return false;
    }

    /**
     * Check if the player is colliding with the house bathroom..
     * This is used to display a dialogue about the locked door.
     * @return
     */
    public boolean checkLockedBathroom(){
        if(gamePanel.getCurrentMap().getMapName().equals("house")&&(!lockedBathroomDialogue)){
            Rectangle rectangle = new Rectangle(gamePanel.getTileSize()*37, (gamePanel.getTileSize()*21)+10, gamePanel.getTileSize()*1, gamePanel.getTileSize()*1);

            gamePanel.getPlayer().getSolidArea().x = gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getSolidArea().x;
            gamePanel.getPlayer().getSolidArea().y = gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getSolidArea().y;
            // Get the object's solid area position
            if(rectangle.intersects(gamePanel.getPlayer().getSolidArea())){
                lockedBathroomDialogue = true;
                return true;
            }
            gamePanel.getPlayer().getSolidArea().x = gamePanel.getPlayer().getSolidAreaDefaultX();
            gamePanel.getPlayer().getSolidArea().y = gamePanel.getPlayer().getSolidAreaDefaultY();
            return false;
        }
        return false;
    }
    /**
     * Check if the player is colliding with the neighbour's house.
     * This is used to display a dialogue about the locked door.
     * @return
     */
    public boolean checkLockedNeighborHouse(){
        if(gamePanel.getCurrentMap().getMapName().equals("origen")&&(!lockedNeighborHouseDialogue)){
            Rectangle rectangle = new Rectangle(gamePanel.getTileSize()*33, (gamePanel.getTileSize()*16)+10, gamePanel.getTileSize()*1, gamePanel.getTileSize()*1);

            gamePanel.getPlayer().getSolidArea().x = gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getSolidArea().x;
            gamePanel.getPlayer().getSolidArea().y = gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getSolidArea().y;
            if(rectangle.intersects(gamePanel.getPlayer().getSolidArea())){
                lockedNeighborHouseDialogue = true;
                return true;
            }
            gamePanel.getPlayer().getSolidArea().x = gamePanel.getPlayer().getSolidAreaDefaultX();
            gamePanel.getPlayer().getSolidArea().y = gamePanel.getPlayer().getSolidAreaDefaultY();
            return false;
        }
        return false;
    }
    /**
     * Check if the player reached the area to start the mall animation.
     * @return
     */
    public boolean checkMallCinematic(){
        if(gamePanel.getCurrentMap().getMapName().equals("mall")&&(!mallCinematic)){
            Rectangle rectangle = new Rectangle(gamePanel.getTileSize()*16, gamePanel.getTileSize()*34, gamePanel.getTileSize()*1, gamePanel.getTileSize()*1);
            gamePanel.getPlayer().getSolidArea().x = gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getSolidArea().x;
            gamePanel.getPlayer().getSolidArea().y = gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getSolidArea().y;
            if(rectangle.intersects(gamePanel.getPlayer().getSolidArea())){
                mallCinematic = true;
                return true;
            }
            gamePanel.getPlayer().getSolidArea().x = gamePanel.getPlayer().getSolidAreaDefaultX();
            gamePanel.getPlayer().getSolidArea().y = gamePanel.getPlayer().getSolidAreaDefaultY();
            return false;
        }
        return false;
    }
    /**
     * CHeck if the player reached the area to start the cave cinematic.
     * @return
     */
    public boolean checkCaveCinematic(){
        if(gamePanel.getCurrentMap().getMapName().equals("cave")&&(!caveCinematic)){
            Rectangle rectangle = new Rectangle(gamePanel.getTileSize()*23, gamePanel.getTileSize()*15, gamePanel.getTileSize()*3, gamePanel.getTileSize()*1);
            gamePanel.getPlayer().getSolidArea().x = gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getSolidArea().x;
            gamePanel.getPlayer().getSolidArea().y = gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getSolidArea().y;
            if(rectangle.intersects(gamePanel.getPlayer().getSolidArea())){
                caveCinematic = true;
                return true;
            }
            gamePanel.getPlayer().getSolidArea().x = gamePanel.getPlayer().getSolidAreaDefaultX();
            gamePanel.getPlayer().getSolidArea().y = gamePanel.getPlayer().getSolidAreaDefaultY();
            return false;
        }
        return false;
    }
    /**
     * Check if the map was already found.
     * @return
     */
    public boolean isMapFound() {return mapFound;}
    /**
     * Set whether the map is found or not.
     * @param mapFound
     */
    public void setMapFound(boolean mapFound) {this.mapFound = mapFound;}
    /**
     * Check if the diary was already found.
     * @return
     */
    public boolean isDiaryFound() {return diaryFound;}
    /**
     * Set whether the diary is found or not.
     * @param diaryFound
     */
    public void setDiaryFound(boolean diaryFound) {this.diaryFound = diaryFound;}
    /**
     * Check if the bat was already found.
     * @return
     */
    public boolean isBatFound() {return batFound;}
    /**
     * Set whether the bat is found or not.
     * @param batFound
     */
    public void setBatFound(boolean batFound) {this.batFound = batFound;}
    /**
     * Check if the locked mom bedroom dialogue was already displayed.
     * @return
     */
    public boolean isLockedMomBedroomDialogue() {return lockedMomBedroomDialogue;}
    /**
     * Set whether the locked mom bedroom dialogue was already displayed or not.
     * @param lockedMomBedroomDialogue
     */
    public void setLockedMomBedroomDialogue(boolean lockedMomBedroomDialogue) {this.lockedMomBedroomDialogue = lockedMomBedroomDialogue;}
    /**
     * Check if the locked bathroom dialogue was already displayed.
     * @return
     */
    public boolean isLockedBathroomDialogue() {return lockedBathroomDialogue;}
    /**
     * Set whether the locked bathroom dialogue was already displayed or not.
     * @param lockedBathroomDialogue
     */
    public void setLockedBathroomDialogue(boolean lockedBathroomDialogue) {this.lockedBathroomDialogue = lockedBathroomDialogue;}
    /**
     * Check if the locked neighbour house dialogue was already displayed.
     * @return
     */
    public boolean isLockedNeighborHouseDialogue() {return lockedNeighborHouseDialogue;}
    /**
     * Set whether the locked neighbour house dialogue was already displayed or not.
     * @param lockedNeighborHouseDialogue
     */
    public void setLockedNeighborHouseDialogue(boolean lockedNeighborHouseDialogue) {this.lockedNeighborHouseDialogue = lockedNeighborHouseDialogue;}
    /**
     * Check if the mall cinematic was already activated.
     * @return
     */
    public boolean isMallCinematic() {return mallCinematic;}
    /**
     * Set whether the mall cinematic was already displayed or not.
     * @param mallCinematic
     */
    public void setMallCinematic(boolean mallCinematic) {this.mallCinematic = mallCinematic;}
    /**
     * Check if the cave cinematic was already activated.
     * @return
     */
    public boolean isCaveCinematic() {return caveCinematic;}
    /**
     * Set whether the cave cinematic was already displayed or not.
     * @param caveCinematic
     */
    public void setCaveCinematic(boolean caveCinematic) {this.caveCinematic = caveCinematic;}
    /**
     *  Check if the bat hint message was already displayed.
     * @return
     */
    public boolean isBatHintDisplayed() {return batHintDisplayed;}
    /**
     * Set whether the bat hint was already displayed or not.
     * @param batHintDisplayed
     */
    public void setBatHintDisplayed(boolean batHintDisplayed) {this.batHintDisplayed = batHintDisplayed;}
}
