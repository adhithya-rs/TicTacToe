package com.interview.strategy.winningStrategy;

import com.interview.models.Board;
import com.interview.models.Cell;
import com.interview.models.Move;

import java.util.HashMap;

public class RowWinningStrategy implements WinningStrategy {
    private static HashMap<Integer, HashMap<Character,Integer>> map = new HashMap<>();

    @Override
    public boolean check(Board board, Move move) {
        Cell cell = move.getCell();
        int row = cell.getRow();
        char symbol = move.getPlayer().getSymbol().getSymbol();
        if(!map.containsKey(row)) {
            map.put(row, new HashMap<>());
        }
        HashMap<Character,Integer> currRowMap = map.get(row);
        if(!currRowMap.containsKey(symbol)) {
            currRowMap.put(symbol, 0);
        }

        currRowMap.put(symbol, currRowMap.get(symbol) + 1);

        return currRowMap.get(symbol) == board.getSize();
    }
}
