package music.factory;

import music.factory.types.BackgroundAudio;
import music.factory.types.EffectAudio;
import music.manager.Audio;

/**
 * Public class to create audios.
 */
public class AudioFactory {
    /**
     * Create the audio.
     * @param type the type of audio.
     * @param filePath the path to the audio file.
     * @return the new audio.
     */
    public static Audio createSound(String type, String filePath) {
        return switch (type.toLowerCase()) {
            case "background" -> new BackgroundAudio(filePath);
            case "effect" -> new EffectAudio(filePath);
            default -> throw new IllegalArgumentException("Unknown audio type: " + type);
        };
    }
}
