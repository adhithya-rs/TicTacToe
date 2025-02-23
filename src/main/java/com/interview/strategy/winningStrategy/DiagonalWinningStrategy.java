package com.interview.strategy.winningStrategy;

import com.interview.models.Board;
import com.interview.models.Cell;
import com.interview.models.Move;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DiagonalWinningStrategy implements WinningStrategy {
    private static HashMap<Character, Integer> leftDiagonal = new HashMap<>();
    private static HashMap<Character, Integer> rightDiagonal = new HashMap<>();

    @Override
    public boolean check(Board board, Move move) {
        Cell cell = move.getCell();
        int row = cell.getRow();
        int col = cell.getCol();
        char symbol = move.getPlayer().getSymbol().getSymbol();
        boolean status = false;
        if(row == col){
            if(!leftDiagonal.containsKey(symbol)){
                leftDiagonal.put(symbol, 0);
            }
            leftDiagonal.put(symbol, leftDiagonal.get(symbol) + 1);
            status = leftDiagonal.get(symbol) == board.getSize();
        }

        if(row+col == board.getSize()-1){
            if(!rightDiagonal.containsKey(symbol)){
                rightDiagonal.put(symbol, 0);
            }
            rightDiagonal.put(symbol, rightDiagonal.get(symbol) + 1);
            status = status || rightDiagonal.get(symbol) == board.getSize();
        }


        return status;
    }
}
