package com.snakesladders.snakesladders.model;

import java.util.Map;

public class Board {
    Map<Integer, Integer> snakes;
    Map<Integer, Integer> ladders;

    public Board(Map<Integer, Integer> ladders, Map<Integer, Integer> snakes) {
        this.ladders = ladders;
        this.snakes = snakes;
    }

    public Map<Integer, Integer> getSnakes() {
        return snakes;
    }

    public Map<Integer, Integer> getLadders() {
        return ladders;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        } else if (obj == this) {
            return true;
        } else {
            Board board = (Board) obj;
            return board.getLadders().size() == this.ladders.size() &&
                    board.getLadders().entrySet().stream().allMatch(e -> e.getValue().equals(this.ladders.get(e.getKey()))) &&
                    board.getSnakes().size() == this.snakes.size() &&
                    board.getSnakes().entrySet().stream().allMatch(e -> e.getValue().equals(this.snakes.get(e.getKey())));
        }
    }

}
