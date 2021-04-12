package com.snakesladders.snakesladders.service;

import com.snakesladders.snakesladders.exceptions.BadFormatException;
import com.snakesladders.snakesladders.exceptions.GameNotFoundException;
import com.snakesladders.snakesladders.model.Game;

public interface GameServiceInterface {
    Game initializeGame(String playerName, int level) throws BadFormatException;

    Game playGame() throws GameNotFoundException;

    Game getGame() throws GameNotFoundException;

    void clear() throws GameNotFoundException;
}
