package com.cinnacinnabuns;

public class GameOfLife {

    private GameBoard board;
    private int snapshot;

    public GameOfLife(int height, int width) {
        board = new GameBoard(height, width);
    }

    public GameBoard getBoard() {
        return board;
    }

    public void setBoard(GameBoard board) {
        this.board = board;
    }

    public void update() {
        board.updateBoard();
        snapshot++;
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

    public static void main(String[] args) {

        GameOfLife gol = new GameOfLife(9, 9);
        //  TODO: Implement setting from file, or some other modular way!
        try {
            gol.getBoard().setCellAt(3, 4, true);
            gol.getBoard().setCellAt(4, 3, true);
            gol.getBoard().setCellAt(4, 4, true);
            gol.getBoard().setCellAt(4, 5, true);
        } catch (InvalidDimensionException e) {
            e.printStackTrace();
        }
        System.out.println(gol);
        for (int i = 0; i < 12; i++) {
            gol.update();
            System.out.println(gol);
        }
    }
}