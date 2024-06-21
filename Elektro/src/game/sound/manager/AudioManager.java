package game.sound.manager;



import game.sound.enums.AudioFile;
import game.sound.factory.AudioFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton class which represents the audio manager.
 */
public class AudioManager {
    private static AudioManager instance;
    private final Map<AudioFile, Audio> soundClips;
    private AudioFile currentPlayingSound;
    /**
     * Private constructor without parameters.
     */
    private AudioManager() {
        soundClips = new HashMap<>();
    }
    /**
     * Load an audio file.
     * @param audioFile the audio identifier.
     * @param filePath the path to the audio file.
     * @param type the type of audio.
     */
    public void loadSound(AudioFile audioFile, String filePath, String type) {
        Audio audio = AudioFactory.createSound(type, filePath);
        soundClips.put(audioFile, audio);
    }
    /**
     * Play an audio.
     * @param audioFile the audio identifier.
     */
    public void playSound(AudioFile audioFile) {
        Audio audio = soundClips.get(audioFile);
        if (audio != null) {
            if (audio.isPlaying()) {
                return;
            } audio.play();
        } else {
            System.err.println("Audio not found: " + audioFile);
        }
    }
    /**
     * Stop an audio.
     * @param audioFile the audio identifier.
     */
    public void stopSound(AudioFile audioFile) {
        Audio audio = soundClips.get(audioFile);
        if (audio != null) {
            audio.stop();
        } else {
            System.err.println("Audio not found: " + audioFile);
        }
    }
    /**
     * Close the audio manager.
     */
    public void close() {
        for (Audio audio : soundClips.values()) {
            audio.stop();
        }
        soundClips.clear();
    }
    /**
     * Set the audio volume.
     * @param audioFile the audio identifier.
     * @param volume the new volume.
     */
    public void setVolume(AudioFile audioFile, float volume) {
        Audio audio = soundClips.get(audioFile);
        if (audio != null) {
            audio.setVolume(volume);
        } else {
            System.err.println("Audio not found: " + audioFile);
        }
    }
    /**
     * Return the sound manager instance.
     * If the instance is null, create a new one.
     * @return the sound manager instance.
     */
    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }
}