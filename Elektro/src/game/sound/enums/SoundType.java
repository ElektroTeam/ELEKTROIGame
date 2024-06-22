package game.sound.enums;

/**
 * The audio that the file references.
 */
public enum SoundType {
    BACKGROUND("/sounds/backgrounds/BlueBoyAdventure.wav"),
    COIN("/sounds/coin.wav"),
    POWERUP("/sounds/powerup.wav"),
    UNLOCK("/sounds/unlock.wav"),
    FANFARE("/sounds/fanfare.wav");

    private final String path;

    SoundType(String path) {
        this.path = path;
    }

    public String getpath() {
        return path;
    }
}
