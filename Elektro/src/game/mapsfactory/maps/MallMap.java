package game.mapsfactory.maps;


import battle.Battle;
import entities.Entity;
import entities.factories.EntityFactory;
import entities.factories.PlayerFactory;
import entities.factories.SentinelFactory;
import enums.StateOfBattle;
import game.levelsfactory.levels.Level;
import items.Item;
import items.consumables.HealerItem;
import items.consumables.healingsFactory.BandageFactory;
import items.consumables.healingsFactory.HealingFactory;
import items.consumables.healingsFactory.KitFactory;
import items.weapons.AttackItem;
import items.weapons.weaponsFactories.ElectricBatFactory;
import items.weapons.weaponsFactories.WeaponFactory;
import music.manager.AudioLoader;
import music.enums.AudioFile;
import utilities.BattleExtensions;

import utilities.Cutscenes;

import java.util.Optional;
/**
 * Public class map.
 * @see Map
 */
public class MallMap extends Map {
    /**
     * Public constructor.
     */
    public MallMap() {
        super("mall");
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
        eF = new SentinelFactory();
        enemy = eF.createEntity();
        getSentinels().add(enemy);
        //System.out.println("Loaded entities.");
    }
    @Override
    public void loadItems(){
        Item kit;
        Item bandage;
        HealingFactory hF;
        hF = new KitFactory();
        kit = hF.createHealing();
        getItems().add(kit);
        hF = new BandageFactory();
        bandage = hF.createHealing();
        getItems().add(bandage); getItems().add(bandage); getItems().add(bandage);
        AttackItem wepon;
        WeaponFactory wF;
        wF = new ElectricBatFactory();
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
        boolean obligatory = true;
        BattleExtensions.reset(Optional.empty(),getSentinels());
        AudioLoader.playSound(AudioFile.MALL);
        int index = 0;
        StateOfBattle stateOfBattle = StateOfBattle.readyAndGo;
        Entity player;
        EntityFactory eF;
        eF = new PlayerFactory();
        player = eF.createEntity();
        Battle battle = new Battle(stateOfBattle, player, getSentinels().getFirst());
        for (Item i : getItems()){
            if(i instanceof HealerItem){
                player.getHealingsItems().add((HealerItem) i);
            }
        }

        for(Item i : getItems()){
            if (i instanceof AttackItem){
                index = getItems().indexOf(i);
            }
        }
        /* MAP'S LOGIC
        * We display a little cutscene of the player entering through the backdoor of the mall.
        * The player will appear in the warehouse of the store "Siars" where he will find hidden supplies (healing items for later).
        * Can walk around the mall, and will find stores like CacaoCity, WcWonalds, BucksStar, etc.
        * Easter egg: phrases in the wall like "be more abstract!"
        * None of the other stores are accessible.
        * He will find Ema exiting one of the stores (cutscene).
        * While they are talking, different Elektro soldiers will appear behind them.
        * The fight begins (fight one or two Elektro soldiers).
        * Final boss: Bob (We need to decide if the chances of winning are 50/50 or 0/100)
        *   Case 1: he wins, but while he is celebrating, Ema gets kidnapped (cutscene).
        *   Case 2: he loses, and while he is in pain on the ground, Ema gets kidnapped (cutscene).
        * Map ends, level ends.
        * */

        Cutscenes.displayConcreteCutscene(level, "mall_first");
        AudioLoader.stopSound(AudioFile.MALL);
        AudioLoader.playSound(AudioFile.SOLDIERS_BATTLE);
        battle.gameplay(Optional.of(obligatory),Optional.empty());

        if (battle.getState().equals(StateOfBattle.Win)) {
            Cutscenes.displayConcreteCutscene(level, "mall_final_win");
            BattleExtensions.overVoltage();
        }else {
            AudioLoader.stopSound(AudioFile.SOLDIERS_BATTLE);
            Cutscenes.displayConcreteCutscene(level, "mall_final_lose");
        }

        player.setWeapon((AttackItem) getItems().get(index));
        player.setHP(100);
        AudioLoader.stopSound(AudioFile.SOLDIERS_BATTLE);
        AudioLoader.playSound(AudioFile.MALL);
        Cutscenes.displayConcreteCutscene(level, "mall_final");
        this.setNextMap(true);
        AudioLoader.stopSound(AudioFile.MALL);

    }
}
