package com.interview.controller;

import com.interview.models.GAME_STATE;
import com.interview.models.Game;
import com.interview.models.Player;

import java.util.List;

public class GameController {


    public Game startGame(int dimension, List<Player> players) {
        return Game.getBuilder().setDimension(dimension).setPlayerList(players).build();
    }

    public void displayBoard(Game game) {
        game.displayBoard();
    }

    public GAME_STATE getGameStatus(Game game) {
        return game.getGame_state();
    }

    public void makeMove(Game game){
        game.makeMove();
    }


}
