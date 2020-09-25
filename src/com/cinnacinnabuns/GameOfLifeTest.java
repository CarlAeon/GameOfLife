package com.cinnacinnabuns;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameOfLifeTest {

    @Test
    void testDimensionsAreValid() {

        Assertions.assertThrows(
                InvalidDimensionException.class,
                () -> new GameOfLife(0, 1));
        Assertions.assertThrows(
                InvalidDimensionException.class,
                () -> new GameOfLife(1, 0));
        Assertions.assertDoesNotThrow(
                () -> new GameOfLife(1, 1));
    }
}