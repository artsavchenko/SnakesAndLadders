package com.snakesladders.snakesladders.service;

import com.snakesladders.snakesladders.utils.RandomUtil;
import org.springframework.stereotype.Service;

@Service
public class DiceService {
    private final int DICE_SIZE = 6;

    public int rollDice() {
        return RandomUtil.getRandomValue(DICE_SIZE);
    }
}
