package com.interview.strategy.winningStrategy;

import com.interview.models.Board;
import com.interview.models.Move;

public interface WinningStrategy {

    boolean check(Board board, Move move);
}
