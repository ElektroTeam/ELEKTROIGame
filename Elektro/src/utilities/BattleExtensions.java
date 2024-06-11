package utilities;

import battle.Battle;
import entities.Entity;
import entities.enemies.*;
import entities.player.Player;
import enums.StateOfBattle;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

/**
 * Utility class for battle extensions related actions.
 */
public class BattleExtensions {
    /**
     * Display the tutorial with tips and tricks to win the battle.
     */
    public static void tutorial(){
        System.out.println("Hello and Welcome to your first fight, here some tips:");
        System.out.println("1. Weapon and attack: each weapon have 2 attacks, STANDARD attack (1) and SPECIAL attack (2)\n some weapons just have an increase in the damage in the special attack, other weapons have a more complex form to attack");
        System.out.println("2. Items: in your backpack have HEALING items, this items will help you to keep your character alive"
        + " use them carefully, because are limited");
        System.out.println("3. How to win: you need to DEFEAT THE ENEMY in front of you but they are strong and some Faster than you\n be careful and take the best decisions");
        System.out.println("4. the attack phase: when you decide to attack THERE IS NO TURNING BACK, so decide what to do first during the main phase");
        System.out.println("5. Over voltage: In this game, after you defeat an enemy you got a little voltage and you recover a percent of your life");
        System.out.println("Enjoy!");
        Prompt.promptEnterToContinue();
    }
    /**
     * Add an extra amount of live.
     * This will mostly be used to add extra life to the player when they win a fight.

     */
    public static void overVoltage(){
        if (Player.getInstance().getHP() <= 85) {
            Player.getInstance().setHP(Player.getInstance().getHP() + 15);
        }else {
            Player.getInstance().setHP(Player.getInstance().getHP() + (100-Player.getInstance().getHP()));
        }
    }


    /**
     * Show a little tutorial of the gameplay against a Boss
     */
    public static void BossTutorial(){
        System.out.println("Hello again!");
        System.out.println("You are going to fight your first Boss, here some tips:");
        System.out.println("Face your destiny: try to escape only will give you troubles, don´t try!");
        System.out.println("Manage you HP: Bosses has more life and damage than a normal monster, be careful.");
        System.out.println("Good luck!");
        Prompt.promptEnterToContinue();
    }

    /**
     *
     * @param boss in case the map have a boss
     * @param enemies the enemies on the map
     * set the HP of the enemies to the original stat
     */
    public static void reset(Optional<Entity> boss, ArrayList<Entity>... enemies){
        for (ArrayList<Entity> e : enemies){
            for (Entity t : e){
                if (t instanceof Crystal){
                    t.setHP(70);
                } else if (t instanceof Infant){
                    t.setHP(80);
                } else if (t instanceof Sentinel) {
                    t.setHP(100);
                } else if (t instanceof Wolf) {
                    t.setHP(120);
                }
            }
        }
        if (boss.isPresent()){
            if(boss.get() instanceof Boss){
                boss.get().setHP(150);
            }
        }
    }

    /**
     * @param enemy with which enemy are you fighting
     * set the HP of the enemy to the original stat depends on which enemy is
     */
    public static void consecutiveBattlesReset(Entity enemy){
        if (enemy instanceof Crystal){
            enemy.setHP(70);
        } else if (enemy instanceof Infant){
            enemy.setHP(80);
        } else if (enemy instanceof Sentinel) {
            enemy.setHP(100);
        } else if (enemy instanceof Wolf) {
            enemy.setHP(120);
        }
    }

    /**
     *
     * @param player the player of the level
     * remove the items
     *
     */
    public static void itemReset(Entity player){
        try {
            player.getHealingsItems().removeIf(Objects::nonNull);
        }catch (Exception e){

        }
    }
}
