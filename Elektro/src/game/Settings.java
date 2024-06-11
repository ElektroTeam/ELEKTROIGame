package game;

import music.manager.AudioLoader;
import music.enums.AudioFile;
import utilities.Prompt;

/**
 * Singleton class to manage game settings.
 * We need this to be singleton because we don't need multiple instances of Settings.
 */
public class Settings {
    private static Settings instance;
    private Prompt prompt = new Prompt();
    private boolean musicOn;
    private boolean soundEffectsOn;

    /**
     * Private constructor to prevent instantiation outside the class.
     * @param musicOn initial state of musicOn setting
     * @param soundEffectsOn initial state of soundEffectsOn setting
     */
    private Settings(boolean musicOn, boolean soundEffectsOn) {
        this.musicOn = musicOn;
        this.soundEffectsOn = soundEffectsOn;
    }

    /**
     * Displays the settings menu and allow the user to change the settings.
     * The user will stay in the settings menu until they decide to leave.
     */
    public void displaySettings(){

        boolean keepInSettings = true;
        while(keepInSettings){
            System.out.println("Settings: ");
            if(musicOn){
                System.out.println("1. Turn music off");
            } else {
                System.out.println("1. Turn music on");
            }
            if(soundEffectsOn){
                System.out.println("2. Turn sound effects off");
            } else {
                System.out.println("2. Turn sound effects on");
            }
            System.out.println("0. Back to menu");
            System.out.print("> ");

            switch(prompt.requestInt()){
                case 1:
                    if(musicOn){
                        AudioLoader.stopSound(AudioFile.MENU);
                        musicOn = false;
                        System.out.println("Music turned off.");
                    } else {
                        musicOn = true;
                        AudioLoader.playSound(AudioFile.MENU);
                        System.out.println("Music turned on.");
                    }
                    break;
                case 2:
                    if(soundEffectsOn){
                        soundEffectsOn = false;
                        System.out.println("Sound effects turned off.");
                    } else {
                        System.out.println("Sound effects turned on.");
                        soundEffectsOn = true;
                    }
                    break;
                case 0:
                    keepInSettings = false;
                    break;
                default:
                    System.out.println("You've entered an invalid option, please try again: ");
                    break;
            }
        }
    }
    /**
     * Return the instance of Settings.
     * If the instance is null, create a new one.
     * @return the instance of Settings.
     */
    public static Settings getInstance(){
        if(instance==null) {
            instance = new Settings(true, true);
        }
        return instance;
    }
    public boolean isMusicOn() {return musicOn;}
    public void setMusicOn(boolean musicOn) {this.musicOn = musicOn;}
    public boolean isSoundEffectsOn() {return soundEffectsOn;}
    public void setSoundEffectsOn(boolean soundEffectsOn) {this.soundEffectsOn = soundEffectsOn;}
}
