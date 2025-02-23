package com.interview.strategy.winningStrategy;

import com.interview.models.Board;
import com.interview.models.Cell;
import com.interview.models.Move;

import java.util.HashMap;

public class ColumnWinningStrategy implements WinningStrategy {
    private static HashMap<Integer, HashMap<Character,Integer>> map = new HashMap<>();

    @Override
    public boolean check(Board board, Move move) {
        Cell cell = move.getCell();
        int col = cell.getCol();
        char symbol = move.getPlayer().getSymbol().getSymbol();
        if(!map.containsKey(col)) {
            map.put(col, new HashMap<>());
        }
        HashMap<Character,Integer> currColMap = map.get(col);
        if(!currColMap.containsKey(symbol)) {
            currColMap.put(symbol, 0);
        }

        currColMap.put(symbol, currColMap.get(symbol) + 1);

        return currColMap.get(symbol) == board.getSize();
    }
}
