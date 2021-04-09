package com.snakesladders.snakesladders.model;

public class Player {
    private int position;
    private final String playerName;

    public Player(String playerName) {
        this.position = 1;
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition (int position) {
        this.position = position;
    }
}
