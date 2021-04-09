package com.snakesladders.snakesladders.service;

import com.snakesladders.snakesladders.enums.Sizes;
import com.snakesladders.snakesladders.utils.RandomUtil;
import org.springframework.stereotype.Service;

@Service
public class DiceService {
    public int rollDice() {
        int result = RandomUtil.getRandomValue(Sizes.DICE.getSize());
        return result;
    }
}
