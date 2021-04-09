package com.snakesladders.snakesladders.service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class DiceServiceTest {
    DiceService diceService = new DiceService();

    @Test
    public void rollDice() {
        Assertions.assertTrue(diceService.rollDice() > 0);
        Assertions.assertTrue(diceService.rollDice() <=6);
    }
}
