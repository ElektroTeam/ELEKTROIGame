package game.mapsfactory;

import game.mapsfactory.maps.HouseMap;
import game.mapsfactory.maps.Map;
/**
 * The factory to create house maps.
 * @see MapFactory
 */
public class HouseMapFactory extends MapFactory {
    @Override
    public Map createMap() {
        return new HouseMap();
    }
}
