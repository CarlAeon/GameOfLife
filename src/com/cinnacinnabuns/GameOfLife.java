package com.cinnacinnabuns;

public class GameOfLife {

    private GameBoard board;
    private int snapshot;

    public GameOfLife(int height, int width) throws InvalidDimensionException {
        board = new GameBoard(height, width);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Snapshot ")
                .append(snapshot)
                .append("\n\n")
                .append(board)
                .toString();
    }
}