package com.snakesladders.snakesladders.service;

import com.snakesladders.snakesladders.exceptions.BadFormatException;
import com.snakesladders.snakesladders.exceptions.GameNotFoundException;
import com.snakesladders.snakesladders.model.Board;
import com.snakesladders.snakesladders.model.Game;
import com.snakesladders.snakesladders.model.Player;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
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
    public void initializeGame() throws BadFormatException {
        Map<Integer, Integer> ladders = new HashMap<>();
        ladders.put(10, 20);
        ladders.put(25, 99);
        Map<Integer, Integer> snakes = new HashMap<>();
        snakes.put(55, 3);
        snakes.put(99, 89);
        Board board = new Board(ladders, snakes);

        Mockito.when(boardServiceMock.initializeBoard(Mockito.anyInt())).thenReturn(board);

        Game result = gameService.initializeGame("testPlayer", 2);
        Assertions.assertEquals(new Game(new Player("testPlayer"), new Board(ladders, snakes)), result);
    }

    @Test(expected = BadFormatException.class)
    public void initializeGameException() throws BadFormatException {
        gameService.initializeGame("testPlayer", 0);
    }

    @Test
    public void playGame() throws BadFormatException, GameNotFoundException {
        Map<Integer, Integer> ladders = new HashMap<>();
        ladders.put(10, 20);
        ladders.put(25, 99);
        Map<Integer, Integer> snakes = new HashMap<>();
        snakes.put(55, 3);
        snakes.put(99, 89);
        Board board = new Board(ladders, snakes);

        Mockito.when(boardServiceMock.initializeBoard(Mockito.anyInt())).thenReturn(board);
        gameService.initializeGame("testPlayer", 2);

        Mockito.when(diceServiceMock.rollDice()).thenReturn(2);

        Game result = gameService.playGame();
        Assertions.assertEquals(3, result.getPlayer().getPosition());
        Assertions.assertEquals(2, result.getDice());
    }

    @Test(expected = GameNotFoundException.class)
    public void playGameException() throws GameNotFoundException {
        gameService.playGame();
    }

    @Test(expected = GameNotFoundException.class)
    public void clear() throws GameNotFoundException, BadFormatException {
        Map<Integer, Integer> ladders = new HashMap<>();
        ladders.put(10, 20);
        ladders.put(25, 99);
        Map<Integer, Integer> snakes = new HashMap<>();
        snakes.put(55, 3);
        snakes.put(99, 89);
        Board board = new Board(ladders, snakes);

        Mockito.when(boardServiceMock.initializeBoard(Mockito.anyInt())).thenReturn(board);
        gameService.initializeGame("testPlayer", 2);

        gameService.clear();
        gameService.clear();
    }

    @Test
    public void getGame() throws BadFormatException, GameNotFoundException {
        Map<Integer, Integer> ladders = new HashMap<>();
        ladders.put(10, 20);
        ladders.put(25, 99);
        Map<Integer, Integer> snakes = new HashMap<>();
        snakes.put(55, 3);
        snakes.put(99, 89);
        Board board = new Board(ladders, snakes);

        Mockito.when(boardServiceMock.initializeBoard(Mockito.anyInt())).thenReturn(board);
        Game expected = gameService.initializeGame("testPlayer", 2);
        Game result = gameService.getGame();
        Assertions.assertEquals(expected, result);
    }

    @Test(expected = GameNotFoundException.class)
    public void getGameException() throws BadFormatException, GameNotFoundException {
        gameService.getGame();
    }
}
