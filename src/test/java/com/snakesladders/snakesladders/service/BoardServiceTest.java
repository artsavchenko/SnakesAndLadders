package com.snakesladders.snakesladders.service;

import com.snakesladders.snakesladders.model.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardServiceTest {

    BoardService boardService;

    @BeforeEach
    public void init() {
        this.boardService = new BoardService();
    }

    @Test
    public void initializeBoard() {
        Board board = boardService.initializeBoard(5);

        Assertions.assertEquals(5, board.getLadders().entrySet().size());
        Assertions.assertEquals(5, board.getSnakes().entrySet().size());
        Assertions.assertTrue(board.getLadders().entrySet().stream().allMatch(e -> e.getKey() < e.getValue()));
        Assertions.assertTrue(board.getSnakes().entrySet().stream().allMatch(e -> e.getKey() > e.getValue()));

    }
}
