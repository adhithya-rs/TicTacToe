package com.interview.models;

import com.interview.factories.BotDifficultyLevelStrategyFactory;
import com.interview.singleton.ScannerObject;
import com.interview.strategy.botPlayingStrategy.BotPlayingStrategy;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bot extends Player{
    private DIFFICULTY_LEVEL difficulty;
    private BotPlayingStrategy strategy;

    public Bot(String playerName, char symbol, DIFFICULTY_LEVEL difficulty) {
        super(playerName, symbol);
        this.difficulty = difficulty;
        this.strategy = BotDifficultyLevelStrategyFactory.getBotPlayingStrategy(difficulty);
    }

    @Override
    public Move makeMove(Board board){
        Move move = strategy.makeMove(board);
        move.setPlayer(this);
        return move;
    }
}
