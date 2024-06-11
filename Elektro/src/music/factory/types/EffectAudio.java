package music.factory.types;

import game.Settings;
import music.manager.Audio;

import javax.sound.sampled.FloatControl;

/**
 * Public class which represents sound effects in the game.
 * @see Audio
 */
public class EffectAudio extends Audio {
    /**
     * Public constructor with parameters.
     * @param filePath the path to the audiof ile.
     */
    public EffectAudio(String filePath) {
        super(filePath);
    }
    /**
     * Play the audio.
     */
    @Override
    public void play() {
        if(Settings.getInstance().isSoundEffectsOn()){
            if (clip != null) {
                clip.setFramePosition(0);
                clip.start();
                //System.out.println("Reproduciendo efecto de sonido: " + clip);

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
            //System.out.println("Deteniendo efecto de sonido: " + clip);

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