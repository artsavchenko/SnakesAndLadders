package com.snakesladders.snakesladders.model;

public class Game {
    private final Player player;
    private final Board board;

    public Game(Player player, Board board){
        this.player = player;
        this.board = board;
    }

    public Player getPlayer() {
        return player;
    }

    public Board getBoard() {
        return board;
    }
}
