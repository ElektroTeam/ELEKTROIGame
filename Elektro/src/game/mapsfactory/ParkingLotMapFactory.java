package game.mapsfactory;

import game.GamePanel;
import game.mapsfactory.maps.Map;
import game.mapsfactory.maps.ParkingLotMap;
/**
 * The factory to create parking lot maps.
 * @see MapFactory
 */
public class ParkingLotMapFactory extends MapFactory {
    /**
     * Create the parking lot map.
     * @return the new parking lot map.
     */
    @Override
    public Map createMap(GamePanel gamePanel) {
        return new ParkingLotMap(gamePanel);
    }
}
