package com.snakesladders.snakesladders.service;

import com.snakesladders.snakesladders.enums.Messages;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

    @Test
    public void initializeGame() {
        String result = gameService.initializeGame("testName", 2);
        Assertions.assertEquals(String.format(Messages.INITIALIZE.getMessage(), "testName", "board", 1), result);

        result = gameService.initializeGame("testName", 0);
        Assertions.assertEquals(String.format(Messages.WRONG_LEVEL_RANGE.getMessage(), 0, Messages.START_INSTRUCTION.getMessage()), result);
    }

    @Test
    public void rollDice() {
        String result = gameService.rollDice();
        Assertions.assertEquals(String.format("%s %s %s", String.format(Messages.DICE_RESULT.getMessage(), 2), "", String.format(Messages.NEW_POSITION.getMessage(), "testName", 3)), result);
    }

    @Test
    public void clear() {
        String result = gameService.clear();
        Assertions.assertEquals(String.format(Messages.GAME_CLEAR.getMessage(), Messages.START_INSTRUCTION.getMessage()), result);

        result = gameService.clear();
        Assertions.assertEquals(String.format(Messages.NOT_INITIALIZED.getMessage(), Messages.START_INSTRUCTION.getMessage()), result);
    }
}
