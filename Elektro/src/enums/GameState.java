package enums;

/**
 * The state of the game
 */
public enum GameState {
    /**
     *  When the player is in the main menu.
     */
    TITLE_STATE,
    /**
     * When the player is playing.
     */
    PLAY_STATE,
    /**
     * When the player paused the game.
     */
    PAUSE_STATE,
    /**
     * When the player is interacting with an NPC.
     */
    DIALOGUE_STATE,
    /**
     * When the player opens the options window.
     */
    OPTIONS_STATE,
    /**
     * When the player lost the game.
     */
    GAME_OVER_STATE,
    /**
     * When the player is checking the map.
     */
    MAP_STATE,
    /**
     * When the player is interacting with cutscene.
     */
    CUTSCENE_STATE,
    /**
     * When the player is choosing a level to play.
     */
    CHOOSE_LEVEL_STATE,
    /**
     * When the player is checking the diary.
     */
    DIARY_STATE,
    /**
     * When the player is interacting with the mall cinematic.
     */
    MALL_CINEMATIC_STATE,
    /**
     * When the player is in battle mode.
     */
    BATTLE_STATE,
    /**
     * When the player is interacting with the cave cinematic.
     */
    CAVE_CINEMATIC_STATE,
    /**
     * When the player is interacting with the cave cinematic (movement included).
     */
    CAVE_CINEMATIC_MOVEMENT_STATE,
    /**
     * When the player is interacting with the final cinematic.
     */
    FINAL_CINEMATIC_STATE,
    /**
     * When the player is interacting with the final cinematic (game finished).
     */
    FINAL_CINEMATIC_FINISHED_STATE,
    /**
     * When the player opens the credits window.
     */
    CREDITS_STATE
}
