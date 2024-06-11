package game.levelsfactory;

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
    public Level createLevel() {
        ExplorationLevel explorationLevel = new ExplorationLevel();
        ArrayList<Cutscene> cutscenes = new ArrayList<>();
        // The player lost the battle and respawns.
        cutscenes.add(new Cutscene("You lost the fight... Let's try again, shall we? (Respawning...)", "lost_battle"));

        cutscenes.add(new Cutscene("You've exited the mall through the main exit.", "parking_lot"));
        cutscenes.add(new Cutscene("You walked through the parking lot of the mall, but couldn't find any items in the abandoned cars.", "parking_lot"));
        cutscenes.add(new Cutscene("You exited the parking lot and now you are in front of a big forest.", "parking_lot"));
        cutscenes.add(new Cutscene("You've entered the forest and found some strawberries in the bushes.", "forest"));
        cutscenes.add(new Cutscene("While you are walking around the forest, a new beast appears in front of you. A wolf-like creature with long fangs, legs and claws. He is ready to attack you. Defend yourself!", "forest"));
        cutscenes.add(new Cutscene("You ran and hide from the Wolf, and he lost track of you.", "forest_escape"));
        cutscenes.add(new Cutscene("You won the battle, now you must continue walking down the forest to get to your destiny.", "forest_win"));
        cutscenes.add(new Cutscene("Deep inside the forest, you found an illuminated cave with human marks. Now it's time to enter the cave.", "forest_win"));
        cutscenes.add(new Cutscene("While you are exploring the cave, another beast appears in front of you. You see a beast with the appearence of a kid, ready to attack you. Defend yourself!", "cave"));
        cutscenes.add(new Cutscene("You managed to escape the beast and while you are running down the cave, you encounter another beast with skeletal appearance. Defend yourself!", "cave_infant_escape"));
        cutscenes.add(new Cutscene("You won the battle, and while you are running down the cave, you encounter another beast with skeletal appearance. Defend yourself!", "cave_infant_win"));
        cutscenes.add(new Cutscene("You managed to escape the beast and while you are running down the cave, you find the secret lair of the soldiers that kidnapped the mysterious girl.", "cave_crystal_escape"));
        cutscenes.add(new Cutscene("You won the battle, and while you are running down the cave, you find the secret lair of the soldiers that kidnapped the mysterious girl.", "cave_crystal_win"));
        cutscenes.add(new Cutscene("You manage to sneak past the soldiers to free the girl and now you have to fight the soldiers", "cave_soldiers_fight"));
        cutscenes.add(new Cutscene("You managed to defeat the soldiers, and there's only one left, the boss named 'Bob'. You must defeat him!", "cave_soldiers_win"));
        cutscenes.add(new Cutscene("You defeated Bob! And while you are celebrating, the girl gets next to you.", "cave_bob_win"));
        cutscenes.add(new Cutscene("\"You surprise me, you are really good at fighting. And it seems you really don't know what's happened. So I'll explain it to you.\"", "cave_final"));
        cutscenes.add(new Cutscene("\"Any human that has lived in the Citadel has an armband like this one. It's connected to the nerve endings and my blood vessels.\"", "cave_final"));
        cutscenes.add(new Cutscene("\"You need it if you want to live, it's like a mechanical heart that needs batteries. Although I don't know why you don't need one.\"", "cave_final"));
        cutscenes.add(new Cutscene("\"The beasts that you encountered are called 'Infants', 'Wolfs' and 'Crystals'. Species that mutated from humans and don't need batteries to live.\"", "cave_final"));
        cutscenes.add(new Cutscene("\"Those are the basic things that you should know. I can tell you more but it's gonna take a long time, and we should live. I'll take you to our camp. Follow me.\"", "cave_final"));
        cutscenes.add(new Cutscene("While you are exiting the cave, you remember that you picked up a book from your yard and decide to take a look at it.", "cave_final"));
        cutscenes.add(new Cutscene("\"May 15, 2020\nEverything has changed. Since they launched the alerts, nothing is the same. Neither is the university. [...]\"", "diary"));
        cutscenes.add(new Cutscene("\"May 16, 2020\nI'm still alive. For now. [...] \nPD: Yes, there's a curfew here too.\"", "diary"));
        cutscenes.add(new Cutscene("\"June 12, 2020\nMom has gotten worse. Dad took her directly to the clinic. [...] \nPD: They say that after the storm, calm comes. What they don't say is that, after the calm, hell always breaks loose.\"", "diary"));
        cutscenes.add(new Cutscene("\"September 15, 2020\nI passed the admission exams. I'm in. \nPD: Mom is dead.\"", "diary"));
        cutscenes.add(new Cutscene("TO BE CONTINUED", "diary"));

        explorationLevel.setCutscenes(cutscenes);
        explorationLevel.setUnlocked(false);

        return explorationLevel;
    }
}
