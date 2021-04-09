package com.snakesladders.snakesladders.enums;

public enum Messages {
    START_INSTRUCTION ("To initialize the Snakes and Ladders game please set \"userName\" and \"level\" in range 1-10 to urn: '/game/initialize/userName/level'. " +
            "After initialization to roll the dice use following urn: '/game/roll_dice'. " +
            "To clear the game use following urn: '/game/clear'"),
    INITIALIZE ("Game initialized for player %s with board setup: %s. Player position = %d."),
    NOT_INITIALIZED ("Game not initialized yet! Please follow instructions to initialize game: %s"),
    GAME_CLEAR ("Game cleared successfully. Please follow instructions to initialize new game: %s"),
    GAME_FINISH ("Game finished successfully. Please follow instructions to initialize new game: %s"),
    LADDERS ("Ladders in this game: "),
    SNAKES ("Snakes in this game: "),
    DICE_RESULT ("The number rolled on the dice: %d."),
    NEW_POSITION ("Now %s is on position: %d."),
    STEP_LADDER ("Congratulations, you step on a ladder %d -> %d."),
    STEP_SNAKE ("Sorry, you step on a snake %d -> %d."),
    WINNER ("Congratulations %s! You win the game!"),
    WRONG_LEVEL ("Wrong level format! Must be integer! Please follow instructions to initialize new game: %s"),
    WRONG_LEVEL_RANGE ("Wrong level range! Must be in range 1-10! You set level: %d. Please follow instructions to initialize new game: %s"),
    GAME_ALREADY_INITIALIZED ("Game already initialized! Player name: %s. Player position: %d. Board setup: %s"),
    OUT_OF_BOUND ("Ooops! Looks like the new position %d is out of board bound! %s stayed on previous position: %d");

    private String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
