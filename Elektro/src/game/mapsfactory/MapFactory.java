package game.mapsfactory;

import game.GamePanel;
import game.mapsfactory.maps.Map;

/**
 * Abstract class to create maps.
 */
public abstract class MapFactory {
    /**
     * Create the map.
     * @return the new map.
     */
    public abstract Map createMap(GamePanel gamePanel);
}
