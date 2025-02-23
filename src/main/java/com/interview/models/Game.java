package com.interview.models;

import com.interview.strategy.winningStrategy.ColumnWinningStrategy;
import com.interview.strategy.winningStrategy.DiagonalWinningStrategy;
import com.interview.strategy.winningStrategy.RowWinningStrategy;
import com.interview.strategy.winningStrategy.WinningStrategy;
import lombok.Getter;
import lombok.Setter;

import java.util.*;


@Getter
@Setter
public class Game {
    private Board board;
    private GAME_STATE game_state;
    private String winner;
    private List<Player> players;
    private int nextPlayerIndex;
    private static List<WinningStrategy> winningStrategies = new ArrayList<>();
    private List<Move> moves;

    static{
        winningStrategies.add(new ColumnWinningStrategy());
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new DiagonalWinningStrategy());
    }

    private Game(int dimension, List<Player> playerList) {
        this.board = new Board(dimension);
        this.game_state = GAME_STATE.IN_PROGRESS;
        this.players = playerList;
        this.nextPlayerIndex = 0;
        this.winner = null;
        this.moves = new ArrayList<>();
    }

    public void displayBoard(){
        board.printBoard();
    }

    public void makeMove(){
        Player player = players.get(nextPlayerIndex);
        System.out.println(player.getPlayerName()+"'s turn");
        Move move = player.makeMove(board);

        if(!isValidMove(move)){
            System.out.println("Invalid move!! Please try again!");
            makeMove();
            return;
        }
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Cell cell = board.getBoard().get(row).get(col);
        cell.setCellState(CELL_STATE.FILLED);
        cell.setPlayer(player);
        Move finalMove = new Move(cell, player);
        nextPlayerIndex = (nextPlayerIndex + 1)%players.size();
        moves.add(finalMove);
        if(checkWinningStatus(board, finalMove)){
            winner = player.getPlayerName();
            game_state = GAME_STATE.ENDED;
        }else if(moves.size()== board.getSize()*board.getSize()){
            game_state = GAME_STATE.DRAW;
        }
    }

    private boolean checkWinningStatus(Board board, Move move) {
        for(WinningStrategy strategy : winningStrategies){
            if(strategy.check(board, move)){
                return true;
            }
        }
        return false;
    }


    private boolean isValidMove(Move move){
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        if(row<0 || row>=board.getSize() || col<0 || col>=board.getSize()){
            return false;
        }

        return board.getBoard().get(row).get(col).getCellState().equals(CELL_STATE.EMPTY);
    }

    //------------------------>Builder class below<--------------------------

    public static Builder getBuilder(){
        return new Builder();
    }

    public static class Builder{
        private int dimension;
        private List<Player> players;

        public Builder setDimension(int dimension){
            this.dimension = dimension;
            return this;
        }
        public Builder setPlayerList(List<Player> playerList){
            this.players = playerList;
            return this;
        }

        private boolean validateSymbols(){
            Set<Character> symbols = new HashSet<>();
            for(Player player : players){
                if(symbols.contains(player.getSymbol().getSymbol())){
                    System.out.println("Two players have same symbol");
                    return false;
                }else{
                    symbols.add(player.getSymbol().getSymbol());
                }
            }
            return true;
        }

        private boolean validateBotCount(){
            long botCount = players.stream().filter(a-> a.getPlayerType().equals(PLAYER_TYPE.BOT)).count();
            if(botCount>1){
                System.out.println("Bot count is greater than 1");
                return false;
            }
            return true;
        }

        private boolean validatePlayersCount(){
            if(players.size() >= dimension){
                System.out.println(players.size() + " too many players");
                return false;
            }
            return true;
        }

        private boolean validateGame(){
            return validateBotCount() && validatePlayersCount() && validateSymbols();
        }

        public Game build(){
            if(validateGame()){
                return new Game(dimension, players);
            }else{
                return null;
            }
        }
    }

}
