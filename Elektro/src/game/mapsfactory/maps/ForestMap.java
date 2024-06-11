package game.mapsfactory.maps;

import battle.Battle;
import entities.Entity;
import entities.factories.EntityFactory;
import entities.factories.PlayerFactory;
import entities.factories.WolfFactory;
import enums.StateOfBattle;
import game.levelsfactory.levels.Level;

import items.Item;
import items.consumables.HealerItem;
import items.consumables.healingsFactory.FoodFactory;
import items.consumables.healingsFactory.HealingFactory;
import music.manager.AudioLoader;
import music.enums.AudioFile;
import utilities.BattleExtensions;

import utilities.Cutscenes;

import java.util.Optional;
/**
 * Public class map.
 * @see Map
 */
public class ForestMap extends Map {
    /**
     * Public constructor.
     */
    public ForestMap() {
        super("forest");
    }
    /**
     * Load the map.
     * This method loads the entities, items, and player spawn.
     */
    @Override
    public void loadMap() {
        super.setNextMap(false);
        loadEntities();
        loadItems();
        loadPlayerSpawn();
        //System.out.println("Loaded map: " + super.getMapName());
    }
    @Override
    public void loadEntities() {
        Entity wolf;
        EntityFactory eF;
        eF = new WolfFactory();
        wolf = eF.createEntity();
        getWolves().add(wolf);
        //System.out.println("Loaded entities.");
    }
    @Override
    public void loadItems() {
        Item food;
        HealingFactory hF;
        hF = new FoodFactory();
        food = hF.createHealing();
        getItems().add(food); getItems().add(food); getItems().add(food);
        //System.out.println("Loaded items.");
    }
    @Override
    public void loadPlayerSpawn(){
        //System.out.println("Loaded player spawn.");
    }
    @Override
    public void playMap(Level level) {
        BattleExtensions.reset(Optional.empty(),getWolves());
        AudioLoader.playSound(AudioFile.FOREST);
        StateOfBattle stateOfBattle = StateOfBattle.readyAndGo;
        Entity player;
        EntityFactory eF;
        eF = new PlayerFactory();
        player = eF.createEntity();
        for (Item i : getItems()){
            if(i instanceof HealerItem){
                player.getHealingsItems().add((HealerItem) i);
            }
        }
        Battle battle = new Battle(stateOfBattle,player,getWolves().getFirst());

        /* MAP'S LOGIC
        * The player will be able to walk around the forest, get some berries and fight wolves.
        * After defeating the last wolf, he gets a wolf claw as a trophy.
        * After a long trip through the forest, he will find the entrance to the cave, where Bob and his allies are hidden, with Ema kidnapped.
        * Cutscenes could be displayed and then load the next map.
        * */
        Cutscenes.displayConcreteCutscene(level, "forest");
        AudioLoader.stopSound(AudioFile.FOREST);
        AudioLoader.playSound(AudioFile.WOLF_BATTLE);
        battle.gameplay(Optional.empty(),Optional.empty());
        AudioLoader.stopSound(AudioFile.WOLF_BATTLE);
        AudioLoader.playSound(AudioFile.FOREST);

        if (battle.getState().equals(StateOfBattle.Win)){
            Cutscenes.displayConcreteCutscene(level, "forest_win");
            BattleExtensions.overVoltage();
        }else if (battle.getState().equals(StateOfBattle.Ran)){
            Cutscenes.displayConcreteCutscene(level, "forest_escape");
        }else {
            Cutscenes.displayConcreteCutscene(level, "lost_battle");
            player.setHP(100);
            playMap(level);
        }
        this.setNextMap(true);
        AudioLoader.stopSound(AudioFile.FOREST);

    }
}