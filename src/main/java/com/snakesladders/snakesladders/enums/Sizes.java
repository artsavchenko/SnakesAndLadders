package com.snakesladders.snakesladders.enums;

public enum Sizes {
    BOARD (100),
    DICE (6);

    private int size;

    Sizes(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
