package utilities;

import game.cutscene.Cutscene;
import game.levelsfactory.levels.Level;

/**
 * Utility class for cutscenes related actions.
 */
public class Cutscenes {
    /**
     * Display specific cutscenes.
     * @param level the level where we get the cutscene arraylist from.
     * @param code the code that identifies the cutscene.
     */
    public static void displayConcreteCutscene(Level level, String code){
        for(Cutscene cutscene : level.getCutscenes()){
            if(cutscene.getCode().equals(code)){
                cutscene.displayCutscene();
            }
        }
    }
}
