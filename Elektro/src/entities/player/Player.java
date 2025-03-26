package entities.player;
import entities.Entity;
import enums.AttackSpeed;
import enums.GameState;
import items.consumables.HealerItem;
import items.consumables.healingsFactory.BandageFactory;
import items.consumables.healingsFactory.FoodFactory;
import items.consumables.healingsFactory.HealingFactory;
import items.consumables.healingsFactory.KitFactory;
import items.weapons.AttackItem;
import items.weapons.weaponsFactories.BatFactory;
import items.weapons.weaponsFactories.ElectricBatFactory;
import items.weapons.weaponsFactories.ElectricGunFactory;
import items.weapons.weaponsFactories.WeaponFactory;

import java.util.ArrayList;

public class Player extends Entity {
    public static Player instance;
    public int screenX;
    public int screenY;
    private int defaultXPosition, defaultYPosition;
    private int standCounter;
    private boolean mapFound = false, diaryFound = false, batFound = false;
    private Player(int HP, AttackSpeed speed, AttackItem weapon) {
        super(HP,speed,weapon,null);

    }
    public void setDefaultValues(){
        // Default spawn position
        // When looking in the map file, use [y][2x] or [line][column]
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        screenX = (gamePanel.getScreenWidth()/2)-(gamePanel.getTileSize()/2);
        screenY = (gamePanel.getScreenHeight()/2)-(gamePanel.getTileSize()/2);
        defaultXPosition = gamePanel.getTileSize()*42;
        defaultYPosition = gamePanel.getTileSize()*27;
        getPlayerImage();
        worldX = defaultXPosition;
        worldY = defaultYPosition;
        speed = 3;
        direction = "down";
        maxLife = 6;
        life = maxLife;
        mapFound = false;
        diaryFound = false;
        healingsItems = new ArrayList<>();
        weaponsBag = new ArrayList<>();
        weapon = null;
        HP = 100;
    }
    public void setDefaultPositions(){
        worldX = defaultXPosition;
        worldY = defaultYPosition;
        direction = "down";
        HP = 100;
    }
    public void updateSpawnPosition(){
        defaultXPosition = gamePanel.getTileSize()*gamePanel.getCurrentMap().getDefaultXSpawn();
        defaultYPosition = gamePanel.getTileSize()*gamePanel.getCurrentMap().getDefaultYSpawn();
        worldX = defaultXPosition;
        worldY = defaultYPosition;
    }
    @Override
    public void update() {
        if(gamePanel.getGameState() == GameState.CAVE_CINEMATIC_MOVEMENT_STATE){
            spriteCounter++;
            if(spriteCounter>12){
                if(spriteNum==1|| spriteNum==0){
                    spriteNum = 2;
                } else if(spriteNum==2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
            if((worldX/gamePanel.getTileSize())==19&&(worldY/gamePanel.getTileSize())==13){
                gamePanel.getUi().setCurrentCutsceneCode("final_battle");
                gamePanel.setGameState(GameState.CAVE_CINEMATIC_STATE);
            } else if((worldX/gamePanel.getTileSize())==19){
                direction = "up";
                worldY -= speed;
            } else {
                direction = "left";
                worldX -= speed;
            }
        } else if(gamePanel.getKeyboardHandler().isUpPressed()||gamePanel.getKeyboardHandler().isDownPressed()||gamePanel.getKeyboardHandler().isLeftPressed()||gamePanel.getKeyboardHandler().isRightPressed()){
            if(gamePanel.getKeyboardHandler().isUpPressed()){
                direction = "up";
            } else if(gamePanel.getKeyboardHandler().isDownPressed()){
                direction = "down";
            } else if(gamePanel.getKeyboardHandler().isLeftPressed()){
                direction = "left";
            } else if(gamePanel.getKeyboardHandler().isRightPressed()){
                direction = "right";
            }
            // Check finished
            if(gamePanel.getCollisionChecker().checkFinishedMap()){
                gamePanel.finishGame();
            }
            // Check tile colission
            collissionOn = false;
            if(gamePanel.isCheckColissions()){
                gamePanel.getCollisionChecker().checkTile(this);
            }
            // Check object colission
            String keyObject = gamePanel.getCollisionChecker().checkObject(this, true);
            gamePanel.getCollisionChecker().checkEntity(this, gamePanel.getNpcs());
            pickUpObject(keyObject);
            // Check battle area intersected
            if(gamePanel.getCollisionChecker().checkBattleArea()){
                //gamePanel.gameState = gamePanel.battleState;
                float random = (int) (Math.random()*10+1);
                if(random > 5){
                    if (gamePanel.getCurrentMap().getMapName().equals("desert")) {
                        gamePanel.getUi().cutsceneMode("crystal_found");
                    } else if (gamePanel.getCurrentMap().getMapName().equals("forest")) {
                        gamePanel.getUi().cutsceneMode("wolf_found");
                    } else if(gamePanel.getCurrentMap().getMapName().equals("cave")){
                        gamePanel.getUi().cutsceneMode("infant_found");
                    }
                }
            }
            // Check locked mom's bedroom
            if(gamePanel.getCollisionChecker().checkLockedMomBedroom()){
                gamePanel.getUi().cutsceneMode("locked_mom_bedroom");
            }
            // Check locked bathroom
            if(gamePanel.getCollisionChecker().checkLockedBathroom()){
                gamePanel.getUi().cutsceneMode("locked_bathroom");
            }
            // Check locked neighbor house
            if(gamePanel.getCollisionChecker().checkLockedNeighborHouse()){
                gamePanel.getUi().cutsceneMode("locked_neighbor_house");
            }
            // Check mall cinematic
            if(gamePanel.getCollisionChecker().checkMallCinematic()){
                gamePanel.getUi().setCurrentCutsceneCode("ema_found");
                gamePanel.setGameState(GameState.MALL_CINEMATIC_STATE);
            }
            if(gamePanel.getCollisionChecker().checkCaveCinematic()){
                gamePanel.setGameState(GameState.CAVE_CINEMATIC_MOVEMENT_STATE);
            }
            // If colission is false, player can move
            if(collissionOn==false){
                switch (direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            spriteCounter++;
            if(spriteCounter>12){
                if(spriteNum==1|| spriteNum==0){
                    spriteNum = 2;
                } else if(spriteNum==2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else if(gamePanel.getKeyboardHandler().isEnterPressed()){
            // MOST MOVE IN ORDER TO CHECK COLISSION
            // Check  npc colission
            int npcIndex = gamePanel.getCollisionChecker().checkEntityInteraction(this, gamePanel.getNpcs());
            interactNPC(npcIndex);
        } else {
            standCounter++;
            if(standCounter==20){
                spriteNum = 0;
                standCounter = 0;
            }
        }
    }
    public void pickUpObject(String keyObject){
        if(!keyObject.equals("")){
            String[] keySplit = keyObject.split("_");
            gamePanel.getKeyboardHandler().setEnterPressed(false);
            if(keySplit[0].toLowerCase().equals(gamePanel.getCurrentMap().getMapName())){
                switch(keySplit[1].toLowerCase()){
                    case "map":
                        if(!gamePanel.getObjects().get(keyObject).isCollected()){
                            mapFound = true;
                            gamePanel.getUi().cutsceneMode("map_found");
                            gamePanel.getObjects().get(keyObject).setCollected(true);
                        }
                        break;
                    case "diary":
                        if(!gamePanel.getObjects().get(keyObject).isCollected()){
                            diaryFound = true;
                            gamePanel.getUi().cutsceneMode("diary_found");
                            gamePanel.getObjects().get(keyObject).setCollected(true);
                        }
                        break;
                    case "medkit":
                        if(!gamePanel.getObjects().get(keyObject).isCollected()){
                            HealingFactory hF = new KitFactory();
                            HealerItem healerItem = hF.createHealing();
                            this.getHealingsItems().add(healerItem);
                            gamePanel.getUi().cutsceneMode("medkit_found");
                            gamePanel.getObjects().get(keyObject).setCollected(true);
                        }
                        break;
                    case "bandage":
                        if(!gamePanel.getObjects().get(keyObject).isCollected()){
                            HealingFactory hF = new BandageFactory();
                            HealerItem healerItem = hF.createHealing();
                            this.getHealingsItems().add(healerItem);
                            this.getHealingsItems().add(healerItem);
                            this.getHealingsItems().add(healerItem);
                            gamePanel.getUi().cutsceneMode("bandage_found");
                            gamePanel.getObjects().get(keyObject).setCollected(true);
                        }
                        break;
                    case "berry":
                        if(!gamePanel.getObjects().get(keyObject).isCollected()){
                            HealingFactory hF = new FoodFactory();
                            HealerItem healerItem = hF.createHealing();
                            this.getHealingsItems().add(healerItem);
                            this.getHealingsItems().add(healerItem);
                            gamePanel.getUi().cutsceneMode("berry_found");
                            gamePanel.getObjects().get(keyObject).setCollected(true);
                        }
                        break;
                    case "bat":
                        batFound = true;
                        if(!gamePanel.getObjects().get(keyObject).isCollected()){
                            WeaponFactory wF = new BatFactory();
                            AttackItem weapon = wF.createWeapon();
                            this.setWeapon(weapon);
                            this.getWeaponsBag().add(weapon);
                            gamePanel.getUi().cutsceneMode("bat_found");
                            gamePanel.getObjects().get(keyObject).setCollected(true);
                        }
                        break;
                    case "electricbat":
                        if(!gamePanel.getObjects().get(keyObject).isCollected()){
                            WeaponFactory wF = new ElectricBatFactory();
                            AttackItem weapon = wF.createWeapon();
                            this.setWeapon(weapon);
                            this.getWeaponsBag().add(weapon);
                            gamePanel.getUi().cutsceneMode("electric_bat_found");
                            gamePanel.getObjects().get(keyObject).setCollected(true);
                        }
                        break;
                    case "electricgun":
                        if(!gamePanel.getObjects().get(keyObject).isCollected()){
                            WeaponFactory wF = new ElectricGunFactory();
                            AttackItem weapon = wF.createWeapon();
                            this.setWeapon(weapon);
                            this.getWeaponsBag().add(weapon);
                            gamePanel.getUi().cutsceneMode("electric_gun_found");
                            gamePanel.getObjects().get(keyObject).setCollected(true);
                        }
                        break;
                }
            }

        }
    }

    public void interactNPC(int index){
        if(index!=999){
            if(gamePanel.getKeyboardHandler().isEnterPressed()){
                gamePanel.setGameState(GameState.DIALOGUE_STATE);
                gamePanel.getNpcs()[index].speak();
            }
        }
        gamePanel.getKeyboardHandler().setEnterPressed(false);
    }
    public void getPlayerImage(){
        up = setup("/entities/player/ray-back1.png");
        up1 = setup("/entities/player/ray-back2.png");
        up2 = setup("/entities/player/ray-back3.png");
        down = setup("/entities/player/ray-front1.png");
        down1 = setup("/entities/player/ray-front2.png");
        down2 = setup("/entities/player/ray-front3.png");
        left = setup("/entities/player/ray-left1.png");
        left1 = setup("/entities/player/ray-left2.png");
        left2 = setup("/entities/player/ray-left3.png");
        right = setup("/entities/player/ray-right1.png");
        right1 = setup("/entities/player/ray-right2.png");
        right2 = setup("/entities/player/ray-right3.png");
    }
    public static Player getInstance(){
        if(instance==null){
            instance = new Player(100,AttackSpeed.STANDAR,null);
        }
        return instance;
    }
    public int getScreenX() {return screenX;}
    public int getScreenY() {return screenY;}
    public int getDefaultXPosition() {return defaultXPosition;}
    public void setDefaultXPosition(int defaultXPosition) {this.defaultXPosition = defaultXPosition;}
    public int getDefaultYPosition() {return defaultYPosition;}
    public void setDefaultYPosition(int defaultYPosition) {this.defaultYPosition = defaultYPosition;}
    public int getStandCounter() {return standCounter;}
    public void setStandCounter(int standCounter) {this.standCounter = standCounter;}
    public boolean isMapFound() {return mapFound;}
    public void setMapFound(boolean mapFound) {this.mapFound = mapFound;}
    public boolean isDiaryFound() {return diaryFound;}
    public void setDiaryFound(boolean diaryFound) {this.diaryFound = diaryFound;}
    public boolean isBatFound() {return batFound;}
    public void setBatFound(boolean batFound) {this.batFound = batFound;}
}
