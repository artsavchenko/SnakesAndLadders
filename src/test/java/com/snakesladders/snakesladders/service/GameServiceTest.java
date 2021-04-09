package com.snakesladders.snakesladders.service;

import com.snakesladders.snakesladders.enums.Messages;
import com.snakesladders.snakesladders.model.Board;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {
    @Mock
    BoardService boardServiceMock;

    @Mock
    DiceService diceServiceMock;

    @InjectMocks
    GameService gameService;

    @BeforeTestMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void initializeGame() {
        Map<Integer, Integer> ladders = new HashMap<>();
        ladders.put(10,20);
        ladders.put(25, 99);
        Map<Integer, Integer> snakes = new HashMap<>();
        snakes.put(55,3);
        snakes.put(99, 89);
        Board board = new Board(ladders,snakes);

        Mockito.when(boardServiceMock.initializeBoard(Mockito.anyInt())).thenReturn(board);

        String result = gameService.initializeGame("testName", 2);
        Assertions.assertEquals(String.format(Messages.INITIALIZE.getMessage(), "testName", board.toString(), 1), result);

        result = gameService.initializeGame("testName", 0);
        Assertions.assertEquals(String.format(Messages.WRONG_LEVEL_RANGE.getMessage(), 0, Messages.START_INSTRUCTION.getMessage()), result);
    }

    @Test
    public void rollDice() {
        Map<Integer, Integer> ladders = new HashMap<>();
        ladders.put(10,20);
        ladders.put(25, 99);
        Map<Integer, Integer> snakes = new HashMap<>();
        snakes.put(55,3);
        snakes.put(99, 89);
        Board board = new Board(ladders,snakes);

        Mockito.when(boardServiceMock.initializeBoard(Mockito.anyInt())).thenReturn(board);
        gameService.initializeGame("testName", 2);

        Mockito.when(diceServiceMock.rollDice()).thenReturn(2);

        String result = gameService.rollDice();
        Assertions.assertEquals(String.format("%s %s %s", String.format(Messages.DICE_RESULT.getMessage(), 2), "", String.format(Messages.NEW_POSITION.getMessage(), "testName", 3)), result);
    }

    @Test
    public void clear() {
        Map<Integer, Integer> ladders = new HashMap<>();
        ladders.put(10,20);
        ladders.put(25, 99);
        Map<Integer, Integer> snakes = new HashMap<>();
        snakes.put(55,3);
        snakes.put(99, 89);
        Board board = new Board(ladders,snakes);

        Mockito.when(boardServiceMock.initializeBoard(Mockito.anyInt())).thenReturn(board);
        gameService.initializeGame("testName", 2);

        String result = gameService.clear();
        Assertions.assertEquals(String.format(Messages.GAME_CLEAR.getMessage(), Messages.START_INSTRUCTION.getMessage()), result);

        result = gameService.clear();
        Assertions.assertEquals(String.format(Messages.NOT_INITIALIZED.getMessage(), Messages.START_INSTRUCTION.getMessage()), result);
    }
}
