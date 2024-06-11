package battle;

import items.consumables.HealerItem;
import items.consumables.gameHealings.Bandage;
import items.consumables.gameHealings.Food;
import items.consumables.gameHealings.Kit;
import entities.Entity;
import enums.StateOfBattle;
import utilities.BattleExtensions;
import utilities.Prompt;

import java.util.Optional;
import java.util.Scanner;
/**
 * This is the battle system (gameplay).
 */
public class Battle implements battle.AttackTurn {
    private StateOfBattle state;
    private Entity player;
    private Entity enemy;
    /**
     * public constructor with parameters.
     * @param state state of the battle
     * @param player who is playing.
     * @param enemy who we fight against
     */
    public Battle(StateOfBattle state, Entity player, Entity enemy) {
        this.state = state;
        this.player = player;
        this.enemy = enemy;
    }

    /**
     * Gets the state of the battle.
     * @return the state of the battle.
     */
    public StateOfBattle getState() {
        return state;
    }
    /**
     * Sets the state of the battle.
     * @param state the state of the battle to set.
     */
    public void setState(StateOfBattle state) {
        this.state = state;
    }
    /**
     * Gets the player entity.
     * @return the player entity.
     */
    public Entity getPlayer() {
        return player;
    }
    /**
     * Sets the player entity.
     * @param player the player entity to set.
     */
    public void setPlayer(Entity player) {
        this.player = player;
    }
    /**
     * Gets the enemy entity.
     * @return the enemy entity.
     */
    public Entity getEnemy() {
        return enemy;
    }
    /**
     * Sets the enemy entity.
     * @param enemy the enemy entity to set.
     */
    public void setEnemy(Entity enemy) {
        this.enemy = enemy;
    }
    /**
     * Starts the battle with 3 possible options: fight, heal and try to scape
     * @param obligatory verify if the battle is obligatory
     * @param consecutives verify if you need to retry this battle
     */
    @Override
    public void gameplay(Optional<Boolean> obligatory, Optional<Boolean> consecutives) {
        //Scanner sc = new Scanner(System.in);
        while (state.equals(StateOfBattle.readyAndGo)) {
            System.out.println("---------------------------------");
            System.out.println("\t\t\tFIGHTING");
            System.out.println("---------------------------------");
            System.out.println("Player HP: " + player.getHP());
            System.out.println(enemy.getName() + " HP: " + enemy.getHP());
            System.out.println("What will you do? \n (1)Attack \n (2)Item \n (3)Escape");
            switch (Prompt.requestInt()){
                case 1:
                    fight();
                    state = verifyCondition();
                    if (state.equals(StateOfBattle.Lose) && consecutives.isPresent()){
                        state = StateOfBattle.readyAndGo;
                        player.setHP(100);
                        BattleExtensions.consecutiveBattlesReset(enemy);
                        System.out.println("Try it again");
                        gameplay(obligatory,consecutives);
                    }
                    break;
                case 2:
                    heal();
                    break;
                case 3:
                    if (obligatory.isPresent() && obligatory.get()){
                        System.out.println("You can´t escape from this battle.");
                    }else {
                        state = ranAttempt();
                    }
                    break;
                default:
                    System.out.println("Please enter a valid option.");
                    break;
            }
        }
    }
    /**
     * A random number between 1-10 decide if player escape or not.
     * if player don't escape the enemy attacks.
     * @return Ran if escape otherwise the player get a hit and go to a validation.
     */
    @Override
    public StateOfBattle ranAttempt() {
        float random = (int) (Math.random()*10+1);
        if(random >8){
            System.out.println("Got away safely");
            return StateOfBattle.Ran;
        }else{
            System.out.println("You got stuck and didn't escape");
            enemyAttack();
            return verifyCondition();
        }

    }
    /**
     * Compare the values of the speed of each and who have the higher speed attack first.
     */
    @Override
    public void fight() {
        if (enemy.getSpeed().getValue() > player.getSpeed().getValue()){
            enemyAttack();
            if(player.getHP() > 0) {
                playerAttack();
            }
        }else{
            playerAttack();
            if(enemy.getHP() > 0) {
                enemyAttack();
            }
        }
    }
    /**
     * Set damage in relation to the attack of the weapon.
     * Every attack has an accuracy and if damage is 0 player miss.
     */
    @Override
    public void playerAttack() {
        boolean flag = true;
        player.getWeapon().useItem();
        int attackDamage;
        do {
            switch (Prompt.requestInt()) {
                case 1:
                    attackDamage = player.getWeapon().lowAttack();
                    if (attackDamage == 0) {
                        System.out.println("You missed the attack");
                    } else {
                        enemy.setHP((enemy.getHP() - attackDamage));
                        System.out.println("Hit");
                    }
                    flag = false;
                    break;
                case 2:
                    attackDamage = player.getWeapon().specialAttack();
                    if (attackDamage == 0) {
                        System.out.println("You missed the attack");
                    } else {
                        enemy.setHP((enemy.getHP() - attackDamage));
                        System.out.println("Special hit!");
                    }
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }while (flag);
    }
    /**
     * The attack is random between 1 or 2 (normal or special).
     * Set damage in relation to the attack of the weapon.
     * Every attack has an accuracy and if damage is 0 player miss.
     */
    @Override
    public void enemyAttack() {
        int attackDamage;
        int random = (int) (Math.random() * 2 + 1);
        switch (random) {
            case 1:
                attackDamage = enemy.getWeapon().lowAttack();
                if (attackDamage == 0) {
                    System.out.println("Enemy missed the attack");
                } else {
                    System.out.println("Enemy has attack");
                    player.setHP((player.getHP() - attackDamage));
                }
                break;
            case 2:
                attackDamage = enemy.getWeapon().specialAttack();
                if (attackDamage == 0) {
                    System.out.println("Enemy missed the attack");
                } else {
                    player.setHP((player.getHP() - attackDamage));
                    System.out.println("Enemy has attack");
                    System.out.println("Special hit!");
                }
                break;
        }
    }
    /**
     * Search in the collection if an object is an instance of Food.
     * If that is the case take one item and set the heal percent (how much HP the player recovers).
     * In case the HP of the player wants to recover is lower than the heal percent.
     * Set the healing stat of the item with a math calculus.
     * At the end remove the item from the collection (has already used it).
     */
    @Override
    public void foodHealing() {
        int indexItem = -1;
        int healPercent = 0;
        if(player.getHP() <= 90 ) {
            for (HealerItem h : player.getHealingsItems()) {
                if (h instanceof Food && (!player.getHealingsItems().isEmpty())) {
                    indexItem = player.getHealingsItems().indexOf(h);
                    healPercent = h.getHealing();
                    break;
                }
            }
            player.getHealingsItems().remove(indexItem);
            player.setHP(player.getHP() + healPercent);
            System.out.println("You recovered "+ healPercent +" HP");
        } else if (player.getHP() == 100) {
            System.out.println("HP is full.");
        } else {
            for (HealerItem h : player.getHealingsItems()) {
                if (h instanceof Food) {
                    indexItem = player.getHealingsItems().indexOf(h);
                }
            }
            player.getHealingsItems().get(indexItem).setHealing(player.getHealingsItems().get(indexItem).getHealing()-overHealth(player.getHealingsItems().get(indexItem)));
            healPercent = player.getHealingsItems().get(indexItem).getHealing();
            player.setHP(player.getHP() + healPercent);
            player.getHealingsItems().remove(indexItem);
            System.out.println("You recovered "+ healPercent +" HP");
        }
    }
    /**
     * Search in the collection if an object is an instance of Bandage.
     * If that is the case take one item and set the heal percent (how much HP the player recovers).
     * In case the HP of the player wants to recover is lower than the heal percent.
     * Set the healing stat of the item with a math calculus.
     * At the end remove the item from the collection (has already used it).
     */
    @Override
    public void bandageHealing() {
        int indexItem = -1;
        int healPercent = 0;
        if(player.getHP()<=75) {
            for (HealerItem h : player.getHealingsItems()) {
                if (h instanceof Bandage && (!player.getHealingsItems().isEmpty())) {
                    indexItem = player.getHealingsItems().indexOf(h);
                    healPercent = h.getHealing();
                    break;
                }
            }
            player.getHealingsItems().remove(indexItem);
            player.setHP(player.getHP() + healPercent);
            System.out.println("You recovered "+ healPercent +" HP");
        } else if (player.getHP() == 100) {
            System.out.println("HP is full");
        } else{
            for (HealerItem h : player.getHealingsItems()) {
                if (h instanceof Bandage) {
                    indexItem = player.getHealingsItems().indexOf(h);
                }
            }
            player.getHealingsItems().get(indexItem).setHealing(player.getHealingsItems().get(indexItem).getHealing()-overHealth(player.getHealingsItems().get(indexItem)));
            healPercent = player.getHealingsItems().get(indexItem).getHealing();
            player.getHealingsItems().remove(indexItem);
            player.setHP(player.getHP() + healPercent);
            System.out.println("You recovered "+ healPercent +" HP");
        }
    }
    /**
     * Search in the collection if an object is an instance of Kit.
     * If that is the case take one item and set the heal percent (how much HP the player recovers).
     * In case the HP of the player wants to recover is lower than the heal percent.
     * Set the healing stat of the item with a math calculus.
     * At the end remove the item from the collection (has already used it).
     */
    @Override
    public void kitHealing() {
        int indexItem = -1;
        int healPercent = 0;
        if(player.getHP() <= 50) {
            for (HealerItem h : player.getHealingsItems()) {
                if (h instanceof Kit && (!player.getHealingsItems().isEmpty())){
                    indexItem = player.getHealingsItems().indexOf(h);
                    healPercent = h.getHealing();
                    break;
                }
            }
            player.getHealingsItems().remove(indexItem);
            player.setHP(player.getHP() + healPercent);
            System.out.println("You recovered "+ healPercent +" HP");
        } else if (player.getHP() == 100) {
            System.out.println("The HP is full");
        }else{
            for (HealerItem h : player.getHealingsItems()) {
                if (h instanceof Kit) {
                    indexItem = player.getHealingsItems().indexOf(h);
                }
            }
            player.getHealingsItems().get(indexItem).setHealing(player.getHealingsItems().get(indexItem).getHealing()-overHealth(player.getHealingsItems().get(indexItem)));
            healPercent = player.getHealingsItems().get(indexItem).getHealing();
            player.getHealingsItems().remove(indexItem);
            player.setHP(player.getHP() + healPercent);
            System.out.println("You recovered "+ healPercent +" HP");
        }
    }
    /**
     * Verify the player and the enemy HP.
     * @return the StateOfBattle Win in case the enemy HP is equals or below 0,
     * return StateOfBattle Lose in case the player HP is equals or below 0,
     * if none of the previous conditions applies, just continues with the battle.
     */
    private StateOfBattle verifyCondition(){
        if(enemy.getHP() <= 0){
            System.out.println("You Win");
            return StateOfBattle.Win;
        }else if (player.getHP() <= 0){
            System.out.println("You fainted");
            return StateOfBattle.Lose;
        }else {
            return StateOfBattle.readyAndGo;
        }
    }
    /**
     * Show how many healing items has in the inventory (collection).
     * The player decide which item wants to use.
     * But if the player don't have any healing items throw an exception.
     */
    private void heal(){
        boolean flag = true;
        //Scanner sc = new Scanner(System.in);
        int cantFood = cantFood();
        int cantKit = cantKit();
        int cantBandage = cantBandage();
        do {
            try {
                System.out.println("Which item do you want to use?");
                System.out.println("1. Food: " + cantFood);
                System.out.println("2. Bandage: " + cantBandage);
                System.out.println("3. Med kit: " + cantKit);
                System.out.println("0. Back");
                switch (Prompt.requestInt()) {
                    case 1:
                        foodHealing();
                        flag = false;
                        break;
                    case 2:
                        bandageHealing();
                        flag = false;
                        break;
                    case 3:
                        kitHealing();
                        flag = false;
                        break;
                    case 0:
                        flag = false;
                        break;
                    default:
                        System.out.println("Invalid option!");
                        break;
                }
            }catch (Exception e){
                System.out.println("You don't have this healing item.");
            }
        }while (flag);
    }
    /**
     * Count how many Food items are in the inventory of the player.
     * @return Quantity of food items.
     */
    private int cantFood(){
        int cantFood = 0;
        for(HealerItem h : player.getHealingsItems()){
            if(h instanceof Food) {
                cantFood++;
            }
        }
        return cantFood;
    }
    /**
     * Count how many kit items are in the inventory of the player.
     * @return Quantity of kit items.
     */
    private int cantKit(){
        int cantKit = 0;
        for(HealerItem h : player.getHealingsItems()){
            if(h instanceof Kit) {
                cantKit++;
            }
        }
        return cantKit;
    }
    /**
     * Count how many bandage items are in the inventory of the player.
     * @return Quantity of bandage items.
     */
    private int cantBandage(){
        int cantBandage= 0;
        for(HealerItem h : player.getHealingsItems()){
            if(h instanceof Bandage) {
                cantBandage++;
            }
        }
        return cantBandage;
    }
    /**
     * Found how much HP wants to recover.
     * @param healerItem is which item want to modify the healing.
     * @return Quantity of healing to use.
     */
    private int overHealth(HealerItem healerItem){
        int dif = player.getHP()-100;
        int underPoints = healerItem.getHealing()+dif;
        return underPoints;
    }

}

