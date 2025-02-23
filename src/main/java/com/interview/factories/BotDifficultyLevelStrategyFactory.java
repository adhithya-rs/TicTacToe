package com.interview.factories;

import com.interview.models.DIFFICULTY_LEVEL;
import com.interview.strategy.botPlayingStrategy.BotPlayingStrategy;
import com.interview.strategy.botPlayingStrategy.EasyBotPlayingStrategy;
import com.interview.strategy.botPlayingStrategy.HardBotPlayingStrategy;
import com.interview.strategy.botPlayingStrategy.MediumBotPlayingStrategy;

public class BotDifficultyLevelStrategyFactory {

    public static BotPlayingStrategy getBotPlayingStrategy(DIFFICULTY_LEVEL difficulty) {
        switch (difficulty) {
            case HARD:
                return new HardBotPlayingStrategy();
                case EASY:
                    return new EasyBotPlayingStrategy();
                    case MEDIUM:
                        return new MediumBotPlayingStrategy();
        }
        return null;
    }
}
