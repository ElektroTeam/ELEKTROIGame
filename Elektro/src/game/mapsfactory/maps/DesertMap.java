package game.mapsfactory.maps;

import battle.Battle;
import enums.StateOfBattle;
import items.Item;
import items.weapons.AttackItem;
import items.weapons.weaponsFactories.BatFactory;
import items.weapons.weaponsFactories.WeaponFactory;
import entities.factories.CrystalFactory;
import entities.factories.EntityFactory;
import entities.factories.PlayerFactory;
import game.levelsfactory.levels.Level;
import music.manager.AudioLoader;
import music.enums.AudioFile;
import utilities.BattleExtensions;

import utilities.Cutscenes;


import entities.*;

import java.util.Optional;
/**
 * Public class map.
 * @see Map
 */
public class DesertMap extends Map {
    private Entity clone;
    /**
     * Public constructor.
     */
    public DesertMap() {
        super("dessert");
    }
    /**
     * Load the map.
     * This method loads the entities, items, and player spawn.
     */
    @Override
    public void loadMap(){
        super.setNextMap(false);
        loadEntities();
        loadItems();
        loadPlayerSpawn();
        //System.out.println("Loaded map: "+super.getMapName());
    }
    @Override
    public void loadEntities(){
        Entity enemy;
        EntityFactory eF;
        eF = new CrystalFactory();
        enemy = eF.createEntity();
        getCrystals().add(enemy);
        //System.out.println("Loaded entities.");
    }
    @Override
    public void loadItems(){
        AttackItem wepon;
        WeaponFactory wF;
        wF = new BatFactory();
        wepon = wF.createWeapon();
        getItems().add(wepon);
        //System.out.println("Loaded items.");
    }
    @Override
    public void loadPlayerSpawn(){
        //System.out.println("Loaded player spawn.");
    }
    @Override
    public void playMap(Level level){
        boolean consecutive = true;
        BattleExtensions.reset(Optional.empty(),getCrystals());

        //Esto esta bien aqui
        AudioLoader.playSound(AudioFile.DESERT);
        int index = 0;
        StateOfBattle stateOfBattle = StateOfBattle.readyAndGo;
        Entity player;
        EntityFactory eF;
        eF = new PlayerFactory();
        player = eF.createEntity();
        //In case wants to replay the level
        BattleExtensions.itemReset(player);
        //for to search the weapon
        for(Item i : getItems()){
            if (i instanceof AttackItem){
                index = getItems().indexOf(i);
            }
        }
        //cast
        AttackItem weapon = (AttackItem) getItems().get(index);
        player.setWeapon(weapon);
        Battle battle = new Battle(stateOfBattle,player, getCrystals().getFirst());
        /* MAP'S LOGIC
        We display a little cutscene of the player living the house, neighborhood and entering the desert.
        The player shall walk forward, there will be a crystal waiting in the middle of the road.
        After getting to a certain distance, the player will enter in fight mode, will fight the crystal (should be given tips to fight and win).
        After winning, the player shall continue walking (then load (parking lot?) mall map).
        * */

        Cutscenes.displayConcreteCutscene(level, "desert");
        BattleExtensions.tutorial();
        AudioLoader.stopSound(AudioFile.DESERT);
        AudioLoader.playSound(AudioFile.CRISTAL_BATTLE);
        battle.gameplay(Optional.empty(),Optional.of(consecutive));
        AudioLoader.stopSound(AudioFile.CRISTAL_BATTLE);
        AudioLoader.playSound(AudioFile.DESERT);
        player.setHP(100);
        if (battle.getState().equals(StateOfBattle.Win)){
            Cutscenes.displayConcreteCutscene(level, "desert_win");
            BattleExtensions.overVoltage();
        }else if (battle.getState().equals(StateOfBattle.Ran)){
            Cutscenes.displayConcreteCutscene(level, "desert_escape");
        }else {
            Cutscenes.displayConcreteCutscene(level, "lost_battle");
            player.setHP(100);
            playMap(level);
        }
        this.setNextMap(true);
        AudioLoader.stopSound(AudioFile.DESERT);

    }


}
