package game.levelsfactory.levels;

import java.util.ArrayList;

/**
 * Public level which extends from Level abstract class.
 * This is the first level of the game.
 * @see Level
 */
public class ExplorationLevel extends Level {
    /**
     * Public constructor.
     */
    public ExplorationLevel() {
        super(new ArrayList<>(), "Exploration", new ArrayList<>(), null, false, null);
    }
}
