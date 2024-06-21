package game.sound.enums;

/**
 * The audio that the file references.
 */
public enum AudioFile {
    FINAL_BATTLE("FinalBattle.wav"),
    CRISTAL_BATTLE("CristalBattle.wav"),
    FINAL_BATTLE_LEVEL_2("FinalBattleLevel2_W.wav"),
    SOLDIERS_BATTLE("SoldiersBattle.wav"),
    WOLF_BATTLE("WolfBattle.wav"),
    INFANT_BATTLE("InfantBattle.wav"),
    HOUSE("House.wav"),
    BACKYARD("Backyard.wav"),
    DESERT("Desert.wav"),
    MALL("Mall.wav"),
    CAVE("Cave.wav"),
    FOREST("Forest.wav"),
    PARKING_LOT("ParkingLot.wav"),
    MENU("Menu.wav"),
    CREDITS("Credits.wav"),
    ENCOUNTER_SOLDIERS("EncounterSoldiers.wav"),
    INTRODUCTION("Introduction.wav"),
    DEAD("Dead.wav"),
    NEXT("Next.wav"),
    SEEING_DEAD_PERSON("SeeingDeadPerson.wav"),
    ITEM_FOUND("ItemFound.wav"),
    ENDIG("Ending.wav");

    private final String fileName;

    AudioFile(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
