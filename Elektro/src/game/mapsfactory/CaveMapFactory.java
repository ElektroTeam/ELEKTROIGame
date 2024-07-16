package game.mapsfactory;

import game.GamePanel;
import game.mapsfactory.maps.CaveMap;
import game.mapsfactory.maps.Map;
/**
 * The factory to create cave maps.
 * @see MapFactory
 */
public class CaveMapFactory extends MapFactory {
    /**
     * Create the cave map.
     * @return the new cave map.
     */
    @Override
    public Map createMap(GamePanel gamePanel) {
        return new CaveMap(gamePanel);
    }
}
