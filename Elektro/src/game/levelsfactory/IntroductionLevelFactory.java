package game.levelsfactory;

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
    public Level createLevel() {
        IntroductionLevel introductionLevel = new IntroductionLevel();
        ArrayList<Cutscene> cutscenes = new ArrayList<>();
        cutscenes.add(new Cutscene("You lost the fight... Let's try again, shall we? (Respawning...)", "lost_battle"));
        cutscenes.add(new Cutscene("\"Mirrors. That was the last thing Ray remembered from his dream. Two mirrors that burst into pieces when he tried to look in them.\"", "house"));
        cutscenes.add(new Cutscene("You wake up in your bed and find that your house has changed in several ways: broken glass, no water, old wood... Like an abandoned house...", "house"));
        cutscenes.add(new Cutscene("You walk around your house, the doors to your mother's room and the main exit are locked... Guess you'll have to get back later...", "house"));
        cutscenes.add(new Cutscene("You find a map among the furniture. You keep it because you think it will be useful later...", "house"));
        // add item map
        cutscenes.add(new Cutscene("\"Everything, in short, was more or less the same except for one thing.\"", "house"));
        cutscenes.add(new Cutscene("\"In the middle of the garden, inert and with her hair spilling onto the ground, lay the body of a blonde woman holding what looked like a small black notebook in her hand.\"", "house"));
        cutscenes.add(new Cutscene("You approach with all the fear in the world, but you relax when you are sure that it's not your mother.", "house"));
        cutscenes.add(new Cutscene("You take the book but don't open it, prefer to read it later and continue investigating. You also grab a baseball bat that you used when you were a child.", "house"));
        // add items book and bat
        cutscenes.add(new Cutscene("You remember that there used to be a mall near your house, you head there passing through a hot desert where before everything was towns.", "desert"));
        cutscenes.add(new Cutscene("While traveling through the desert, you encounter a very strange being. Someone with a skeletal appearance, spindly and with bony legs extended. His face was drawn with deep chiseled features. He gets into attack position. Defend yourself!", "desert"));
        // fight
        cutscenes.add(new Cutscene("You managed to escape the beast... You continue your way and arrive at the back of Galleries mall, you enter through the Siarz warehouse.", "desert_escape"));
        cutscenes.add(new Cutscene("You won the fight! You continue your way and arrive at the back of Galleries mall, you enter through the Siarz warehouse.", "desert_win"));
        cutscenes.add(new Cutscene("Among the packages you find some bandages and first aid kits, you save them for later. You leave the warehouse to continue exploring the place.","mall_first"));
        // add items supplies (bandages/first aid kit)
        cutscenes.add(new Cutscene("You walk the hallways, finding stores like CacaoCity, WcWonalds, BucksStar, among others completely abandoned and looted.", "mall_first"));
        cutscenes.add(new Cutscene("You continue moving forward and find a blonde girl leaving the Adidos store. You approach her to try to talk to her.", "mall_first"));
        cutscenes.add(new Cutscene("At first she gets defensive, preventing you from approaching her. But when she sees your right arm, she goes on the offensive.", "mall_first"));
        cutscenes.add(new Cutscene("\"Where's your armband?! Why doesn't your heart need batteries?!\"", "mall_first"));
        cutscenes.add(new Cutscene("The last question leaves you baffled. When you are about to ask her what she meant by that question, several men dressed as soldiers approach with electric weapons ready to fight.", "mall_first"));
        // fight
        cutscenes.add(new Cutscene("The battle is over and you won. However, while you are celebrating that you managed to defeat the soldiers, the mysterious girl you spoke to earlier is taken away behind you.", "mall_final_win"));
        cutscenes.add(new Cutscene("The battle is over and you lost. While you are on the ground trying to get up, the soldiers take away the mysterious girl you spoke to earlier.", "mall_final_lose"));
        cutscenes.add(new Cutscene("Determined to find out what has happened to Origen and the rest of the world, you decide to embark on an adventure to rescue the girl and ask her about what happened.", "mall_final"));

        introductionLevel.setCutscenes(cutscenes);
        introductionLevel.setUnlocked(true);

        return introductionLevel;
    }
}
