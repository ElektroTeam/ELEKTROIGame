package game.mapsfactory;

import game.GamePanel;
import game.mapsfactory.maps.Map;
import game.mapsfactory.maps.OrigenMap;

/**
 * The factory to create origen maps.
 * @see MapFactory
 */
public class OrigenMapFactory extends MapFactory {
    /**
     * Create the origen map.
     * @return the new origen map.
     */
    @Override
    public Map createMap(GamePanel gamePanel) {
        return new OrigenMap(gamePanel);
    }
}
