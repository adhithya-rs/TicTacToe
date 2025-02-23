package com.interview.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cell {
    private int row;
    private int col;
    private CELL_STATE cellState;
    private Player player;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.cellState = CELL_STATE.EMPTY;
    }
}
