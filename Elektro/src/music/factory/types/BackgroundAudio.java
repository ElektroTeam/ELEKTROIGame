package music.factory.types;

import game.Settings;
import music.manager.Audio;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * Public class which represents the background music in the game.
 * @see Audio
 */
public class BackgroundAudio extends Audio {
    /**
     * Public constructor with parameters.
     * @param filePath the path to the audio file.
     */
    public BackgroundAudio(String filePath) {
        super(filePath);
    }
    /**
     * Play the audio.
     */
    @Override
    public void play() {
        if(Settings.getInstance().isMusicOn()) {
            if (clip != null) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.setFramePosition(0);
                clip.start();
                //System.out.println("Reproduciendo sonido de fondo: " + clip);

            }
        }
    }
    /**
     * Stop the audio.
     */
    @Override
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            //System.out.println("Deteniendo sonido de fondo: " + clip);

        }
    }
    /**
     * Set the volume.
     * @param volume the new audio volume.
     */
    @Override
    public void setVolume(float volume) {
        if (clip != null) {
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(volume);
        }
    }


}