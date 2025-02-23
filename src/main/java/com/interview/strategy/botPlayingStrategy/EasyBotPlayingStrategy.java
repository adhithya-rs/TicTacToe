package com.interview.strategy.botPlayingStrategy;

import com.interview.models.Board;
import com.interview.models.CELL_STATE;
import com.interview.models.Cell;
import com.interview.models.Move;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Move makeMove(Board board) {
        int size = board.getSize();

        for(int i=0;i<size; i++){
            for(int j=0;j<size;j++){
                Cell cell = board.getBoard().get(i).get(j);
                if(cell.getCellState().equals(CELL_STATE.EMPTY)){
                    return new Move(cell, null);
                }
            }
        }
        return null;
    }
}
