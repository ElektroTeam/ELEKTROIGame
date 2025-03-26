package game.mapsfactory;

import game.GamePanel;
import game.mapsfactory.maps.ForestMap;
import game.mapsfactory.maps.Map;
/**
 * The factory to create forest maps.
 * @see MapFactory
 */
public class ForestMapFactory extends MapFactory {
    /**
     * Create the forest map.
     * @return the new forest map.
     */
    @Override
    public Map createMap(GamePanel gamePanel) {
        return new ForestMap(gamePanel);
    }
}
