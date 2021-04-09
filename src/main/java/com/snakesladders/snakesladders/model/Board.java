package com.snakesladders.snakesladders.model;

import com.snakesladders.snakesladders.enums.Messages;

import java.util.Map;
import java.util.stream.Collectors;

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
    public String toString() {
        return Messages.LADDERS.getMessage() + ladders.entrySet().stream().map(e -> e.getKey()+"->"+e.getValue()).collect(Collectors.joining("; "))
                + "\n" + Messages.SNAKES.getMessage() + snakes.entrySet().stream().map(e -> e.getKey()+"->"+e.getValue()).collect(Collectors.joining("; "));
    }












}
