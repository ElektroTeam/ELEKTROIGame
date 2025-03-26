package game.levelsfactory;

import game.GamePanel;
import game.cutscene.Cutscene;
import game.levelsfactory.levels.ExplorationLevel;
import game.levelsfactory.levels.Level;

import java.util.ArrayList;

/**
 * The factory of the second level.
 * @see LevelFactory
 */
public class ExplorationLevelFactory extends LevelFactory {
    /**
     * Create an Exploration level.
     * We set the cutscenes of the level here.
     * @return the created level
     */
    @Override
    public Level createLevel(GamePanel gamePanel) {
        ExplorationLevel explorationLevel = new ExplorationLevel(gamePanel);
        ArrayList<Cutscene> cutscenes = new ArrayList<>();
        // General
        cutscenes.add(new Cutscene("You lost the fight... Let's try again, shall we? (Respawning...)", "lost_battle"));
        cutscenes.add(new Cutscene("You managed to escape the battle. \nLet's be more careful where we get into.", "escaped_battle"));
        cutscenes.add(new Cutscene("You won the battle! That was close... let's continue.", "won_battle"));
        cutscenes.add(new Cutscene("You found some bandages. Good for healing.", "bandage_found"));
        cutscenes.add(new Cutscene("You found a med kit. Good for healing.", "medkit_found"));
        // Parking lot
        cutscenes.add(new Cutscene("\"This parking lot is full of destroyed cars. I guess none of them work.\"", "parkinglot"));
        // Forest
        cutscenes.add(new Cutscene("\"I used to visit the forest with my dad every weekend. Before...\"", "forest"));
        cutscenes.add(new Cutscene("\"Before everything happened.\"", "forest"));
        cutscenes.add(new Cutscene("You found some strawberries. They are delicious.", "berry_found"));
        cutscenes.add(new Cutscene("You've encountered a strange beast, a wolf-like creature with long fangs, legs, \nand claws.", "wolf_found"));
        cutscenes.add(new Cutscene("He is ready to attack you. Defend yourself!", "wolf_found"));
        // Cave
        cutscenes.add(new Cutscene("\"I'm sure they took her somewhere inside this cave.\"", "cave"));
        cutscenes.add(new Cutscene("You found an electric gun, a powerful one.", "electric_gun_found"));
        cutscenes.add(new Cutscene("You've encountered a beast with the appearance of a kid, ready to attack you. \nDefend yourself!", "infant_found"));
        cutscenes.add(new Cutscene("\"Ema! Are you alright?\"", "final_battle"));
        cutscenes.add(new Cutscene("\"I'm all right. Please, help me!\"\n\n~ Ema", "final_battle"));
        cutscenes.add(new Cutscene("\"Don't speak to her!\"\n\n ~ Bob", "final_battle"));
        cutscenes.add(new Cutscene("\"If you want her freedom. You must fight my soldiers first and then me.\"\n\n~ Bob", "enter_battle_mode_cave"));
        cutscenes.add(new Cutscene("\"All right, let's do this!\"", "enter_battle_mode_cave"));
        cutscenes.add(new Cutscene("\"All right, you defeated my soldiers, now it's time for some real fight.\"\n\n~ Bob", "final_boss"));
        cutscenes.add(new Cutscene("\"You surprise me, you are really good at fighting. And it seems you really don't \nknow what happened. So I'll explain it to you.\"\n~ Ema", "cave_final"));
        cutscenes.add(new Cutscene("\"Any human that has lived in the Citadel has an armband like this one. It's connected \nto the nerve endings and my blood vessels.\"\n~ Ema", "cave_final"));
        cutscenes.add(new Cutscene("\"You need it if you want to live, it's like a mechanical heart that needs batteries. \nAlthough I don't know why you don't need one.\"\n~ Ema", "cave_final"));
        cutscenes.add(new Cutscene("\"What about the monsters?\"", "cave_final"));
        cutscenes.add(new Cutscene("\"The beasts that you encountered are called 'Infants', 'Wolfs' and 'Crystals'. Species \nthat mutated from humans and don't need batteries to live.\"\n~ Ema", "cave_final"));
        cutscenes.add(new Cutscene("\"Those are the basic things that you should know. I can tell you more but it's gonna take \na long time, and we should leave. I'll take you to our camp. Follow me.\"\n~ Ema", "cave_final"));
        cutscenes.add(new Cutscene("\"All right, let's go!", "finished_final_cinematic"));

        explorationLevel.setCutscenes(cutscenes);
        explorationLevel.setUnlocked(false);

        return explorationLevel;
    }
}
