package com.cinnacinnabuns;

import java.util.ArrayList;

public class GameBoard {

    ArrayList<ArrayList<Cell>> boardArray;

    public GameBoard(int height, int width) throws InvalidDimensionException {

        if (height < 1 || width < 1)
            throw new InvalidDimensionException();

        boardArray = new ArrayList<>(height);
        for (int h = 0; h < height; h++) {
            boardArray.add(new ArrayList<>());
        }
        for (ArrayList<Cell> boardLine : boardArray) {
            for (int w = 0; w < width; w++) {
                boardLine.add(new Cell());
            }
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for (ArrayList<Cell> boardLine : boardArray) {
            for (Cell cell : boardLine) {
                sb.append(cell);
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}

class InvalidDimensionException extends Exception {
}
