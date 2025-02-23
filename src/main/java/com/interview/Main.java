package com.interview;

import com.interview.controller.GameController;
import com.interview.models.*;
import com.interview.singleton.ScannerObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        GameController gameController = new GameController();
        System.out.println("Enter the dimension of the board");
        Scanner sc= ScannerObject.getInstance();
        int dimension = Integer.parseInt(sc.nextLine());
        List<Player> players = getPlayers(dimension, sc);

        Game game= gameController.startGame(dimension, players);
        if(game!=null){
            while(gameController.getGameStatus(game).equals(GAME_STATE.IN_PROGRESS)){
                gameController.displayBoard(game);
                gameController.makeMove(game);
                System.out.println();
            }
            gameController.displayBoard(game);
            if(game.getGame_state().equals(GAME_STATE.DRAW)){
                System.out.println("Draw.");
            }else{
                System.out.println(game.getWinner()+" has won the game.");
            }
        }
        ScannerObject.close();
    }

    private static List<Player> getPlayers(int dimension, Scanner sc) {
        List<Player> players = new ArrayList<>();
        for(int i=1; i< dimension;i++){
            String name="";
            char symbol = '-';
            PLAYER_TYPE playerType;
            if(i==1){
                System.out.println("Do you want to play with bot? y/n");
                if(sc.nextLine().equals("y")){
                    System.out.println("Enter bot name");
                    name = sc.nextLine();
                    System.out.println("Enter bot's symbol");
                    symbol = sc.nextLine().charAt(0);
                    System.out.println("Enter bot's difficulty Level:\n1. EASY\n2. MEDIUM\n3. HARD");
                    int choice = Integer.parseInt(sc.nextLine());
                    DIFFICULTY_LEVEL difficultyLevel = null;
                    switch(choice){
                        case 1:
                            difficultyLevel = DIFFICULTY_LEVEL.EASY;
                            break;
                            case 2:
                                difficultyLevel = DIFFICULTY_LEVEL.MEDIUM;
                                break;
                                case 3:
                                    difficultyLevel = DIFFICULTY_LEVEL.HARD;
                                    break;
                                    default:
                                        System.out.println("Invalid difficulty level");
                                        break;
                    }
                    Player player = new Bot(name, symbol, difficultyLevel);
                    player.setPlayerType(PLAYER_TYPE.BOT);
                    players.add(player);
                    continue;
                }
            }
            System.out.println("Enter player-"+i+" name");
            name = sc.nextLine();
            System.out.println("Enter your symbol");
            symbol = sc.nextLine().charAt(0);
            Player player = new Player(name, symbol);
            player.setPlayerType(PLAYER_TYPE.HUMAN);
            players.add(player);
        }
        return players;
    }
}