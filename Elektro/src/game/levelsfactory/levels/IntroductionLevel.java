package game.levelsfactory.levels;

import java.util.ArrayList;

/**
 * Public level which extends from Level abstract class.
 * This is the first level of the game.
 * @see Level
 */
public class IntroductionLevel extends Level{
    /**
     * Public constructor.
     */
    public IntroductionLevel() {
        super(new ArrayList<>(), "Introduction", new ArrayList<>(), null, true, null);
    }

}
