package game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];
    FloatControl floatControl;
   int volumeScale = 3;
   float volume;
    public Sound() {
        soundURL[0] = getClass().getResource("/sounds/BlueBoyAdventure.wav");
        soundURL[1] = getClass().getResource("/sounds/coin.wav");
        soundURL[2] = getClass().getResource("/sounds/powerup.wav");
        soundURL[3] = getClass().getResource("/sounds/unlock.wav");
        soundURL[4] = getClass().getResource("/sounds/fanfare.wav");
    }
    public void setFile(int i){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
        } catch(Exception e){
            e.printStackTrace();
        }

    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
    public void checkVolume(){
        switch (volumeScale){
            case 0:
                volume = -80F;
                break;
            case 1:
                volume = -20F;
                break;
            case 2:
                volume = -12F;
                break;
            case 3:
                volume = -5F;
                break;
            case 4:
                volume = 1F;
                break;
            case 5:
                volume = 6F;
                break;
        }
        floatControl.setValue(volume);
    }
}
