package game;

import entities.Entity;
import entities.player.Player;
import enums.GameState;
import game.levelsfactory.ExplorationLevelFactory;
import game.levelsfactory.IntroductionLevelFactory;
import game.levelsfactory.LevelFactory;
import game.levelsfactory.levels.Level;
import game.mapsfactory.*;
import game.mapsfactory.maps.Map;
import items.VisualObject;
import utilities.UtilityTool;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
/**
 * Public class which handles everything in the game.
 * It's the game base.
 * @see JPanel
 * @see Runnable
 */
public class GamePanel extends JPanel implements Runnable {
    // Screen settings
    private final int originalTileSize = 16; // 16x16 tile
    private final int scale = 3;
    private final int tileSize = originalTileSize * scale; // 48x48 tile
    private final int maxScreenCol = 20;
    private final int maxScreenRow = 12;
    private final int screenWidth = tileSize * maxScreenCol; // 960 pixels
    private final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    // Handlers and managers
    private KeyboardHandler keyboardHandler = new KeyboardHandler(this);
    private AssetSetter assetSetter = new AssetSetter(this);
    private TileManager tileManager;
    private CollisionChecker collisionChecker = new CollisionChecker(this);
    private Thread gameThread;
    // UI
    private UI ui = new UI(this);
    private boolean developerMode = false;
    // Music and sound effect
    private Sound music = new Sound();
    private Sound soundEffect = new Sound();
    // FPS
    int FPS = 60;
    // World settings
    private int maxWorldCol = 50;
    private int maxWorldRow = 50;
    private final int worldWidth = tileSize*maxWorldCol;
    private final int worldHeight = tileSize*maxWorldRow;
    // Entities and objects
    //private SuperObject objects[][] = new SuperObject[10][1];
    private HashMap<String, VisualObject> objects = new HashMap<>();
    private Player player;
    private Entity npcs[] = new Entity[10];
    // Full screen
    private int screenWidth2 = screenWidth;
    private int screenHeight2 = screenHeight;
    private BufferedImage tempScreen;
    private Graphics2D g2d;
    private boolean fullScreenOn = false;
    private boolean checkColissions = true;
    // Game settings
    private GameState gameState;
    private String gameVersion;
    // Images
    private BufferedImage logo, mainMenu, batteryIcon, mapIcon, diaryIcon, diaryBackground, efrainDev, isaacDev, diegoDev, abrahamDev;
    // Levels and maps
    private LinkedList<Level> levelList;
    private Level currentLevel;
    private Map currentMap;
    /**
     * Public constructor.
     */
    public GamePanel(){
        //
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyboardHandler);
        this.setFocusable(true);
        // Set up
        player = Player.getInstance();
        player.setGamePanel(this);
        player.setDefaultValues();
        // Images
        logo = UtilityTool.setup("/logo/logo.png", this);
        mainMenu = UtilityTool.setup("/backgrounds/main_menu.png", this);
        batteryIcon = UtilityTool.setup("/items/visuals/battery.png", this);
        mapIcon = UtilityTool.setup("/items/visuals/map.png", this);
        diaryIcon = UtilityTool.setup("/items/visuals/diary.png", this);
        diaryBackground = UtilityTool.setup("/backgrounds/diary_background.png", this);
        efrainDev = UtilityTool.setup("/devs/EFRAIN.png", this);
        isaacDev = UtilityTool.setup("/devs/ISAAC.png", this);
        diegoDev = UtilityTool.setup("/devs/DIEGO.png", this);
        abrahamDev = UtilityTool.setup("/devs/ABRAHAM.png", this);
        // Scale
        batteryIcon = UtilityTool.scaleImage(batteryIcon, tileSize, tileSize);
        mapIcon = UtilityTool.scaleImage(mapIcon, tileSize, tileSize);
        diaryIcon = UtilityTool.scaleImage(diaryIcon, tileSize, tileSize);
        efrainDev = UtilityTool.scaleImage(efrainDev, tileSize, tileSize);
        isaacDev = UtilityTool.scaleImage(isaacDev, tileSize, tileSize);
        diegoDev = UtilityTool.scaleImage(diegoDev, tileSize, tileSize);
        abrahamDev = UtilityTool.scaleImage(abrahamDev, tileSize, tileSize);

    }
    /**
     * Set up the game.
     */
    public void setUpGame(){
        assetSetter.setObjects();
        assetSetter.setNPCs();
        playMusic(0);
        gameState = GameState.TITLE_STATE;
        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2d = (Graphics2D) tempScreen.getGraphics();
        //setFullScreen();
        loadLevels();
        currentLevel = levelList.getFirst();
        if(currentLevel == null){
            return;
        }
        currentLevel.setFinishedLevel(false);
        //currentLevel.play();
    }
    /**
     * Load the levels.
     */
    public void loadLevels(){
        System.out.println("Loading levels...");
        // Create level list
        levelList = new LinkedList<>();
        LevelFactory levelFactory = null;
        ArrayList<Map> maps = new ArrayList<>();
        // Create first level
        levelFactory = new IntroductionLevelFactory();
        Level level1 = levelFactory.createLevel(this);
        maps.add(new HouseMapFactory().createMap(this));
        maps.add(new OrigenMapFactory().createMap(this));
        maps.add(new DesertMapFactory().createMap(this));
        maps.add(new MallMapFactory().createMap(this));
        level1.setMaps(maps);

        maps = new ArrayList<>();
        levelFactory = new ExplorationLevelFactory();
        Level level2 = levelFactory.createLevel(this);
        maps.add(new ParkingLotMapFactory().createMap(this));
        maps.add(new ForestMapFactory().createMap(this));
        maps.add(new CaveMapFactory().createMap(this));
        level2.setMaps(maps);

        levelList.add(level1);
        levelList.add(level2);
    }
    /**
     * Retry the map.
     */
    public void retry(){
        ui.cutsceneMode("lost_battle");
        player.setDefaultPositions();
        currentMap.resetBattleZones();
        assetSetter.setNPCs();
        assetSetter.setObjects();
        ui.setOpacity(0.0f);
        if(currentMap.getMapName().equals("mall")){
            currentMap.loadEntities();
            ui.setFinishedMallCinematic(false);
            collisionChecker.setMallCinematic(false);
        }
        if(currentMap.getMapName().equals("cave")) {
            npcs[2] = null;
            npcs[3] = null;
            npcs[4] = null;
            npcs[5] = null;
            ui.setFinishedCaveCinematic(false);
            ui.setFinishedFinalCinematic(false);
            collisionChecker.setCaveCinematic(false);
            currentMap.loadEntities();
        }
    }
    /**
     * Restart the map.
     */
    public void restart(){
        player.setDefaultValues();
        player.setDefaultPositions();
        currentMap.resetBattleZones();
        assetSetter.setNPCs();
        assetSetter.setObjects();
        ui.setOpacity(0.0f);
        collisionChecker.setMapFound(false);
        collisionChecker.setDiaryFound(false);
    }

    /**
     * Finish the game.
     * If there's no next map, move to next level.
     * If there's no next level, finish game.
     */
    public void finishGame(){
        try{
            if(currentLevel.getMaps().get(currentLevel.getMaps().indexOf(currentMap)+1)!=null){
                currentMap = currentLevel.getMaps().get(currentLevel.getMaps().indexOf(currentMap)+1);
                player.updateSpawnPosition();
                currentMap.loadMap();
                tileManager = currentMap.getTileManager();
            }

        } catch(IndexOutOfBoundsException indexErrorMap){
            try{
                if(levelList.get(levelList.indexOf(currentLevel)+1)!=null){
                    currentLevel = levelList.get(levelList.indexOf(currentLevel)+1);
                    currentLevel.setUnlocked(true);
                    currentLevel.play();
                }
            } catch(IndexOutOfBoundsException indexErrorLevel){
                gameState = GameState.FINAL_CINEMATIC_FINISHED_STATE;
            }
        }
    }
    /**
     * Set full screen.
     */
    public void setFullScreen(){
        // Get local screen device
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
        graphicsDevice.setFullScreenWindow(Main.window);
        // Get full screen width and height
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
    }
    /**
     * Start the game thread.
     */
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();

    }
    /**
     * Run the game.
     */
    @Override
    public void run() {
        double drawInternal = 1000000000/FPS; // 0.01666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currenTime;
        long timer = 0;
        int drawCount = 0;
        while(gameThread!=null){
            currenTime = System.nanoTime();
            delta += (currenTime-lastTime)/drawInternal;
            timer += (currenTime-lastTime);
            lastTime = currenTime;
            if(delta >= 1){
                update();
                //repaint();
                drawToTemptScreen(); // Draw everything to the buffered image
                drawToScreen(); // Draw to  the buffered image to the screen
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                System.out.println("FPS: "+drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    /**
     * Update the game.
     */
    public void update(){
        if(gameState == GameState.PLAY_STATE || gameState == GameState.CAVE_CINEMATIC_MOVEMENT_STATE) {
            player.update();
            for(int i = 0; i < npcs.length; i++){
                if(npcs[i] != null){
                    npcs[i].update();
                }
            }
        }
        if(gameState == GameState.MALL_CINEMATIC_STATE || gameState == GameState.CAVE_CINEMATIC_STATE || gameState == GameState.FINAL_CINEMATIC_STATE){
            for(int i = 0; i < npcs.length; i++){
                if(npcs[i] != null){
                    npcs[i].update();
                }
            }
        }
        if(gameState == GameState.MAP_STATE){
            for(int i = 0; i < npcs.length; i++){
                if(npcs[i] != null){
                    npcs[i].update();
                }
            }
        }
        if(gameState == GameState.PAUSE_STATE){
            // Do nothing
        }
    }
    /**
     * Draw everything to tempt screen.
     */
    public void drawToTemptScreen(){
        // Debugging
        long drawStart = 0;
        if (keyboardHandler.isCheckDrawTime()) {
            drawStart = System.nanoTime();
        }
        // Title screen
        if(gameState == GameState.TITLE_STATE) {
            ui.draw(g2d);
        } else if(gameState == GameState.CHOOSE_LEVEL_STATE) {
            ui.draw(g2d);
        } else if(gameState == GameState.CREDITS_STATE){
            ui.draw(g2d);
        } else if(gameState == GameState.MAP_STATE){
            currentMap.drawFullMapScreen(g2d);
        } else {
            // Tile
            tileManager.draw(g2d);
            // Intro cinematic
            if(currentMap.isDisplayIntroCinematic()){
                ui.cutsceneMode(currentMap.getMapName());

            }
            // Object
            objects.forEach((k, v) -> {
                String[] keySplit = k.split("_");
                if(keySplit[0].toLowerCase().equals(currentMap.getMapName())){
                    if(!v.isCollected()){
                        v.draw(g2d, this);
                    }
                }
            });
            // Entity
            for(int i = 0; i < npcs.length; i++) {
                if (npcs[i] != null) {
                    npcs[i].draw(g2d);
                }
            }
            // Player
            player.draw(g2d);
            // UI
            ui.draw(g2d);
            // Debugging
            if(keyboardHandler.isCheckDrawTime()){
                long drawEnd = System.nanoTime();
                long passed = drawEnd - drawStart;
                g2d.setFont(getFont().deriveFont(32F));
                g2d.setColor(Color.WHITE);
                g2d.drawString("Draw time: "+passed,  10, 570);
                System.out.println("Draw time: "+passed);
            }
        }
    }
    /**
     * Draw to final screen.
     */
    public void drawToScreen(){
        Graphics graphics = getGraphics();
        graphics.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        graphics.dispose();
    }
    /**
     * Play music.
     * @param i
     */
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    /**
     * Stop the music.
     */
    public void stopMusic(){
        music.stop();
    }
    /**
     * Play the sound effect.
     * @param i
     */
    public void playSoundEffect(int i){
        soundEffect.setFile(i);
        soundEffect.play();
    }
    /**
     * Get the screen width.
     * @return
     */
    public int getScreenWidth() {return screenWidth;}
    /**
     * Get the screen height.
     * @return
     */
    public int getScreenHeight() {return screenHeight;}
    /**
     * Get the keyboard handler.
     * @return
     */
    public KeyboardHandler getKeyboardHandler() {return keyboardHandler;}
    /**
     * Get the tile manager.
      * @return
     */
    public TileManager getTileManager() {return tileManager;}
    /**
     * Set the tile manager.
     * @param tileManager
     */
    public void setTileManager(TileManager tileManager) {this.tileManager = tileManager;}
    /**
     * Get the collision checker.
     * @return
     */
    public CollisionChecker getCollisionChecker() {return collisionChecker;}
    /**
     * Check if the user is in developer mode.
     * @return
     */
    public boolean isDeveloperMode() {return developerMode;}
    /**
     * Set whether the user is in developer mode or not.
     * @param developerMode
     */
    public void setDeveloperMode(boolean developerMode) {this.developerMode = developerMode;}
    /**
     * Get the music manager.
     * @return
     */
    public Sound getMusic() {return music;}
    /**
     * Get the sound effect manager.
     * @return
     */
    public Sound getSoundEffect() {return soundEffect;}
    /**
     * Get the UI.
     * @return
     */
    public UI getUi() {return ui;}
    /**
     * Get the max world col.
     * @return
     */
    public int getMaxWorldCol() {return maxWorldCol;}
    /**
     * Set the max world col.
     * @param maxWorldCol
     */
    public void setMaxWorldCol(int maxWorldCol) {this.maxWorldCol = maxWorldCol;}
    /**
     * Get the max world row.
     * @return
     */
    public int getMaxWorldRow() {return maxWorldRow;}
    /**
     * Set the max world row.
     * @param maxWorldRow
     */
    public void setMaxWorldRow(int maxWorldRow) {this.maxWorldRow = maxWorldRow;}
    /**
     * Get the objects hashmap.
     * @return
     */
    public HashMap<String, VisualObject> getObjects() {return objects;}
    /**
     * Set the objects hashmap.
     * @param objects
     */
    public void setObjects(HashMap<String, VisualObject> objects) {this.objects = objects;}
    /**
     * Get the player.
     * @return
     */
    public Player getPlayer() {return player;}
    /**
     * Get the NPCs.
     * @return
     */
    public Entity[] getNpcs() {return npcs;}
    /**
     * Set the NPCs.
     * @return
     */
    public Graphics2D getG2d() {return g2d;}
    /**
     * Check if the window is in full screen.
     * @return
     */
    public boolean isFullScreenOn() {return fullScreenOn;}
    /**
     * Set the full screen mode.
     * @param fullScreenOn
     */
    public void setFullScreenOn(boolean fullScreenOn) {this.fullScreenOn = fullScreenOn;}
    /**
     * Check if collision is activated.
     * @return
     */
    public boolean isCheckColissions() {return checkColissions;}
    /**
     * Set if collisions are activated.
     * @param checkColissions
     */
    public void setCheckColissions(boolean checkColissions) {this.checkColissions = checkColissions;}
    /**
     * Get the game state.
     * @return
     */
    public GameState getGameState() {return gameState;}
    /**
     * Set the game state.
     * @param gameState
     */
    public void setGameState(GameState gameState) {this.gameState = gameState;}
    /**
     * Get the game version.
     * @return
     */
    public String getGameVersion() {return gameVersion;}
    /**
     * Set the game version.
     * @param gameVersion
     */
    public void setGameVersion(String gameVersion) {this.gameVersion = gameVersion;}
    /**
     * Get the game logo.
     * @return
     */
    public BufferedImage getLogo() {return logo;}
    /**
     * Get the main menu background.
     * @return
     */
    public BufferedImage getMainMenu() {return mainMenu;}
    /**
     * Get the map icon.
     * @return
     */
    public BufferedImage getMapIcon() {return mapIcon;}
    /**
     * Get the diary icon.
     * @return
     */
    public BufferedImage getDiaryIcon() {return diaryIcon;}
    /**
     * Get the diary background.
     * @return
     */
    public BufferedImage getDiaryBackground() {return diaryBackground;}
    /**
     * Get the level list.
     * @return
     */
    public LinkedList<Level> getLevelList() {return levelList;}
    /**
     * Get the current level.
     * @return
     */
    public Level getCurrentLevel() {return currentLevel;}
    /**
     * Set the current level.
     * @param currentLevel
     */
    public void setCurrentLevel(Level currentLevel) {this.currentLevel = currentLevel;}
    /**
     * Get the current map.
     * @return
     */
    public Map getCurrentMap() {return currentMap;}
    /**
     * Set the current map.
     * @param currentMap
     */
    public void setCurrentMap(Map currentMap) {this.currentMap = currentMap;}
    /**
     * Get Efraín Morales' image.
     * @return
     */
    public BufferedImage getEfrainDev() {return efrainDev;}
    /**
     * Get Isaac Tovar's image.
     * @return
     */
    public BufferedImage getIsaacDev() {return isaacDev;}
    /**
     * Get Diego Iraheta's image.
     * @return
     */
    public BufferedImage getDiegoDev() {return diegoDev;}
    /**
     * Get Abraham Flores' image.
     * @return
     */
    public BufferedImage getAbrahamDev() {return abrahamDev;}
    /**
     * Get the tile size.
     * @return
     */
    public int getTileSize() {return tileSize;}
    /**
     * Get the battery icon image.
     * @return
     */
    public BufferedImage getBatteryIcon() {return batteryIcon;}
    /**
     * Set the battery icon image.
     * @param batteryIcon
     */
    public void setBatteryIcon(BufferedImage batteryIcon) {this.batteryIcon = batteryIcon;}
}

