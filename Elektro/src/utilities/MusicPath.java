package utilities;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public enum MusicPath {
    //backgrounds

    BACKGROUND_MUSIC("/sounds/backgrounds/BlueBoyAdventure.wav"),
    COIN("/sounds/effects/coin.wav"),
    POWERUP("/sounds/effects/powerup.wav"),
    UNLOCK("/sounds/effects/unlock.wav"),
    FANFARE("/sounds/effects/fanfare.wav");

    private final String filePath;


    MusicPath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }


}
