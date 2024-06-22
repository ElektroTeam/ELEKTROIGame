package game.sound.manager;



import game.sound.enums.SoundType;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SoundManager {
    private final Map<SoundType, SoundClip> soundMap = new HashMap<>();
    private int volumeScale = 3;

    public SoundManager() {
        for (SoundType soundType : SoundType.values()) {
            URL soundURL = getClass().getResource(soundType.getpath());
            soundMap.put(soundType, new SoundClip(soundURL));
        }
    }

    public void play(SoundType soundType) {
        SoundClip soundClip = soundMap.get(soundType);
        if (soundClip != null) {
            soundClip.play();
        }
    }

    public void loop(SoundType soundType) {
        SoundClip soundClip = soundMap.get(soundType);
        if (soundClip != null) {
            soundClip.loop();
        }
    }

    public void stop(SoundType soundType) {
        SoundClip soundClip = soundMap.get(soundType);
        if (soundClip != null) {
            soundClip.stop();
        }
    }

    public void setVolumeScale(int volumeScale) {
        this.volumeScale = volumeScale;
        float volume = getVolumeForScale(volumeScale);
        for (SoundClip soundClip : soundMap.values()) {
            soundClip.setVolume(volume);
        }
    }

    private float getVolumeForScale(int volumeScale) {
        switch (volumeScale) {
            case 0: return -80F;
            case 1: return -20F;
            case 2: return -12F;
            case 3: return -5F;
            case 4: return 1F;
            case 5: return 6F;
            default: return -5F;
        }
    }

    public int getVolumeScale() {
        return volumeScale;
    }
}
