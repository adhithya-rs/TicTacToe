package com.interview.strategy.botPlayingStrategy;

import com.interview.models.Board;
import com.interview.models.Move;

public interface BotPlayingStrategy {

    Move makeMove(Board board);

}
