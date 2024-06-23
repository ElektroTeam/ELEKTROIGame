package game;

import entities.Entity;
import entities.player.Player;
import game.sound.enums.SoundType;
import game.sound.manager.SoundManager;
import items.SuperObject;
import utilities.UtilityTool;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {
    // Screen settings
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 960d pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    public KeyboardHandler keyboardHandler = new KeyboardHandler(this);
    public Thread gameThread;
    public AssetSetter assetSetter = new AssetSetter(this);
    public TileManager tileManager;
    public ColissionChecker colissionChecker = new ColissionChecker(this);
    public EventHandler eventHandler = new EventHandler(this);
    public boolean developerMode = false;

    // Music and sound effect
    public SoundManager musicManager;
    public SoundManager soundEffectManager;
    //public int creditsState;
    private SoundType currentMusic;

    //Sound music = new Sound();
    //Sound soundEffect = new Sound();
    // UI
    public UI ui = new UI(this);
    // FPS
    int FPS = 60;
    // World settings
    public int maxWorldCol = 50;
    public int maxWorldRow = 50;
    public final int maxMaps = 10;
    public int currentMap = 0;
    public final int worldWidth = tileSize*maxWorldCol;
    public final int worldHeight = tileSize*maxWorldRow;
    // Entities and objects
    public SuperObject objects[] = new SuperObject[10];
    public Player player = new Player(this, keyboardHandler);
    public Entity npcs[] = new Entity[10];
    // Full screen
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2d;
    public boolean fullScreenOn = false;
    // Game settings
    public int gameState;


    // ENUMS
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int optionsState = 4;
    public final int gameOverState = 5;
    public final int mapState = 6;
    public final int creditsState = 7;
    // Logo
    BufferedImage logo = UtilityTool.setup("/logo/logo.png", this);
    public Map1 map1;
    public Map2 map2;

    public GamePanel(){
        musicManager = new SoundManager();
        soundEffectManager = new SoundManager();
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyboardHandler);
        this.setFocusable(true);
    }
    public void setUpGame(){
        assetSetter.setObjects();
        assetSetter.setNPCs();
        //playMusic(0);
        gameState = titleState;
        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2d = (Graphics2D) tempScreen.getGraphics();
        setFullScreen();
        map1 = new Map1(this);
        map2 = new Map2(this);
        map1.loadMap();
        map2.loadMap();
        tileManager = map2.tileManager;
    }
    public void retry(){
        player.setDefaultPositions();
        assetSetter.setNPCs();
        assetSetter.setObjects();
    }
    public void restart(){
        player.setDefaultValues();
        player.setDefaultPositions();
        assetSetter.setNPCs();
        assetSetter.setObjects();
    }
    public void setFullScreen(){
        // Get local screen device
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
        graphicsDevice.setFullScreenWindow(Main.window);
        // Get full screen width and height
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();

    }
    /*@Override
    public void run() {
        double drawInternal = 1000000000/FPS; // 0.01666 seconds
        double nextDrawTime = System.nanoTime()+drawInternal;
        while(gameThread!=null){
            //System.out.println("The game loop is running.");
            // 1. UPDATE: update info like character positions
            update();
            // 2. DRAW: draw the screen with updated info
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if(remainingTime<0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInternal;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }*/
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
    public void update(){
        if(gameState == playState) {
            player.update();
            for(int i = 0; i < npcs.length; i++){
                if(npcs[i] != null){
                    npcs[i].update();
                }
            }
        }
        if(gameState == pauseState){
            // Do nothing
        }
    }
    public void drawToTemptScreen(){
        // Debugging
        long drawStart = 0;
        if (keyboardHandler.checkDrawTime) {
            drawStart = System.nanoTime();
        }
        // Title screen
        if(gameState == titleState) {
            ui.draw(g2d);
        } else if(gameState == mapState){
            System.out.println("Draw map");
            if(tileManager == map1.tileManager){
                map1.drawFullMapScreen(g2d);
            } else {
                map2.drawFullMapScreen(g2d);
            }
        } else if (gameState == creditsState) {
            ui.drawCreditsScreen(g2d);
        } else {
            // Tile
            tileManager.draw(g2d);
            // Object
            for(int i = 0; i<objects.length; i++){
                if(objects[i] != null){
                    objects[i].draw(g2d, this);
                }
            }
            // Entity
            for(int i = 0; i < npcs.length; i++){
                if(npcs[i]!=null){
                    npcs[i].draw(g2d);
                }
            }
            // Player
            player.draw(g2d);
            // UI
            ui.draw(g2d);
            // Debugging
            if(keyboardHandler.checkDrawTime){
                long drawEnd = System.nanoTime();
                long passed = drawEnd - drawStart;
                g2d.setFont(getFont().deriveFont(32F));
                g2d.setColor(Color.WHITE);
                g2d.drawString("Draw time: "+passed,  10, 570);
                System.out.println("Draw time: "+passed);
            }
        }
    }
    public void drawToScreen(){
        Graphics graphics = getGraphics();
        graphics.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        graphics.dispose();
    }
//    public void playMusic(int i){
//        music.setFile(i);
//        music.play();
//        music.loop();
//    }
//    public void stopMusic(){
//        music.stop();
//    }
//    public void playSoundEffect(int i){
//        soundEffect.setFile(i);
//        soundEffect.play();
//    }

    //Music
    public void playMusic(SoundType soundType) {
        if (currentMusic != null) {
            musicManager.stop(currentMusic);
        }
        musicManager.play(soundType);
        musicManager.loop(soundType);
        currentMusic = soundType;
    }

    public void stopMusic() {
        if (currentMusic != null) {
            musicManager.stop(currentMusic);
            currentMusic = null;
        }
    }

    public void playSoundEffect(SoundType soundType) {
        soundEffectManager.play(soundType);
    }

    public void setMusicVolumeScale(int volumeScale) {
        musicManager.setVolumeScale(volumeScale);
    }

    public void setSoundEffectVolumeScale(int volumeScale) {
        soundEffectManager.setVolumeScale(volumeScale);
    }
}


