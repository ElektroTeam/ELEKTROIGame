package game.mapsfactory;

import game.GamePanel;
import game.mapsfactory.maps.DesertMap;
import game.mapsfactory.maps.Map;
/**
 * The factory to create desert maps.
 * @see MapFactory
 */
public class DesertMapFactory extends MapFactory {
    /**
     * Create the desert map.
     * @return the new desert map.
     */
    @Override
    public Map createMap(GamePanel gamePanel) {
        return new DesertMap(gamePanel);
    }
}
