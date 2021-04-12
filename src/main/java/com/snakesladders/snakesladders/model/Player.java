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

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        } else if (obj == this) {
            return true;
        } else {
            Player player = (Player) obj;
            return player.getPosition() == this.position && player.getPlayerName().equals(this.playerName);
        }
    }
}
