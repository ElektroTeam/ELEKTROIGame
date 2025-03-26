package game.levelsfactory;

import game.GamePanel;
import game.cutscene.Cutscene;
import game.levelsfactory.levels.IntroductionLevel;
import game.levelsfactory.levels.Level;

import java.util.ArrayList;
/**
 * The factory of the second level.
 * @see LevelFactory
 */
public class IntroductionLevelFactory extends LevelFactory {
    /**
     * Create an Introduction level.
     * We set the cutscenes of the level here.
     * @return the created level
     */
    @Override
    public Level createLevel(GamePanel gamePanel) {
        IntroductionLevel introductionLevel = new IntroductionLevel(gamePanel);
        ArrayList<Cutscene> cutscenes = new ArrayList<>();
        // General
        cutscenes.add(new Cutscene("You lost the fight... Let's try again, shall we? (Respawning...)", "lost_battle"));
        cutscenes.add(new Cutscene("You managed to escape the battle. \nLet's be more careful where we get into.", "escaped_battle"));
        cutscenes.add(new Cutscene("You won the battle! That was close... let's continue.", "won_battle"));
        cutscenes.add(new Cutscene("You found some bandages. Good for healing.", "bandage_found"));
        cutscenes.add(new Cutscene("You found a med kit. Good for healing.", "medkit_found"));
        // House
        cutscenes.add(new Cutscene("Mirrors. That was the last thing Ray remembered from his dream. \nTwo mirrors that burst into pieces when he tried to look in them.", "house"));
        cutscenes.add(new Cutscene("\"Ugh... shouldn't have overslept. How much did I sleep?\nWait... what happened to the house...?\"", "house"));
        cutscenes.add(new Cutscene("\"My mom's bedroom is locked? That's weird.\"", "locked_mom_bedroom"));
        cutscenes.add(new Cutscene("\"Why is the bathroom locked...?\"", "locked_bathroom"));
        cutscenes.add(new Cutscene("You found a map. It might be useful in case you get lost.", "map_found"));

        // Origen
        cutscenes.add(new Cutscene("In the middle of the garden, inert and with her hair spilling onto the ground, \nlay the body of a blonde woman holding what looked like a small black notebook \nin her hand.", "origen"));
        cutscenes.add(new Cutscene("\"What is that...? Mom?\nWait... it's not you.\"", "origen"));
        cutscenes.add(new Cutscene("\"Is that a book? a diary? I should take a look...\"", "origen"));
        cutscenes.add(new Cutscene("You found a diary. Whose diary could it be?", "diary_found"));
        cutscenes.add(new Cutscene("You found a bat. It should be useful for self-defense.", "bat_found"));
        cutscenes.add(new Cutscene("Hmm, it seems like the house is locked.", "locked_neighbor_house"));
        cutscenes.add(new Cutscene("HINT: You should check around the map... \nThere might be something that you will use in the future...", "must_find_bat_first"));
        // Desert
        cutscenes.add(new Cutscene("The desert.\nIt's really hot...", "desert"));
        cutscenes.add(new Cutscene("You've encountered a strange beast, with skeletal appearance, spindly and with bony \nextended legs. His face drawn with deep chiseled features.", "crystal_found"));
        cutscenes.add(new Cutscene("He gets into attack position, get ready to defend yourself!", "crystal_found"));

        // Mall
        cutscenes.add(new Cutscene("\"Ah.. I remember when I used to visit Galleries with Zack all the time.\"", "mall"));
        cutscenes.add(new Cutscene("\"Hey, who are you?!\"", "ema_found"));
        cutscenes.add(new Cutscene("\"Who am I? The question is, who are you? And what are you doing out here?!\"\n\n~ Ema", "ema_found"));
        cutscenes.add(new Cutscene("\"You shouldn... Wait! Where's your armband?! Why doesn't your heart need batteries?!\"\n\n~ Ema", "ema_found"));
        cutscenes.add(new Cutscene("\"An armband? Batteries? What do you mean?!\"", "ema_found"));
        cutscenes.add(new Cutscene("\"It's hard to explain. First of all, we need to...\"\n\n ~ Ema", "ema_found"));
        cutscenes.add(new Cutscene("\"Escape before we find you? I think it's too late for that.\"\n\n~ Bob", "enter_bob"));
        cutscenes.add(new Cutscene("\"Who are you?\"", "enter_bob"));
        cutscenes.add(new Cutscene("\"Name's Bob. And I'm here for Ema.\"\n\n~ Bob", "enter_bob"));
        cutscenes.add(new Cutscene("\"Too much chatting. Let the fight begin!\"\n\n~ Ema",  "enter_battle_mode_mall"));
        cutscenes.add(new Cutscene("You were defeated by Bob. And while you were unconscious in the ground, they \ntook Ema.", "finished_battle_mall"));
        cutscenes.add(new Cutscene("It's time for you to be a hero, save her!", "finished_battle_mall"));
        cutscenes.add(new Cutscene("You found an electric bat. It should be more powerful than the normal bat.", "electric_bat_found"));

        introductionLevel.setCutscenes(cutscenes);
        introductionLevel.setUnlocked(true);

        return introductionLevel;
    }
}
