package game.sound.enums;

/**
 * The audio that the file references.
 */
public enum SoundType {
    //backgrounds
    BLUEBOY("/sounds/backgrounds/BlueBoyAdventure.wav"),
    HOUSETHEME("/sounds/backgrounds/HouseTheme.wav"),
    DUNGEON("/sounds/backgrounds/Dungeon.wav"),


    //battles
    FINAL_BATTLE("/sounds/backgrounds/FinalBattle.wav"),

    //effects
    BLOCKED("/sounds/effects/Blocked.wav"),
    BURNING("/sounds/effects/Burning.wav"),
    CHIPWALL("/sounds/effects/ChipWall.wav"),
    COIN("/sounds/effects/coin.wav"),
    CURSOR("/sounds/effects/cursor.wav"),
    CUTTREE("/sounds/effects/cuttree.wav"),
    GAMEOVER("/sounds/effects/gameover.wav"),
    HITMONSTER("/sounds/effects/hitmonster.wav"),
    LEVELUP("/sounds/effects/levelup.wav"),
    MERCHANT("/sounds/effects/merchant.wav"),
    PARRY("/sounds/effects/parry.wav"),
    RECEIVEDAMAGE("/sounds/effects/receivedamage.wav"),
    SLEEP("/sounds/effects/sleep.wav"),
    SPEAK("/sounds/effects/speak.wav"),
    STAIRS("/sounds/effects/stairs.wav"),
    POWERUP("/sounds/effects/powerup.wav"),
    UNLOCK("/sounds/effects/unlock.wav"),
    FANFARE("/sounds/effects/fanfare.wav");


    private final String path;

    SoundType(String path) {
        this.path = path;
    }

    public String getpath() {
        return path;
    }
}
