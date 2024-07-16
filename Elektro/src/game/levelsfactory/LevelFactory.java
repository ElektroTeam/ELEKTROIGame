package game.levelsfactory;

import game.GamePanel;
import game.levelsfactory.levels.Level;

/**
 * Abstract class for factory of levels.
 */
public abstract class LevelFactory {
    /**
     * Create a level.
     * @return the created level.
     */
    public abstract Level createLevel(GamePanel gamePanel);
}
