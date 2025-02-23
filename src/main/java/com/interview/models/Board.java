package com.interview.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Board {
    private List<List<Cell>> board;
    private int size;

    public Board(int dimension) {
        this.size = dimension;
        this.board = new ArrayList<List<Cell>>();
        for(int i=0;i<dimension;i++){
            List<Cell> cells = new ArrayList<>();
            for(int j=0;j<dimension;j++){
                Cell cell = new Cell(i, j);
                cells.add(cell);
            }
            this.board.add(cells);
        }
    }

    public void printBoard(){
        for(int i=0;i<this.size;i++){
            for(int j=0;j<this.size;j++){
                if(board.get(i).get(j).getCellState().equals(CELL_STATE.EMPTY)){
                    System.out.print("|  |");
                }else{
                    System.out.print("| "+board.get(i).get(j).getPlayer().getSymbol().getSymbol()+"|");
                }
            }
            System.out.println();
        }
    }
}
