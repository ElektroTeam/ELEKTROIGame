package game.sound.manager;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundClip {
    private Clip clip;
    private FloatControl floatControl;

    public SoundClip(URL soundURL) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            clip.start();
        }
    }

    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }

    public void setVolume(float volume) {
        if (floatControl != null) {
            floatControl.setValue(volume);
        }
    }
}
