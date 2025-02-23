package com.interview.models;

import com.interview.singleton.ScannerObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
    private String playerName;
    private PLAYER_TYPE playerType;
    private Symbol symbol;

    public Player(String playerName, char symbol) {
        this.playerName = playerName;
        this.symbol = new Symbol(symbol);
    }

    public Move makeMove(Board board){
        System.out.println("Enter the row");
        int row = Integer.parseInt(ScannerObject.getInstance().nextLine());
        System.out.println("Enter the column");
        int column = Integer.parseInt(ScannerObject.getInstance().nextLine());
        Cell cell = new Cell(row, column);
        return new Move(cell, this);
    }
}
