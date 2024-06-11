package game.mapsfactory.maps;

import battle.Battle;
import entities.Entity;
import entities.factories.*;
import enums.StateOfBattle;
import game.levelsfactory.levels.Level;

import items.Item;
import items.consumables.HealerItem;
import items.consumables.gameHealings.Bandage;
import items.consumables.gameHealings.Kit;
import items.consumables.healingsFactory.BandageFactory;
import items.consumables.healingsFactory.HealingFactory;
import items.consumables.healingsFactory.KitFactory;
import items.weapons.AttackItem;
import items.weapons.weaponsFactories.ElectricGunFactory;
import items.weapons.weaponsFactories.VoltCannonFactory;
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
public class CaveMap extends Map{
    /**
     * Public constructor.
     */
    public CaveMap() {
        super("cave");
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
        eF = new InfantFactory();
        enemy = eF.createEntity();
        getInfants().add(enemy);
        eF = new SentinelFactory();
        enemy = eF.createEntity();
        getSentinels().add(enemy);
        eF = new BossFactory();
        enemy = eF.createEntity();
        WeaponFactory wF;
        wF = new VoltCannonFactory();
        AttackItem weapon = wF.createWeapon();
        enemy.setWeapon(weapon);
        enemy.setName("Bob");
        setBoss(enemy);
        //System.out.println("Loaded entities.");
    }
    @Override
    public void loadItems(){
        Item kit;
        HealingFactory hF;
        hF = new KitFactory();
        kit = hF.createHealing();
        getItems().add(kit);
        Item bandage;
        hF = new BandageFactory();
        bandage = hF.createHealing();
        getItems().add(bandage); getItems().add(bandage);
        AttackItem wepon;
        WeaponFactory wF;
        wF = new ElectricGunFactory();
        wepon = wF.createWeapon();
        getItems().add(wepon);
        //System.out.println("Loaded items.");
    }
    @Override
    public void loadPlayerSpawn(){
        //System.out.println("Loaded player spawn.");
    }
    @Override
    public void playMap(Level level) {
        boolean obligatory = true;
        boolean consecutive = true;
        AudioLoader.playSound(AudioFile.CAVE);
        BattleExtensions.reset(Optional.of(getBoss()),getSentinels(),getInfants(),getCrystals(),getCrystals());
        int index = 0;
        StateOfBattle stateOfBattle = StateOfBattle.readyAndGo;
        Entity player;
        EntityFactory eF;
        eF = new PlayerFactory();
        player = eF.createEntity();
        Battle battle;
        /* MAP'S LOGIC
        * We display a cutscene of the player entering the cave, and he sees three infants that he must fight first.
        * After fighting the infants, he steps into two crystals that he must fight in order to pass.
        * After fighting the crystals, he gets to the secret lair of Bob and his soldiers.
        * He fights three soldiers and then the final fight begins.
        * Final boss: Bob (this time he must be defeated in order to pass to the next level)
        * Once Bob is defeated, he gets a new weapon (electric bat or a taser gun).
        * Final animation well be displayed: the player saves Ema and Ema starts telling him about what's happened in the last years.
        * Map ends, level ends.
        * */

        Cutscenes.displayConcreteCutscene(level, "cave");
        AudioLoader.stopSound(AudioFile.CAVE);
        battle = new Battle(stateOfBattle,player, getInfants().getFirst());
        AudioLoader.playSound(AudioFile.INFANT_BATTLE);
        battle.gameplay(Optional.of(obligatory),Optional.of(consecutive));
        AudioLoader.stopSound(AudioFile.INFANT_BATTLE);


        Cutscenes.displayConcreteCutscene(level, "cave_infant_win");
        BattleExtensions.overVoltage();
        for (Item i : getItems()){
            if(i instanceof Bandage){
                player.getHealingsItems().add((HealerItem) i);
                break;
            }
        }

        AudioLoader.playSound(AudioFile.CAVE);
        AudioLoader.stopSound(AudioFile.CAVE);
        battle = new Battle(stateOfBattle,player,getCrystals().getFirst());
        AudioLoader.playSound(AudioFile.CRISTAL_BATTLE);
        battle.gameplay(Optional.of(obligatory),Optional.of(consecutive));
        AudioLoader.stopSound(AudioFile.CRISTAL_BATTLE);
        AudioLoader.playSound(AudioFile.CAVE);
        Cutscenes.displayConcreteCutscene(level, "cave_crystal_win");
        BattleExtensions.overVoltage();
        for (Item i : getItems()){
            if(i instanceof Bandage){
                player.getHealingsItems().add((HealerItem) i);
                break;
            }
        }
        Cutscenes.displayConcreteCutscene(level, "cave_soldiers_fight");
        AudioLoader.stopSound(AudioFile.CAVE);
        battle = new Battle(stateOfBattle,player, getSentinels().getFirst());
        AudioLoader.playSound(AudioFile.SOLDIERS_BATTLE);
        battle.gameplay(Optional.of(obligatory),Optional.of(consecutive));
        AudioLoader.stopSound(AudioFile.SOLDIERS_BATTLE);
        for (Item i : getItems()){
            if(i instanceof Kit){
                player.getHealingsItems().add((HealerItem) i);
                break;
            }
        }
        for(Item i : getItems()){
            if (i instanceof AttackItem){
                index = getItems().indexOf(i);
            }
        }
        player.setWeapon((AttackItem) getItems().get(index));

        // Final boss
        battle = new Battle(stateOfBattle,player,getBoss());

        AudioLoader.playSound(AudioFile.ENCOUNTER_SOLDIERS);

        AudioLoader.playSound(AudioFile.FINAL_BATTLE_LEVEL_2);

        BattleExtensions.BossTutorial();
        AudioLoader.stopSound(AudioFile.ENCOUNTER_SOLDIERS);
        AudioLoader.playSound(AudioFile.FINAL_BATTLE_LEVEL_2);
        battle.gameplay(Optional.of(obligatory),Optional.empty());



        AudioLoader.stopSound(AudioFile.FINAL_BATTLE_LEVEL_2);


        if (battle.getState().equals(StateOfBattle.Lose)){
            Cutscenes.displayConcreteCutscene(level, "lost_battle");
            AudioLoader.stopSound(AudioFile.FINAL_BATTLE_LEVEL_2);

            player.setHP(100);
            playMap(level);
        }else{

            AudioLoader.stopSound(AudioFile.FINAL_BATTLE_LEVEL_2);

            AudioLoader.playSound(AudioFile.ENDIG);

            AudioLoader.playSound(AudioFile.ENDIG);

            Cutscenes.displayConcreteCutscene(level, "cave_bob_win");
            Cutscenes.displayConcreteCutscene(level, "cave_final");
            Cutscenes.displayConcreteCutscene(level, "diary");
            this.setNextMap(true);
            AudioLoader.stopSound(AudioFile.ENDIG);

        }
    }
}

