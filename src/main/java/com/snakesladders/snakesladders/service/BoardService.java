package com.snakesladders.snakesladders.service;

import com.snakesladders.snakesladders.enums.Sizes;
import com.snakesladders.snakesladders.model.Board;
import com.snakesladders.snakesladders.utils.RandomUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BoardService {

    public Board initializeBoard(int level) {
        Map<Integer, Integer> ladders = initializeLadders(level);
        Map<Integer, Integer> snakes = initializeSnakes(level, ladders);
        return new Board(ladders, snakes);
    }

    private Map<Integer, Integer> initializeLadders(int level) {
        Map<Integer, Integer> ladders = new HashMap<>(level);
        while (ladders.entrySet().size() < level) {
            int ladderStartPosition = RandomUtil.getRandomValue(1, Sizes.BOARD.getSize() - 3);
            int ladderEndPosition = RandomUtil.getRandomValue(ladderStartPosition, Sizes.BOARD.getSize() - ladderStartPosition - 1);
            ladders.put(ladderStartPosition, ladderEndPosition);
        }
        return ladders;
    }

    private Map<Integer, Integer> initializeSnakes(int level, Map<Integer, Integer> ladders) {
        Map<Integer, Integer> snakes = new HashMap<>(level);
        while (snakes.entrySet().size() < level) {
            int snakeStartPosition = RandomUtil.getRandomValue(1,Sizes.BOARD.getSize() - 2);
            int snakeEndPosition = RandomUtil.getRandomValue(0, snakeStartPosition - 1);
            if(ladders.containsKey(snakeStartPosition) || ladders.containsKey(snakeEndPosition)) {continue;}
            snakes.put(snakeStartPosition, snakeEndPosition);
        }
        return snakes;
    }
}
