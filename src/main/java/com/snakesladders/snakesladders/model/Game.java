package com.snakesladders.snakesladders.model;

import com.snakesladders.snakesladders.enums.Statuses;
import com.snakesladders.snakesladders.service.GameServiceInterface;

public class Game {
    private final Player player;
    private final Board board;
    private Integer dice;
    private Statuses status;

    public Game(Player player, Board board) {
        this.player = player;
        this.board = board;
        this.dice = null;
        this.status = Statuses.INITIALIZED;
    }

    public Player getPlayer() {
        return player;
    }

    public Board getBoard() {
        return board;
    }

    public Integer getDice() {
        return dice;
    }

    public void setDice(Integer dice) {
        this.dice = dice;
    }

    public Statuses getStatus() {
        return status;
    }

    public void setStatus(Statuses status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        } else if (obj == this) {
            return true;
        } else {
            Game game = (Game) obj;
            return game.getBoard().equals(this.board) && game.getPlayer().equals(this.getPlayer()) && game.getStatus().equals(this.status);
        }
    }

}
