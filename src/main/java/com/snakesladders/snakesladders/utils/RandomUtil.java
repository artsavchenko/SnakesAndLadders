package com.snakesladders.snakesladders.utils;

import java.util.Random;

public class RandomUtil {
    public static int getRandomValue(int upperBound) {
        Random rand = new Random();
        int randValue = rand.nextInt(upperBound);
        return 1 + randValue;
    }

    public static int getRandomValue(int underBound, int upperBound) {
        Random rand = new Random();
        int randValue = rand.nextInt(upperBound);
        return underBound + 1 + randValue;
    }
}
