package game.sound.manager;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * Abstract class which represents audios in the game.
 */
public abstract class Audio {
    protected Clip clip;
    /**
     * Public constructor with parameters.
     * @param filePath the path to the audio file.
     */
    public Audio(String filePath) {
        loadSound(filePath);
    }
    /**
     * Play the audio.
     */
    public abstract void play();
    /**
     * Stop the audio.
     */
    public abstract void stop();
    /**
     * Set the audio volume.
     * @param volume the new audio volume.
     */
    public abstract void setVolume(float volume);
    /**
     * Validate if the audio is currently playing.
     * @return whether the audio is being played or not.
     */
    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }
    /**
     * Load the audio.
      * @param filePath the path to the audio file.
     */
    private void loadSound(String filePath) {
        try {
            URL audioUrl = getClass().getResource(filePath);
            if (audioUrl == null) {
                System.err.println("We couldn't find the audio file: " + filePath);
                return;
            }
            //System.out.println("Loading file from: " + audioUrl);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioUrl);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioStream);
            //System.out.println("The file was succesfully loaded: " + filePath);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    /**
     * Get the audio clip.
     * @return the audio clip.
     */
    public Clip getClip() {return clip;}
    /**
     * Set the audio clip.
     * @param clip the new audio clip.
     */
    public void setClip(Clip clip) {this.clip = clip;}
}
