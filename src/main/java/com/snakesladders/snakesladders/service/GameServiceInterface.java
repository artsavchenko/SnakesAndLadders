package com.snakesladders.snakesladders.service;

public interface GameServiceInterface {
    String initializeGame(String playerName, int level);
    String rollDice();
    String clear();
}
