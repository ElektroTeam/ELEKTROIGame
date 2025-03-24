package game;

import battle.Battle;
import battle.MapBattle;
import entities.Entity;
import enums.GameState;
import enums.StateOfBattle;
import game.cutscene.Cutscene;
import items.Heart;
import items.VisualObject;
import utilities.BattleExtensions;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * Public class which handles all UI in the game.
 */
public class UI {
    private GamePanel gamePanel;
    private Graphics2D g2d;
    private Font arial_40, arial_80B, purisaBold, pixelTimes;
    private BufferedImage heart_full, heart_half, heart_blank;
    private String currentDialogue = "";
    private String currentCutscene = "";
    private String currentCutsceneCode = "";
    private int cutsceneIndex = 0;
    private int commandNum = 0;
    private int subState = 0;
    private Entity npc;
    private int charIndex = 0;
    private String combinedText = "";
    private String[] diaryEntries = new String[20], credits = new String[20];
    private int diaryPage = 0, diaryIndex = 0;
    private boolean finishedMallCinematic = false, removeLockedDialogue = true, finishedCaveCinematic = false, finishedFinalCinematic = false;
    private float opacity = 0.0f;
    /**
     * Public constructor.
     * @param gamePanel
     */
    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B =  new Font("Arial", Font.PLAIN, 80);
        try{
            InputStream inputStream = getClass().getResourceAsStream("/fonts/PurisaBold.ttf");
            purisaBold = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            inputStream = getClass().getResourceAsStream("/fonts/PixelTimes.ttf");
            pixelTimes = Font.createFont(Font.TRUETYPE_FONT, inputStream);

        } catch (FontFormatException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        VisualObject heart = new Heart(gamePanel);
        heart_full = heart.getImage();
        heart_half = heart.getImage2();
        heart_blank = heart.getImage3();
        diaryEntries[0] = "May 15, 2020\n\nEverything has changed. Ever since they launched \nthe alerts, nothing is the same. \nNeither is the university. We still have classes, but \nalmost all students have decided to go back to their \nhouses. [...]";
        diaryEntries[1] = "May 16, 2020\n\nI'm still alive. For now. Classes have been suspended \nand everyone was sent back home. \nThe bells of the clock in the town hall square are \nalready ringing. It's 10:00 p.m. Everyone home.\nPS: Yes, there's a curfew here too.";
        diaryEntries[2] = "May 24, 2020\n\nIt took them a while, but they finally started \ncalling things by their name: Third World War. [...]\nPD. I wonder if there will be someone alive to write \nor read the future books.";
        diaryEntries[3] = "May 28, 2020\n\nIt happened. Everything has changed.\nOrigen suffered the first attack. The village that I \nthought that would never be attacked. I still cannot \nbelieve what happened yesterday... Mom and I went to \nthe mall to get supplies. We were in the car, without \nthe masks and getting ready to leave when she said \nshe had forgotten something. She got out of the car \nwithout the mask, when the siren started to sound. \nNext thing I remember is light.";
        diaryEntries[4] = "Everything happened very quickly: I turned to mom \nto yell at her to run, but the mask drowned out my \nvoice. Suddenly, she was thrown into the air. The \ncar also received the impact, the windows burst \nand it overturned with me. I started looking for \nmom, desperate. Disoriented and in shock, I walked \naround her car and found her. I don't know how we \ngot home, or how long it took. I put her mask on \nhim. Dad arrived three hours after the \nexplosion, he was worried when I told him that";
        diaryEntries[5] = "mom was not wearing the mask at the time of the\nexplosion. This morning she took blood samples \nfrom both of us to analyze in the laboratory. \nPS: World War III has broken out.";
        diaryEntries[6] = "May 31, 2020\n\nWe're not home anymore, and I don't know if we'll \never come back. I now write from my new room \nin the east complex. Some have decided to call it \nthe Ocaso. I don't even know how many meters \nunderground we are, or how far we are from \nOrigen. They don't want to tell us the \nwhereabouts of this place. [...]";
        diaryEntries[7] = "June 12, 2020\n\nMom has gotten worse. Dad took her directly \nto the clinic. [...] \nPS: They say that after the storm, calm comes. \nWhat they don't say is that, after the calm, \nhell always breaks loose.";
        diaryEntries[8] = "Aug 7, 2020\n\nMom is still undergoing treatment, and they are still \nnot able to determine what she has. They won't let \nme see her, even though I talk to her every night on \nthe phone. Today a notification appeared on all our \nphones: they are going to open a call to fill fifty \nplaces in the laboratories. I'll try to apply. [...]";
        diaryEntries[9] = "September 15, 2020\n\nI passed the admission exams. I'm in. \nPS: Mom is dead.";

        diaryEntries[18] = "Year 2034\n\nI've been alone down here for eight years. There's \nno one left. Sarah is gone... The government \ndecided to evacuate the survivors of the attack to \nthe emergency complex whose situation I do not \nknow. They take me for crazy, they say that I'm \nobsessed. But they are the crazy and the blind, who \nare satisfied with the life they have... \nBecause they also decided to use the vaccine that";
        diaryEntries[19] = "my father injected to go outside. They have \ncondemned their lives depending on external energy \nso that the heart doesn't suffer cardiac arrest caused \nby the vaccine. That is why I have contacted Sarah to \nsee what I have discovered. Because, finally, I have \nfound the solution. I have found the cure.";

        credits[0] = "Lead Developer: Efraín Morales";
        credits[1] = "Lead Designer: Isaac Tovar";
        credits[2] = "Gameplay developer: Diego Iraheta";
        credits[3] = "Audio Engineer: Abraham Flores";
        credits[4] = "Special thanks to: ";
        credits[5] = "Manu Carbajo & Javier Ruescas (Book writers)";
        credits[6] = "@RyiSnow (Game inspiration and ideas)";
        credits[7] = "Manuel Madriz (Instructor)";
        credits[8] = "Ing. Enmanuel Araujo & Ing. Mario López";
    }
    /**
     * Draw everything in the game (depending on the game state).
     * @param g2d
     */
    public void draw(Graphics2D g2d){
        this.g2d = g2d;
        //g2d.setFont(purisaBold);
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);
        // Title state
        if(gamePanel.getGameState() == GameState.TITLE_STATE) {
            drawTitleScreen();
        } else if(gamePanel.getGameState() == GameState.PLAY_STATE){
            drawIconButtons();
            drawCurrentMapName();
            drawPlayerLife();
            if(gamePanel.isDeveloperMode()){
                drawDeveloperModeWindow();
            }
        } else if (gamePanel.getGameState() == GameState.PAUSE_STATE){
            if(gamePanel.isDeveloperMode()){
                drawDeveloperModeWindow();
            }
            drawIconButtons();
            drawCurrentMapName();
            drawPlayerLife();
            drawPauseScreen();
        } else if(gamePanel.getGameState() == GameState.DIALOGUE_STATE){
            if(gamePanel.isDeveloperMode()){
                drawDeveloperModeWindow();
            }
            drawIconButtons();
            drawCurrentMapName();
            drawPlayerLife();
            drawDialogueScreen();
        } else if(gamePanel.getGameState() == GameState.OPTIONS_STATE){
            if(gamePanel.isDeveloperMode()){
                drawDeveloperModeWindow();
            }
            drawIconButtons();
            drawCurrentMapName();
            drawPlayerLife();
            drawOptionsScreen();
        } else if(gamePanel.getGameState() == GameState.GAME_OVER_STATE){
            if(gamePanel.isDeveloperMode()){
                drawDeveloperModeWindow();
            }
            drawIconButtons();
            drawCurrentMapName();
            drawPlayerLife();
            drawGameOverScreen();
        } else if(gamePanel.getGameState() == GameState.CUTSCENE_STATE){
            if(gamePanel.isDeveloperMode()){
                drawDeveloperModeWindow();
            }
            drawIconButtons();
            drawCurrentMapName();
            drawPlayerLife();
            //drawIntroCutsceneScreen();
            drawCutsceneScreen();
        } else if(gamePanel.getGameState() == GameState.CHOOSE_LEVEL_STATE){
            drawChoosingLevelWindow();
        } else if(gamePanel.getGameState() == GameState.DIARY_STATE){
            drawDiaryText();
        } else if(gamePanel.getGameState() == GameState.MALL_CINEMATIC_STATE) {
            drawMallCinematic();
        } else if(gamePanel.getGameState() == GameState.CAVE_CINEMATIC_STATE){
            drawCaveCinematic();
        } else if(gamePanel.getGameState() == GameState.FINAL_CINEMATIC_STATE) {
            drawFinalCinematic();
        } else if(gamePanel.getGameState() == GameState.FINAL_CINEMATIC_FINISHED_STATE){
            drawFinalCinematicFinished();
        } else if(gamePanel.getGameState() == GameState.BATTLE_STATE){
            if(gamePanel.isDeveloperMode()){
                drawDeveloperModeWindow();
            }
            drawIconButtons();
            drawCurrentMapName();
            drawPlayerLife();
            drawBattleScreen();
        } else if(gamePanel.getGameState() == GameState.CREDITS_STATE){
            drawCreditsWindow();
        }
    }
    /**
     * Draw the credits window.
     */
    public void drawCreditsWindow(){
        int x, y;
        String text;
        g2d.setColor(new Color(7, 17, 40));
        g2d.fillRect(0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight());
        // Title
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 24F));
        g2d.setColor(Color.WHITE);
        text = "CREDITS";
        x = getXforCenteredText(text);
        y = gamePanel.getTileSize();
        g2d.drawString(text, x, y);
        // Draw text
        for(int i = 0; i < credits.length; i++){
            if(credits[i]!=null&&!credits[i].equals("")){
                x = getXforCenteredText(credits[i]);
                y += gamePanel.getTileSize();
                if(i==4){
                    y += gamePanel.getTileSize();
                }
                g2d.drawString(credits[i], x, y);
                x -= gamePanel.getTileSize();
                // Draw character
                y -= (gamePanel.getTileSize() - 20);
                if(i==0){
                    g2d.drawImage(gamePanel.getEfrainDev(), x, y, null);
                } else if(i==1){
                    g2d.drawImage(gamePanel.getIsaacDev(), x, y, null);
                } else if(i==2){
                    g2d.drawImage(gamePanel.getDiegoDev(), x, y, null);
                } else if(i==3){
                    g2d.drawImage(gamePanel.getAbrahamDev(), x, y, null);
                }
                y += (gamePanel.getTileSize() - 20);
            }
        }

    }
    /**
     * Draw the battle mode screen.
     */
    public void drawBattleScreen(){
        g2d.setColor(new Color(0, 0, 0, 150));
        g2d.fillRect(0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight());
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 50F));
        String text;
        int x, y;
        text = "YOU ARE CURRENTLY";
        // Shadow
        g2d.setColor(Color.BLACK);
        x = getXforCenteredText(text);
        y = gamePanel.getTileSize()*6;
        drawMultipleLines(text, x, y);
        // Main text
        g2d.setColor(Color.WHITE);
        g2d.drawString(text, x-4, y-4);
        text = "IN BATTLE MODE";
        // Shadow
        g2d.setColor(Color.BLACK);
        x = getXforCenteredText(text);
        y += 40;
        drawMultipleLines(text, x, y);
        // Main text
        g2d.setColor(Color.WHITE);
        g2d.drawString(text, x-4, y-4);
    }
    /**
     * Draw the final cinematic (game finished) screen.
     */
    public void drawFinalCinematicFinished(){
        String text;
        int x, y;
        if(opacity>=1.0f){
            opacity = 1.0f;
        }
        g2d.setColor(new Color(0, 0, 0, opacity));
        g2d.fillRect(0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight());
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 50F));
        g2d.setColor(Color.WHITE);
        opacity += 0.005f;
        if(opacity>=1.0f){
            text = "Thanks for playing!";
            x = getXforCenteredText(text);
            y = gamePanel.getTileSize()*5;
            g2d.drawString(text, x, y);
            text = "New levels will come soon.";
            x = getXforCenteredText(text);
            y += 60;
            g2d.drawString(text, x, y);
        }
    }
    /**
     * Draw the final cinematic screen.
     */
    public void drawFinalCinematic(){
        String text;
        gamePanel.getPlayer().setDirection("up");
        gamePanel.getPlayer().setSpriteNum(0);
        if(currentCutsceneCode.equals("cave_final")){
            if(gamePanel.getNpcs()[2]!=null){
                gamePanel.getNpcs()[2].setDirection("down");
            }
        }
        String currentCutscenes[] = new String[20];
        int x = 0, y = 0, width = 0, height = 0, index = 0;
        for(Cutscene cutscene: gamePanel.getCurrentLevel().getCutscenes()){
            if(cutscene.getCode().equals(currentCutsceneCode)){
                currentCutscenes[index] = cutscene.getText();
                index++;
            }
        }
        x = gamePanel.getTileSize()*2;
        y = gamePanel.getTileSize()*8;
        width = gamePanel.getScreenWidth() - (gamePanel.getTileSize()*4);
        // It's currently for three lines, could be changed if needed
        height = gamePanel.getTileSize()*3;
        drawSubWindow(x, y, width, height);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 16F));
        x += gamePanel.getTileSize();
        y += gamePanel.getTileSize();
        if(currentCutscenes[cutsceneIndex]!=null) {
            char characters[] = currentCutscenes[cutsceneIndex].toCharArray();
            if(charIndex < characters.length){
                String s = String.valueOf(characters[charIndex]);
                combinedText = combinedText + s;
                currentCutscene = combinedText;
                charIndex++;
            }
            if(gamePanel.getKeyboardHandler().isEnterPressed()){
                charIndex = 0;
                combinedText = "";
                if(gamePanel.getGameState() == GameState.FINAL_CINEMATIC_STATE){
                    cutsceneIndex++;
                    gamePanel.getKeyboardHandler().setEnterPressed(false);
                }
            }
        } else {
            cutsceneIndex = 0;
            if(gamePanel.getGameState() == GameState.FINAL_CINEMATIC_STATE){
                if(currentCutsceneCode.equals("cave_final")) {
                    currentCutsceneCode = "finished_final_cinematic";
                } else if(currentCutsceneCode.equals("finished_final_cinematic")){
                    finishedFinalCinematic = true;
                    currentCutsceneCode = "";
                } else {
                    // Move player and Ema and display final messages
                    gamePanel.setGameState(GameState.FINAL_CINEMATIC_FINISHED_STATE);
                }
            }
        }
        drawMultipleLines(currentCutscene, x, y);
    }
    /**
     * Draw the cave cinematic screen.
     */
    public void drawCaveCinematic(){
        gamePanel.getPlayer().setDirection("up");
        gamePanel.getPlayer().setSpriteNum(0);
        String currentCutscenes[] = new String[20];
        int x = 0, y = 0, width = 0, height = 0, index = 0;
        for(Cutscene cutscene: gamePanel.getCurrentLevel().getCutscenes()){
            if(cutscene.getCode().equals(currentCutsceneCode)){
                currentCutscenes[index] = cutscene.getText();
                index++;
            }
        }
        x = gamePanel.getTileSize()*2;
        y = gamePanel.getTileSize()*8;
        width = gamePanel.getScreenWidth() - (gamePanel.getTileSize()*4);
        // It's currently for three lines, could be changed if needed
        height = gamePanel.getTileSize()*3;
        drawSubWindow(x, y, width, height);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 16F));
        x += gamePanel.getTileSize();
        y += gamePanel.getTileSize();
        if(currentCutscenes[cutsceneIndex]!=null) {
            char characters[] = currentCutscenes[cutsceneIndex].toCharArray();
            if(charIndex < characters.length){
                String s = String.valueOf(characters[charIndex]);
                combinedText = combinedText + s;
                currentCutscene = combinedText;
                charIndex++;
            }
            if(gamePanel.getKeyboardHandler().isEnterPressed()){
                charIndex = 0;
                combinedText = "";
                if(gamePanel.getGameState() == GameState.CAVE_CINEMATIC_STATE){
                    cutsceneIndex++;
                    gamePanel.getKeyboardHandler().setEnterPressed(false);
                }
            }
        } else {
            cutsceneIndex = 0;
            if(gamePanel.getGameState() == GameState.CAVE_CINEMATIC_STATE){
                if(currentCutsceneCode.equals("final_battle")) {
                    currentCutsceneCode = "enter_battle_mode_cave";
                } else if(currentCutsceneCode.equals("enter_battle_mode_cave")){
                    gamePanel.getNpcs()[4].setMoving(false);
                    gamePanel.getNpcs()[5].setMoving(false);
                    gamePanel.getNpcs()[4].setSpriteNum(0);
                    gamePanel.getNpcs()[5].setSpriteNum(0);
                    /*gamePanel.getNpcs()[2] = null;
                    gamePanel.getNpcs()[3] = null;
                    gamePanel.getNpcs()[4] = null;
                    gamePanel.getNpcs()[5] = null;*/
                    finishedCaveCinematic = true;
                    currentCutsceneCode = "";
                } else {
                    gamePanel.setGameState(GameState.BATTLE_STATE);
                    Entity enemy = gamePanel.getCurrentMap().getMultipleEnemies().get(1);
                    Battle bt = new Battle(StateOfBattle.readyAndGo,gamePanel.getPlayer(),enemy,true,MapBattle.verifyEnemyBackground(gamePanel.getCurrentMap(),enemy), gamePanel);
                    BattleExtensions.reset(Optional.empty(),enemy);
                    bt.gameplay();
                }
            }
        }
        drawMultipleLines(currentCutscene, x, y);
    }
    /**
     * Draw the mall cinematic screen.
     */
    public void drawMallCinematic(){
        gamePanel.getPlayer().setDirection("right");
        gamePanel.getPlayer().setSpriteNum(0);
        if(currentCutsceneCode.equals("ema_found")){
            if(gamePanel.getNpcs()[9]!=null){
                gamePanel.getNpcs()[9].setDirection("left");
            }
        }
        String currentCutscenes[] = new String[20];
        int x = 0, y = 0, width = 0, height = 0, index = 0;
        for(Cutscene cutscene: gamePanel.getCurrentLevel().getCutscenes()){
            if(cutscene.getCode().equals(currentCutsceneCode)){
                currentCutscenes[index] = cutscene.getText();
                index++;
            }
        }
        x = gamePanel.getTileSize()*2;
        y = gamePanel.getTileSize()*8;
        width = gamePanel.getScreenWidth() - (gamePanel.getTileSize()*4);
        // It's currently for three lines, could be changed if needed
        height = gamePanel.getTileSize()*3;
        drawSubWindow(x, y, width, height);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 16F));
        x += gamePanel.getTileSize();
        y += gamePanel.getTileSize();
        if(currentCutscenes[cutsceneIndex]!=null) {
            char characters[] = currentCutscenes[cutsceneIndex].toCharArray();
            if(charIndex < characters.length){
                String s = String.valueOf(characters[charIndex]);
                combinedText = combinedText + s;
                currentCutscene = combinedText;
                charIndex++;
            }
            if(gamePanel.getKeyboardHandler().isEnterPressed()){
                charIndex = 0;
                combinedText = "";
                if(gamePanel.getGameState() == GameState.MALL_CINEMATIC_STATE){
                    cutsceneIndex++;
                    gamePanel.getKeyboardHandler().setEnterPressed(false);
                }
            }
        } else {
            cutsceneIndex = 0;
            if(gamePanel.getGameState() == GameState.MALL_CINEMATIC_STATE){
                if(currentCutsceneCode.equals("ema_found")) {
                    currentCutsceneCode = "enter_bob";
                } else if(currentCutsceneCode.equals("enter_bob")) {
                    currentCutsceneCode = "enter_battle_mode_mall";
                } else if(currentCutsceneCode.equals("enter_battle_mode_mall")){
                    gamePanel.getNpcs()[9] = null;
                    gamePanel.getNpcs()[8] = null;
                    gamePanel.getNpcs()[7] = null;
                    gamePanel.getNpcs()[6] = null;
                    finishedMallCinematic = true;
                    currentCutsceneCode = "";
                } else {
                    // Enter battle state
                    gamePanel.setGameState(GameState.BATTLE_STATE);
                    BattleExtensions.reset(Optional.of(gamePanel.getCurrentMap().getBoss()),null);
                    Battle bt = new Battle(StateOfBattle.readyAndGo, gamePanel.getPlayer(), gamePanel.getCurrentMap().getBoss(),true,gamePanel.getCurrentMap().getBattleBackground(), gamePanel);
                    bt.getEnemy().setHP(500);
                    bt.gameplay();
                }
            }
        }
        drawMultipleLines(currentCutscene, x, y);
    }
    /**
     * Draw the diary screen.
     */
    public void drawDiaryText(){
        int x, y;
        // Background
        g2d.drawImage(gamePanel.getDiaryBackground(), 0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight(), null);
        // Line
        g2d.setColor(Color.GRAY);
        g2d.setFont(g2d.getFont().deriveFont(16F));
        g2d.fillRect((gamePanel.getScreenWidth()/2)-(gamePanel.getTileSize()/2), gamePanel.getTileSize()-12, gamePanel.getTileSize(), gamePanel.getScreenHeight()-(gamePanel.getTileSize()*2));
        // Buttons
        g2d.setFont(g2d.getFont().deriveFont(14F));
        g2d.setColor(Color.WHITE);
        g2d.drawString(new String("(A) PREVIOUS PAGE    (D) NEXT PAGE    (ESC) Close diary").toUpperCase(), gamePanel.getTileSize(), (gamePanel.getTileSize()*12)-10);

        try{
            g2d.setFont(g2d.getFont().deriveFont(16F));
            g2d.setColor(Color.GRAY);
            diaryIndex = diaryPage*2;
            // Draw page number
            x = gamePanel.getTileSize();
            y = (gamePanel.getTileSize()*11)-20;
            g2d.drawString(String.valueOf(diaryIndex+1), x, y);
            x += gamePanel.getTileSize()*18;
            g2d.drawString(String.valueOf(diaryIndex+2), x, y);
            // Draw entry
            if(diaryEntries[diaryIndex]!=null){
                x = gamePanel.getTileSize();
                y = gamePanel.getTileSize()*2;
                drawMultipleLines(diaryEntries[diaryIndex], x, y);
            }
            diaryIndex++;
            if(diaryEntries[diaryIndex]!=null){
                x = 30 + gamePanel.getTileSize()*10;
                y = gamePanel.getTileSize()*2;
                drawMultipleLines(diaryEntries[diaryIndex], x, y);
            }
        } catch(ArrayIndexOutOfBoundsException e){
            diaryPage--;
        }
    }
    /**
     * Draw the icon buttons in the play state.
     */
    public void drawIconButtons(){
        int x, y;
        x = 10;
        y = gamePanel.getTileSize()*6;
        // Draw icons
        if(gamePanel.getPlayer().isMapFound()){
            g2d.drawImage(gamePanel.getMapIcon(), x, y, null);
            x = 15+gamePanel.getTileSize();
            y = gamePanel.getTileSize()*6 + gamePanel.getTileSize()/2 + 10;
            g2d.drawString("(1)", x, y);
        }
        if(gamePanel.getPlayer().isDiaryFound()){
            x = 10;
            y = gamePanel.getTileSize()*7;
            g2d.drawImage(gamePanel.getDiaryIcon(), x, y, null);
            x = 15+gamePanel.getTileSize();
            y = gamePanel.getTileSize()*7 + gamePanel.getTileSize()/2 + 10;
            g2d.drawString("(2)", x, y);
        }
    }
    /**
     * Drw the choose level screen.
     */
    public void drawChoosingLevelWindow(){
        int x, y;
        String text;
        g2d.setColor(new Color(7, 17, 40));
        g2d.fillRect(0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight());
        // Title
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 30F));
        g2d.setColor(Color.WHITE);
        text = "Which level do you want to play?";
        x = getXforCenteredText(text);
        y = gamePanel.getTileSize()*3;
        g2d.drawString(text, x, y);
        y += gamePanel.getTileSize()*2;
        // Levels
        for(int i = 0; i < gamePanel.getLevelList().size(); i++){
            if(gamePanel.getLevelList().get(i)!=null){
                text = gamePanel.getLevelList().get(i).getTitle().toUpperCase();
                if(gamePanel.getLevelList().get(i).isUnlocked()) {
                    text += " (UNLOCKED)";
                }
                x = getXforCenteredText(text);
                g2d.drawString(text, x, y);
                if(commandNum==i){
                    g2d.drawString(">", x-40, y);
                    if(gamePanel.getKeyboardHandler().isEnterPressed()) {
                        if (gamePanel.getLevelList().get(i).isUnlocked()||gamePanel.isDeveloperMode()) {
                            gamePanel.setCurrentLevel(gamePanel.getLevelList().get(i));
                            gamePanel.setGameState(GameState.PLAY_STATE);
                            gamePanel.getCurrentLevel().play();
                        } else {
                            removeLockedDialogue = false;
                            gamePanel.getKeyboardHandler().setEnterPressed(false);
                        }

                    }

                }
                y += gamePanel.getTileSize();
            }
        }
        if(!removeLockedDialogue){
            text = "You cannot play this level because \nyou need to unlock the previous one first.";
            x = gamePanel.getTileSize()*2;
            y = gamePanel.getTileSize()*8;
            drawMultipleLines(text, x, y);
            if(gamePanel.getKeyboardHandler().isEnterPressed()){
                gamePanel.setGameState(GameState.TITLE_STATE);
            }
        }
        // Back to title
        text = "BACK TO MAIN MENU";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize()*2;
        g2d.drawString(text, x, y);
        if(commandNum==gamePanel.getLevelList().size()){
            g2d.drawString(">", x-40, y);
            if(gamePanel.getKeyboardHandler().isEnterPressed()){
                gamePanel.setGameState(GameState.TITLE_STATE);
                commandNum = 0;
                gamePanel.getKeyboardHandler().setEnterPressed(false);
            }
        }
        gamePanel.getKeyboardHandler().setEnterPressed(false);
    }
    /**
     * Draw the game over screen.
     */
    public void drawGameOverScreen(){
        g2d.setColor(new Color(0, 0, 0, 150));
        g2d.fillRect(0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight());
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 110F));
        String text;
        int x, y;
        text = "Game Over";
        // Shadow
        g2d.setColor(Color.BLACK);
        x = getXforCenteredText(text);
        y = gamePanel.getTileSize()*4;
        g2d.drawString(text, x, y);
        // Main text
        g2d.setColor(Color.WHITE);
        g2d.drawString(text, x-4, y-4);
        // Retry
        g2d.setFont(g2d.getFont().deriveFont(50F));
        text = "Retry";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize()*4;
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
    /**
     * Draw the player life screen.
     */
    public void drawPlayerLife(){
        int x = gamePanel.getTileSize()/2;
        int y = gamePanel.getTileSize()/2;
        int i = 0;
        g2d.drawImage(gamePanel.getBatteryIcon(), x, y, null);
        /*// Draw blank heart
        while(i < gamePanel.getPlayer().getMaxLife()/2){
            g2d.drawImage(heart_blank, x, y, null);
            i++;
            x += gamePanel.getTileSize();
        }
        // Reset
        x = gamePanel.getTileSize()/2;
        y = gamePanel.getTileSize()/2;
        i = 0;
        // Draw current life
        while(i < gamePanel.getPlayer().getLife()){
            g2d.drawImage(heart_half, x, y, null);
            i++;
            if(i < gamePanel.getPlayer().getLife()){
                g2d.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gamePanel.getTileSize();
        }*/
    }
    /**
     * Draw the title screen.
     */
    public void drawTitleScreen(){
        g2d.setColor(new Color(7, 17, 40));
        g2d.fillRect(0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight());
        // Background image
        //g2d.drawImage(gamePanel.getMainMenu(), 0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight(), null);
        // Title name
        //g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 56F));
        g2d.setFont(pixelTimes);
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 64F));
        String text = "ELEKTRO 1";
        int x, y;
        x = getXforCenteredText(text);
        y = gamePanel.getTileSize()*2;
        // Shadow
        g2d.setColor(Color.GRAY);
        g2d.drawString(text, x+5, y+5);
        // Main color
        g2d.setColor(Color.WHITE);
        g2d.drawString(text, x, y);
        y += gamePanel.getTileSize();
        text = "You Can (Not) Redo";
        g2d.setColor(Color.CYAN);
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize();
        g2d.drawString(text, x, y);
        // Logo
        x = (gamePanel.getScreenWidth()/2) - (gamePanel.getTileSize()*3)/2;
        y += gamePanel.getTileSize();
        //g2d.drawImage(gamePanel.getLogo(), x, y, gamePanel.getTileSize()*3, gamePanel.getTileSize()*3, null);
        // Menu
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 48F));
        g2d.setColor(Color.WHITE);
        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize()*3;
        g2d.drawString(text, x, y);
        if(commandNum==0){
            g2d.drawString(">", x-gamePanel.getTileSize(), y);
        }
        /*text = "SETTINGS";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize();
        g2d.drawString(text, x, y);
        if(commandNum==1){
            g2d.drawString(">", x-gamePanel.getTileSize(), y);
        }*/
        text = "CREDITS";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize();
        g2d.drawString(text, x, y);
        if(commandNum==1){
            g2d.drawString(">", x-gamePanel.getTileSize(), y);
        }
        text = "QUIT";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize();
        g2d.drawString(text, x, y);
        if(commandNum==2){
            g2d.drawString(">", x-gamePanel.getTileSize(), y);
        }

        // Dev mode window
        if(gamePanel.isDeveloperMode()){
            g2d.setFont(g2d.getFont().deriveFont(18F));
            text = "Developer mode";
            x = 5;
            y = gamePanel.getTileSize()*11;
            g2d.drawString(text, x, y);
        }
        // Game version
        text = "v1.0.0";
        x = 7;
        y = gamePanel.getTileSize()*12;
        g2d.setFont(g2d.getFont().deriveFont(18F));
        g2d.drawString(text, x, y);
    }
    /**
     * Draw the pause screen.
     */
    public void drawPauseScreen(){
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(70F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gamePanel.getScreenHeight()/2;
        g2d.drawString(text, x, y);
    }
    /**
     * Draw the intro cutscene screen (deprecated).
     */
    public void drawIntroCutsceneScreen(){
        String currentCutscenes[] = new String[100];
        String code = gamePanel.getCurrentMap().getMapName().toLowerCase();
        int x = 0, y = 0, width = 0, height = 0, index = 0;
        for(Cutscene cutscene: gamePanel.getCurrentLevel().getCutscenes()){
            if(cutscene.getCode().equals(code)){
                currentCutscenes[index] = cutscene.getText();
                index++;
            }
        }
        index = 0;
        x = gamePanel.getTileSize()*2;
        y = gamePanel.getTileSize()/2;
        width = gamePanel.getScreenWidth() - (gamePanel.getTileSize()*4);
        // It's currently for three lines, could be changed if needed
        height = gamePanel.getTileSize()*3;
        drawSubWindow(x, y, width, height);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 16F));
        x += gamePanel.getTileSize();
        y += gamePanel.getTileSize();
        if(currentCutscenes[cutsceneIndex]!=null) {
            char characters[] = currentCutscenes[cutsceneIndex].toCharArray();
            if(charIndex < characters.length){
                String s = String.valueOf(characters[charIndex]);
                combinedText = combinedText + s;
                currentCutscene = combinedText;
                charIndex++;
            }
            if(gamePanel.getKeyboardHandler().isEnterPressed()){
                charIndex = 0;
                combinedText = "";
                if(gamePanel.getGameState() == GameState.CUTSCENE_STATE){
                    cutsceneIndex++;
                    gamePanel.getKeyboardHandler().setEnterPressed(false);
                }
            }
        } else {
            gamePanel.getCurrentMap().setDisplayIntroCinematic(false);
            cutsceneIndex = 0;
            if(gamePanel.getGameState() == GameState.CUTSCENE_STATE){
                gamePanel.setGameState(GameState.PLAY_STATE);
            }
        }
        drawMultipleLines(currentCutscene, x, y);
    }
    /**
     * Enter in cutscene mode.
     * @param code
     */
    public void cutsceneMode(String code){
        gamePanel.setGameState(GameState.CUTSCENE_STATE);
        this.currentCutsceneCode = code;
    }
    /**
     * Draw the cutscene screen.
     */
    public void drawCutsceneScreen() {
        String currentCutscenes[] = new String[100];
        int x = 0, y = 0, width = 0, height = 0, index = 0;
        for(Cutscene cutscene: gamePanel.getCurrentLevel().getCutscenes()){
            if(cutscene.getCode().equals(currentCutsceneCode)){
                currentCutscenes[index] = cutscene.getText();
                index++;
            }
        }
        index = 0;
        x = gamePanel.getTileSize()*2;
        y = gamePanel.getTileSize()/2;
        width = gamePanel.getScreenWidth() - (gamePanel.getTileSize()*4);
        // It's currently for three lines, could be changed if needed
        height = gamePanel.getTileSize()*3;
        drawSubWindow(x, y, width, height);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 16F));
        x += gamePanel.getTileSize();
        y += gamePanel.getTileSize();
        if(currentCutscenes[cutsceneIndex]!=null) {
            char characters[] = currentCutscenes[cutsceneIndex].toCharArray();
            if(charIndex < characters.length){
                String s = String.valueOf(characters[charIndex]);
                combinedText = combinedText + s;
                currentCutscene = combinedText;
                charIndex++;
            }
            if(gamePanel.getKeyboardHandler().isEnterPressed()){
                charIndex = 0;
                combinedText = "";
                if(gamePanel.getGameState() == GameState.CUTSCENE_STATE){
                    cutsceneIndex++;
                    gamePanel.getKeyboardHandler().setEnterPressed(false);
                }
            }
        } else {
            cutsceneIndex = 0;
            if(gamePanel.getGameState() == GameState.CUTSCENE_STATE){
                if(currentCutsceneCode.equals("crystal_found")||currentCutsceneCode.equals("wolf_found")||currentCutsceneCode.equals("infant_found")) {
                    gamePanel.setGameState(GameState.BATTLE_STATE);
                    Entity enemy = gamePanel.getCurrentMap().getEnemy();
                    Battle bt = new Battle(StateOfBattle.readyAndGo, gamePanel.getPlayer(), enemy, false, MapBattle.verifyEnemyBackground(gamePanel.getCurrentMap(), enemy), gamePanel);
                    BattleExtensions.reset(Optional.empty(), gamePanel.getCurrentMap().getEnemy());
                    //try weapon inventory
                    bt.gameplay();
                } else if(currentCutsceneCode.equals("final_boss")){
                    gamePanel.setGameState(GameState.BATTLE_STATE);
                    Battle bt = new Battle(StateOfBattle.readyAndGo, gamePanel.getPlayer(), gamePanel.getCurrentMap().getBoss(),true,MapBattle.verifyEnemyBackground(gamePanel.getCurrentMap(), gamePanel.getCurrentMap().getBoss()), gamePanel);
                    BattleExtensions.reset(Optional.of(gamePanel.getCurrentMap().getBoss()),null);
                    //try weapon inventory
                    bt.gameplay();
                } else {
                    gamePanel.setGameState(GameState.PLAY_STATE);
                    if(gamePanel.getCurrentMap().isDisplayIntroCinematic()){
                        gamePanel.getCurrentMap().setDisplayIntroCinematic(false);
                    }
                }
            }
        }
        drawMultipleLines(currentCutscene, x, y);
    }
    /**
     * Draw the dialogue screen.
     */
    public void drawDialogueScreen(){
        int x, y, width, height;
        x = gamePanel.getTileSize()*2;
        y = gamePanel.getTileSize()/2;
        width = gamePanel.getScreenWidth() - (gamePanel.getTileSize()*4);
        height = gamePanel.getTileSize()*3;
        drawSubWindow(x, y, width, height);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 16F));
        x += gamePanel.getTileSize();
        y += gamePanel.getTileSize();
        if(npc.getDialogues()[npc.getDialogueSet()][npc.getDialogueIndex()]!=null){
            //currentDialogue = npc.getDialogues()[npc.getDialogueSet()][npc.getDialogueIndex()];
            char characters[] = npc.getDialogues()[npc.getDialogueSet()][npc.getDialogueIndex()].toCharArray();
            if(charIndex < characters.length){
                String s = String.valueOf(characters[charIndex]);
                combinedText = combinedText + s;
                currentDialogue = combinedText;
                charIndex++;
            }
            if(gamePanel.getKeyboardHandler().isEnterPressed()){
                charIndex = 0;
                combinedText = "";
                if(gamePanel.getGameState() == GameState.DIALOGUE_STATE){
                    npc.setDialogueIndex(npc.getDialogueIndex()+1);
                    gamePanel.getKeyboardHandler().setEnterPressed(false);
                }
            }
        } else {
            npc.setDialogueIndex(0);
            if(gamePanel.getGameState() == GameState.DIALOGUE_STATE){
                gamePanel.setGameState(GameState.PLAY_STATE);
            }
        }
        drawMultipleLines(currentDialogue, x, y);

    }
    /**
     * Draw the current map name.
     */
    public void drawCurrentMapName(){
        try {
            int x = gamePanel.getTileSize()/2;
            int y = gamePanel.getTileSize()*2;
            g2d.setFont(g2d.getFont().deriveFont(32F));
            g2d.drawString(gamePanel.getCurrentMap().getMapName().toUpperCase(), x, y);
        } catch(NullPointerException e){
            // Do something later
        }
    }
    /**
     * Draw a sub window.
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public void drawSubWindow(int x, int y, int width, int height){
        try {
            Color color = new Color(0, 0, 0, 210);
            g2d.setColor(color);
            g2d.fillRoundRect(x, y, width, height, 35, 35);
            color = new Color(255, 255, 255);
            g2d.setColor(color);
            g2d.setStroke(new BasicStroke(5));
            g2d.drawRoundRect(x+5, y+5, width-10, height-10, 35, 35);
        } catch(NullPointerException e){
            // Do seomthing later
        }
    }
    /**
     * Draw the options screen.
     */
    public void drawOptionsScreen(){
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(32F));
        // Sub window
        int frameX = gamePanel.getTileSize()*6;
        int frameY = gamePanel.getTileSize();
        int frameWidth = gamePanel.getTileSize()*8;
        int frameHeight = gamePanel.getTileSize()*10;
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
        gamePanel.getKeyboardHandler().setEnterPressed(false);

    }
    /**
     * Draw the top options screen.
     * @param frameX
     * @param frameY
     */
    public void optionsTop(int frameX, int frameY){
        int textX;
        int textY;
        // Title
        String text = "Options";
        textX = getXforCenteredText(text);
        textY = frameY + gamePanel.getTileSize();
        g2d.drawString(text, textX, textY);
        g2d.setFont(g2d.getFont().deriveFont(24F));
        // Full screen on/off
        text = "Full screen";
        textX  = frameX + gamePanel.getTileSize();
        textY += gamePanel.getTileSize()*2;
        g2d.drawString(text, textX, textY);
        if(commandNum == 0){
            g2d.drawString(">", textX-25, textY);
            if(gamePanel.getKeyboardHandler().isEnterPressed()){
                if(gamePanel.isFullScreenOn()){
                    gamePanel.setFullScreenOn(false);
                } else {
                    gamePanel.setFullScreenOn(true);
                }
                subState = 1;
            }
        }
        // Music
        text = "Music";
        textY += gamePanel.getTileSize();
        g2d.drawString(text, textX, textY);
        if(commandNum == 1){
            g2d.drawString(">", textX-25, textY);
        }
        // Sound effects
        text = "SE";
        textY += gamePanel.getTileSize();
        g2d.drawString(text, textX, textY);
        if(commandNum == 2){
            g2d.drawString(">", textX-25, textY);
        }
        // Controls
        text = "Controls";
        textY += gamePanel.getTileSize();
        g2d.drawString(text, textX, textY);
        if(commandNum == 3){
            g2d.drawString(">", textX-25, textY);
            if(gamePanel.getKeyboardHandler().isEnterPressed()){
                subState = 2;
                commandNum = 0;
            }
        }
        // End game
        text = "End game";
        textY += gamePanel.getTileSize();
        g2d.drawString(text, textX, textY);
        if(commandNum == 4){
            g2d.drawString(">", textX-25, textY);
            if(gamePanel.getKeyboardHandler().isEnterPressed()){
                subState = 3;
                commandNum = 0;
            }
        }
        // Back
        text = "Back";
        textY += gamePanel.getTileSize()*2;
        g2d.drawString(text, textX, textY);
        if(commandNum == 5){
            g2d.drawString(">", textX-25, textY);
            if(gamePanel.getKeyboardHandler().isEnterPressed()){
                gamePanel.setGameState(GameState.PLAY_STATE);
                commandNum = 0;
            }
        }

        // Full screen checkbox
        textX = frameX + (int) (gamePanel.getTileSize()*4.5);
        textY = frameY + gamePanel.getTileSize()*2 + 24;
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(textX, textY, 24, 24);
        if(gamePanel.isFullScreenOn()){
            g2d.fillRect(textX, textY, 24, 24);
        }
        // Music volume
        textY += gamePanel.getTileSize();
        g2d.drawRect(textX, textY, 120, 24); // 120 / 5  = 24
        int volumeWidth = 24*gamePanel.getMusic().volumeScale;
        g2d.fillRect(textX, textY, volumeWidth, 24);
        // Sound effects
        textY += gamePanel.getTileSize();
        g2d.drawRect(textX, textY, 120, 24);
        volumeWidth = 24*gamePanel.getSoundEffect().volumeScale;
        g2d.fillRect(textX, textY, volumeWidth, 24);

    }
    /**
     * Draw the full screen notification screen.
     * @param frameX
     * @param frameY
     */
    public void optionsFullScreenNotification(int frameX, int frameY){
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(20F));
        String text = "This option is \ncurrently not available\non your device.";
        int textX = frameX + gamePanel.getTileSize();
        int textY = frameY + gamePanel.getTileSize()*3;
        drawMultipleLines(text, textX, textY);
        // Back
        text = "Back";
        textY = frameY + gamePanel.getTileSize()*9;
        g2d.drawString(text, textX, textY);
        if(commandNum == 0){
            g2d.drawString(">", textX-25, textY);
            if(gamePanel.getKeyboardHandler().isEnterPressed()){
                subState = 0;
            }
        }
    }
    /**
     * Draw the controls screen.
     * @param frameX
     * @param frameY
     */
    public void optionsControl(int frameX, int frameY){
        String text;
        int textX, textY;
        // Title
        text = "Controls";
        textX = getXforCenteredText(text);
        textY = frameY + gamePanel.getTileSize();
        g2d.drawString(text, textX, textY);
        g2d.setFont(g2d.getFont().deriveFont(24F));
        // Move
        text = "Move";
        textX = frameX + gamePanel.getTileSize();
        textY += gamePanel.getTileSize();
        g2d.drawString(text, textX, textY);
        // Attack
        text = "Attack";
        textY += gamePanel.getTileSize();
        g2d.drawString(text, textX, textY);
/*        // Pause
        text = "Pause";
        textY += gamePanel.getTileSize();
        g2d.drawString(text, textX, textY);*/
        // Options
        text = "Options";
        textY += gamePanel.getTileSize();
        g2d.drawString(text, textX, textY);
        // Map
        if(gamePanel.getPlayer().isMapFound()){
            text = "Open map";
            textY += gamePanel.getTileSize();
            g2d.drawString(text, textX, textY);
        }
        // Diary
        if(gamePanel.getPlayer().isDiaryFound()){
            text = "Open diary";
            textY += gamePanel.getTileSize();
            g2d.drawString(text, textX, textY);
        }

        // Move keys
        text = "WASD";
        textX = frameX + gamePanel.getTileSize()*5;
        textY = frameY + gamePanel.getTileSize()*2;
        g2d.drawString(text, textX, textY);
        // Attack key
        text = "MOUSE";
        textY += gamePanel.getTileSize();
        g2d.drawString(text, textX, textY);
        // Pause
/*      text = "ESC";
        textY += gamePanel.getTileSize();
        g2d.drawString(text, textX, textY);*/
        // Options
        text = "ESC";
        textY += gamePanel.getTileSize();
        g2d.drawString(text, textX, textY);
        // Map key
        if(gamePanel.getPlayer().isMapFound()){
            text = "1";
            textY += gamePanel.getTileSize();
            g2d.drawString(text, textX, textY);
        }
        // Diary key
        if(gamePanel.getPlayer().isDiaryFound()){
            text = "2";
            textY += gamePanel.getTileSize();
            g2d.drawString(text, textX, textY);
        }

        // Back
        text = "Back";
        textX = frameX + gamePanel.getTileSize();
        textY = frameY + gamePanel.getTileSize()*9;
        g2d.drawString(text, textX, textY);
        if(commandNum == 0){
            g2d.drawString(">", textX-25, textY);
            if(gamePanel.getKeyboardHandler().isEnterPressed()){
                subState = 0;
                commandNum = 3;
            }
        }
    }
    /**
     * Draw the end game confirmation screen.
     * @param frameX
     * @param frameY
     */
    public void optionsEndGameConfirmation(int frameX, int frameY){
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(20F));
        String text = "Do you want to \nquit the game and return\n to the title screen?";
        int textX = frameX + gamePanel.getTileSize();
        int textY = frameY + gamePanel.getTileSize()*3;
        drawMultipleLines(text, textX, textY);
        // Yes
        text = "Yes";
        textX = getXforCenteredText(text);
        textY += gamePanel.getTileSize()*3;
        g2d.drawString(text, textX, textY);
        if(commandNum == 0){
            g2d.drawString(">", textX-25, textY);
            if(gamePanel.getKeyboardHandler().isEnterPressed()){
                subState = 0;
                gamePanel.restart();
                gamePanel.getPlayer().setDefaultPositions();
                gamePanel.setGameState(GameState.TITLE_STATE);
                gamePanel.getMusic().stop();
                gamePanel.playMusic(0);
            }
        }
        // No
        text = "No";
        textX = getXforCenteredText(text);
        textY += gamePanel.getTileSize();
        g2d.drawString(text, textX, textY);
        if(commandNum == 1){
            g2d.drawString(">", textX-25, textY);
            if(gamePanel.getKeyboardHandler().isEnterPressed()){
                subState = 0;
                commandNum = 4;
            }
        }
    }
    /**
     * Draw the developer mode screen.
     */
    public void drawDeveloperModeWindow(){
        try{
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
            g2d.drawString("Player X: "+(gamePanel.getPlayer().getWorldX()/gamePanel.getTileSize())+" | Player Y: "+(gamePanel.getPlayer().getWorldY()/gamePanel.getTileSize()), x, y);
            y += lineHeight;
            //g2d.drawString("Ema X: "+(gamePanel.getNpcs()[0].getWorldX()/gamePanel.getTileSize())+ " | Ema Y: "+(gamePanel.getNpcs()[0].getWorldY()/gamePanel.getTileSize()), x, y);
            g2d.drawString("(9) Reload map", x, y);
            y += lineHeight;
            g2d.drawString("(8) Draw time", x, y);
            y += lineHeight;
            g2d.drawString("(7) Draw battle area", x, y);
            y += lineHeight;
            g2d.drawString("(6) Map", x, y);
            y += lineHeight;
            g2d.drawString("(5) Diary", x, y);
            y += lineHeight;
            g2d.drawString("(4) Next map", x, y);
            y += lineHeight;
            g2d.drawString("(3) Choose level", x, y);
            y += lineHeight;
            if(!gamePanel.isCheckColissions()){
                g2d.drawString("Ignoring colission checker", x, y);
                y += lineHeight;
            }
        } catch(Exception e){
            // Do something later
        }
    }
    /**
     * Draw a text with multiple lines.
     * @param text
     * @param textX
     * @param textY
     */
    public void drawMultipleLines(String text, int textX, int textY){
        for(String line : text.split("\n")){
            g2d.drawString(line, textX, textY);
            textY += 40;
        }
    }
    /**
     * Get the X position to draw a text in the center of the screen.
     * @param text
     * @return
     */
    public int getXforCenteredText(String text){
        int length = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        return (gamePanel.getScreenWidth()/2) - (length/2);

    }
    /**
     *  Get the current cutscene code.
     * @return
     */
    public String getCurrentCutsceneCode() {return currentCutsceneCode;}
    /**
     * Set the current cutscene code.
     * @param currentCutsceneCode
     */
    public void setCurrentCutsceneCode(String currentCutsceneCode) {this.currentCutsceneCode = currentCutsceneCode;}
    /**
     * Get the command num.
     * @return
     */
    public int getCommandNum() {return commandNum;}
    /**
     * Set the command num.
     * @param commandNum
     */
    public void setCommandNum(int commandNum) {this.commandNum = commandNum;}
    /**
     * Get the substate.
     * @return
     */
    public int getSubState() {return subState;}
    /**
     * Set the substate.
     * @param subState
     */
    public void setSubState(int subState) {this.subState = subState;}
    /**
     * Get the current interacting NPC.
     * @return
     */
    public Entity getNpc() {return npc;}
    /**
     * Set the current interacting NPC.
     * @param npc
     */
    public void setNpc(Entity npc) {this.npc = npc;}
    /**
     * Get the diary entries.
     * @return
     */
    public String[] getDiaryEntries() {return diaryEntries;}
    /**
     * Set the diary entries.
     * @param diaryEntries
     */
    public void setDiaryEntries(String[] diaryEntries) {this.diaryEntries = diaryEntries;}
    /**
     * Get the current diary page.
     * @return
     */
    public int getDiaryPage() {return diaryPage;}
    /**
     * Set the current diary page.
     * @param diaryPage
     */
    public void setDiaryPage(int diaryPage) {this.diaryPage = diaryPage;}
    /**
     * Check if the mall cinematic has finished.
     * @return
     */
    public boolean isFinishedMallCinematic() {return finishedMallCinematic;}
    /**
     * Set whether the mall cinematic has finished or not.
     * @param finishedMallCinematic
     */
    public void setFinishedMallCinematic(boolean finishedMallCinematic) {this.finishedMallCinematic = finishedMallCinematic;}
    /**
     * Check if the locked level dialogue has to be removed.
     * @return
     */
    public boolean isRemoveLockedDialogue() {return removeLockedDialogue;}
    /**
     * Set if the locked level dialogue has to be removed.
     * @param removeLockedDialogue
     */
    public void setRemoveLockedDialogue(boolean removeLockedDialogue) {this.removeLockedDialogue = removeLockedDialogue;}
    /**
     * Check if the cave cinematic has finished.
     * @return
     */
    public boolean isFinishedCaveCinematic() {return finishedCaveCinematic;}
    /**
     * Set whether the cave cinematic has finished or not.
     * @param finishedCaveCinematic
     */
    public void setFinishedCaveCinematic(boolean finishedCaveCinematic) {this.finishedCaveCinematic = finishedCaveCinematic;}
    /**
     * Check if the final cinematic has finished.
     * @return
     */
    public boolean isFinishedFinalCinematic() {return finishedFinalCinematic;}
    /**
     * Set whether the final cinematic has finished or not.
     * @param finishedFinalCinematic
     */
    public void setFinishedFinalCinematic(boolean finishedFinalCinematic) {this.finishedFinalCinematic = finishedFinalCinematic;}
    /**
     * Get the opacity.
     * @return
     */
    public float getOpacity() {return opacity;}
    /**
     * Set the opacity.
     * @param opacity
     */
    public void setOpacity(float opacity) {this.opacity = opacity;}
}
