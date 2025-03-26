package battle;

import entities.Entity;
import entities.enemies.Boss;
import entities.enemies.Crystal;
import entities.enemies.Infant;
import entities.enemies.Sentinel;
import game.mapsfactory.maps.CaveMap;
import game.mapsfactory.maps.Map;
import utilities.BattleExtensions;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Verify class for battle GUI build and actions
 */
public class MapBattle {
    /**
     * Search for the battle GUI background depend on the Map
     * @param map current map the player is playing
     * @param enemy which enemy is in the battle
     * @return background of the map related with the enemy
     */
    public static String verifyEnemyBackground(Map map, Entity enemy){
        String mapBackground = "";
        if (map instanceof CaveMap){
            if (enemy instanceof Crystal) {
                mapBackground = map.getMultipleBattleBackgrounds().getFirst();
            }else if (enemy instanceof Sentinel) {
                mapBackground = map.getMultipleBattleBackgrounds().get(1);
            }else if (enemy instanceof Infant) {
                mapBackground = map.getMultipleBattleBackgrounds().get(2);
            } else if(enemy instanceof Boss){
                mapBackground = map.getMultipleBattleBackgrounds().get(3);
            }

        }else {
            mapBackground = map.getBattleBackground();
        }
        return mapBackground;
    }

    /**
     * Decide which enemy of the map are going to the battle
     * @param map current map the player is playing
     * @return the enemy
     */
    public static Entity decideEnemy(Map map){
        Entity enemy = null;
        if (map instanceof CaveMap) {
            BattleExtensions.reset(Optional.of(map.getBoss()),null,map.getMultipleEnemies());
            float random = (int) (Math.random() * 3 + 1);
            switch ((int) random){
                case 1:
                    for (Entity e: map.getMultipleEnemies()) {
                        if (e instanceof Crystal) {
                            enemy = e;
                        }
                    }
                    break;
                case 2:
                    for (Entity e: map.getMultipleEnemies()) {
                        if (e instanceof Infant) {
                            enemy = e;
                        }
                    }break;
                case 3:
                    for (Entity e: map.getMultipleEnemies()) {
                        if (e instanceof Sentinel) {
                            enemy = e;
                        }
                    }
                    break;
            }
        }else {
            enemy = map.getEnemy();
        }
        return enemy;
    }
}
