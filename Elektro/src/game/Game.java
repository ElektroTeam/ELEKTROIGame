package game;

import game.cutscene.Cutscene;
import game.levelsfactory.ExplorationLevelFactory;
import game.levelsfactory.IntroductionLevelFactory;
import game.levelsfactory.LevelFactory;
import game.levelsfactory.levels.Level;
import game.mapsfactory.*;
import game.mapsfactory.maps.Map;
import utilities.Prompt;

import java.util.*;

/**
 * Public class for actions related to the Game itself.
 */
public class Game {
    private List<Level> levelList;
    private Prompt prompt = new Prompt();
    /**
     * Load the game.
     * This method loads the levels and asks the user which level they want to play.
     * The user can only play the level if it's unlocked.
     */
    public void loadGame(){
        loadLevels();
        while(true){
            Level level = chooseLevel();
            if(level == null){
                return;
            }
            level.setNextLevel(false);
            level.loadMaps();
            level.play();
            level.setNextLevel(true);
            int currentIndex = levelList.indexOf(level);
            if (currentIndex < levelList.size() - 1) {
                Level nextLevel = levelList.get(currentIndex + 1);
                nextLevel.setUnlocked(true);
                System.out.println("Next level unlocked: " + nextLevel.getTitle());
            } else {
                System.out.println("You've finished the last level, congratulations. New levels will come in the future.");
            }
        }
    }
    /**
     * Load all the levels.
     * Create a new list of levels, then create the level and map factory to create all the levels and their respective maps.
     */
    public void loadLevels(){
        System.out.println("Loading levels...");
        // Create level list
        levelList = new LinkedList<>();
        LevelFactory levelFactory = null;
        ArrayList<Map> maps = new ArrayList<>();
        // Create first level
        levelFactory = new IntroductionLevelFactory();
        Level level1 = levelFactory.createLevel();
        maps.add(new HouseMapFactory().createMap());
        maps.add(new DesertMapFactory().createMap());
        maps.add(new MallMapFactory().createMap());
        level1.setMaps(maps);

        maps = new ArrayList<>();
        levelFactory = new ExplorationLevelFactory();
        Level level2 = levelFactory.createLevel();
        maps.add(new ParkingLotMapFactory().createMap());
        maps.add(new ForestMapFactory().createMap());
        maps.add(new CaveMapFactory().createMap());
        level2.setMaps(maps);

        levelList.add(level1);
        levelList.add(level2);
    }

    /**
     * Choose a level to play.
     * Display the list of levels available and then ask the user which level they want to play.
     * The user can also cancel the operation and go back to the main menu.
     * @return the level the user asked to play, or null if the user decides to cancel the operation
     */
    public Level chooseLevel(){
        while(true){
            Level level = null;
            System.out.println("#\t\tLevel title");
            int iterator = 1;
            for(Level currentLevel : levelList){
                System.out.println((iterator++) + "\t\t" + currentLevel.getTitle());
            }
            System.out.println("0\t\tBack to the menu");
            System.out.println("Please choose a level to play: ");
            int option = prompt.requestInt();
            if(option==0){
                return null;
            } else {
                try{
                    level = levelList.get(option-1);
                    if(level.isUnlocked()){
                        return level;
                    } else {
                        System.out.println("You cannot play that level because you need to unlock the previous one first: "+ (levelList.get(levelList.indexOf(level)-1).getTitle()));
                        Prompt.promptEnterToContinue();
                    }
                } catch (IndexOutOfBoundsException e){
                    System.out.println("You've entered a level that does not exist, please try again.");
                    Prompt.promptEnterToContinue();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}