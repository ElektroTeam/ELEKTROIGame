package game.sound.manager;


import game.sound.enums.AudioFile;

/**
 * Public class which manages the audio loader.
 */
public class AudioLoader {
    static AudioManager audioManager = AudioManager.getInstance();
    /**
     * Close the sound manager.
     */
    public static void closeSound() {
        audioManager.close();
    }
    /**
     * Load the audios.
     */
    public static void loadSounds() {
        audioManager.loadSound(AudioFile.FINAL_BATTLE, "../../../resources/audio/battles/FinalBattle_N.wav", "background");
        audioManager.loadSound(AudioFile.CRISTAL_BATTLE, "../../../resources/audio/battles/CristalBattle_XY.wav", "background");
        audioManager.loadSound(AudioFile.FINAL_BATTLE_LEVEL_2, "../../../resources/audio/battles/FinalBattleLevel2_W.wav", "background");
        audioManager.loadSound(AudioFile.SOLDIERS_BATTLE, "../../../resources/audio/battles/SoldiersElecktroBattle_PD.wav", "background");
        audioManager.loadSound(AudioFile.WOLF_BATTLE, "../../../resources/audio/battles/WolfBattle_N.wav", "background");
        audioManager.loadSound(AudioFile.INFANT_BATTLE, "../../../resources/audio/battles/InfantBattle.wav", "background");

        audioManager.loadSound(AudioFile.HOUSE, "../../../resources/audio/level 1/house/HouseTheme_XY.wav", "background");
        audioManager.loadSound(AudioFile.BACKYARD, "../../../resources/audio/level 1/backyard/backyard.wav", "background");
        audioManager.loadSound(AudioFile.DESERT, "../../../resources/audio/level 1/desert/Desert.wav", "background");
        audioManager.loadSound(AudioFile.MALL, "../../../resources/audio/level 1/mall/Mall_XY.wav", "background");
        audioManager.loadSound(AudioFile.CAVE, "../../../resources/audio/level 2/cave/Cave_BW.wav", "background");
        audioManager.loadSound(AudioFile.FOREST, "../../../resources/audio/level 2/forest/Forest_XY.wav", "background");
        audioManager.loadSound(AudioFile.PARKING_LOT, "../../../resources/audio/level 2/parking_lot/ParkingLot_GS.wav", "background");

        audioManager.loadSound(AudioFile.MENU, "../../../resources/audio/menu/Menu.wav", "background");
        audioManager.loadSound(AudioFile.CREDITS, "../../../resources/audio/menu/Credits.wav", "background");

        audioManager.loadSound(AudioFile.ENCOUNTER_SOLDIERS, "../../../resources/audio/Cinematics/EncounterSoldiers_RZ.wav", "background");
        audioManager.loadSound(AudioFile.INTRODUCTION, "../../../resources/audio/Cinematics/Introduction_BW.wav", "background");
        audioManager.loadSound(AudioFile.ENDIG, "../../../resources/audio/Cinematics/Ending.wav", "background" );

        audioManager.loadSound(AudioFile.DEAD, "../../../resources/effects/battle_effects/death.wav", "effect");
        audioManager.loadSound(AudioFile.NEXT, "../../../resources/effects/button_effects/Next.wav", "effect");
        audioManager.loadSound(AudioFile.SEEING_DEAD_PERSON, "../../../resources/effects/entity_effects/seeingDeadPerson.wav", "effect");
        audioManager.loadSound(AudioFile.ITEM_FOUND, "../../../resources/effects/item_effects/ItemFound.wav", "effect");

        //Corregir nivel de sonido
        audioManager.setVolume(AudioFile.NEXT, -17.0f);
        audioManager.setVolume(AudioFile.SOLDIERS_BATTLE, -5.0f);
        audioManager.setVolume(AudioFile.PARKING_LOT, -5.0f);
        audioManager.setVolume(AudioFile.CAVE, -5.0f);
        
    }
    /**
     * Play the audio.
     * @param audioFile the audio identifier.
     */
    public static void playSound(AudioFile audioFile) {
        audioManager.playSound(audioFile);
    }
    /**
     * Stop the audio.
     * @param audioFile the audio identifier.
     */
    public static void stopSound(AudioFile audioFile) {
        audioManager.stopSound(audioFile);
    }
}
