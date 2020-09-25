package com.cinnacinnabuns;

import java.util.ArrayList;

public class GameBoard {

    private ArrayList<ArrayList<Cell>> boardArray = new ArrayList<>();
    private ArrayList<ArrayList<Cell>> tempBoardArray = new ArrayList<>();  //  NOTE: Used in calculations, like updateBoard()

    public GameBoard(int height, int width) {

        assert (height < 1 || width < 1);
        setupBoardArray(boardArray, height, width);
        setupBoardArray(tempBoardArray, height, width);
    }

    public Cell getCellAt(int indexH, int indexW) throws InvalidDimensionException {

        validateIndex(indexH, indexW);
        return boardArray.get(indexH).get(indexW);
    }

    //  TODO: Implement setting directly to cell, not Boolean
    public void setCellAt(int indexH, int indexW, Boolean isAlive) throws InvalidDimensionException {
        validateIndex(indexH, indexW);
        try {
            getCellAt(indexH, indexW).setValue(isAlive);
        } catch (InvalidDimensionException e) {
            e.printStackTrace();
        }
    }

    public int getNeighborCardinality(int indexH, int indexW) throws InvalidDimensionException {

        validateIndex(indexH, indexW);
        int cardinalitySum = 0;
        for (int y = indexH - 1; y <= indexH + 1; y++) {
            for (int x = indexW - 1; x <= indexW + 1; x++) {
                if (hasIndex(y, x) && !(y == indexH && x == indexW))     //  NOTE: I expect invalid indexes here, don't validate!
                    if (getCellAt(y, x).getValue())
                        cardinalitySum++;
            }
        }
        return cardinalitySum;
    }

    public void updateBoard() {

        for (ArrayList<Cell> boardLine : boardArray) {
            for (Cell cell : boardLine) {
                try {
                    int indexH = boardArray.indexOf(boardLine);
                    int indexW = boardLine.indexOf(cell);
                    int nc = getNeighborCardinality(indexH, indexW);
                    if (cell.getValue()) {
                        if (nc == 2 || nc == 3) {
                            getTempBoardArrayElement(indexH, indexW).revive();
                        } else {
                            getTempBoardArrayElement(indexH, indexW).kill();
                        }
                    } else {
                        if (nc == 3) {
                            getTempBoardArrayElement(indexH, indexW).revive();
                        } else {
                            getTempBoardArrayElement(indexH, indexW).kill();
                        }
                    }
                } catch (InvalidDimensionException e) {
                    System.out.println("Unexpected indexing exception encountered while updating board!");
                    e.printStackTrace();
                }
            }
        }
        swapBoards();
    }

    private void swapBoards() {
        ArrayList<ArrayList<Cell>> boardArray = this.boardArray;
        this.boardArray = tempBoardArray;
        tempBoardArray = boardArray;
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

    private boolean hasIndex(int height, int width) {
        return height >= 0 && height <= boardArray.size() - 1
                && width >= 0 && width <= boardArray.get(0).size() - 1;
    }

    private void validateIndex(int height, int width) throws InvalidDimensionException {
        if (!hasIndex(height, width))
            throw new InvalidDimensionException();
    }

    private Cell getTempBoardArrayElement(int indexH, int indexW) {
        return tempBoardArray.get(indexH).get(indexW);
    }

    private static void setupBoardArray(ArrayList<ArrayList<Cell>> boardArray, int height, int width) {

        assert boardArray != null;
        boardArray.clear();
        for (int h = 0; h < height; h++) {
            boardArray.add(new ArrayList<>());
        }
        for (ArrayList<Cell> boardLine : boardArray) {
            for (int w = 0; w < width; w++) {
                boardLine.add(new Cell());
            }
        }
    }
}

class InvalidDimensionException extends Exception {
}
