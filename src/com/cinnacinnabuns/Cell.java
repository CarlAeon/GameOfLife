package com.cinnacinnabuns;

public class Cell {

    private Boolean value;

    public Cell() {
        this(false);
    }

    public Cell(Boolean value) {
        this.value = value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value ? "X" : "·";
    }

    public boolean evaluateNextState(int neighborCardinality) {
        if (value) {
            return neighborCardinality == 2 || neighborCardinality == 3;
        } else {
            return neighborCardinality == 3;
        }
    }
}
