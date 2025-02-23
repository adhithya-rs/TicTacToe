package com.interview.strategy.botPlayingStrategy;

import com.interview.models.Board;
import com.interview.models.CELL_STATE;
import com.interview.models.Cell;
import com.interview.models.Move;

import java.util.List;
import java.util.Random;

public class MediumBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Move makeMove(Board board) {
        int size = board.getSize();
        List<List<Cell>> grid = board.getBoard();

        // Check if the bot can win in the next move
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell cell = grid.get(i).get(j);
                if (cell.getCellState().equals(CELL_STATE.EMPTY)) {
                    cell.setCellState(CELL_STATE.BOT); // Temporarily mark as bot move
                    if (isWinningMove(board, cell)) {
                        return new Move(cell, null);
                    }
                    cell.setCellState(CELL_STATE.EMPTY); // Reset
                }
            }
        }

        // Check if opponent can win in the next move and block them
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell cell = grid.get(i).get(j);
                if (cell.getCellState().equals(CELL_STATE.EMPTY)) {
                    cell.setCellState(CELL_STATE.HUMAN); // Simulate opponent move
                    if (isWinningMove(board, cell)) {
                        cell.setCellState(CELL_STATE.EMPTY); // Reset
                        return new Move(cell, null);
                    }
                    cell.setCellState(CELL_STATE.EMPTY); // Reset
                }
            }
        }

        // Otherwise, pick a random available move
        return getRandomMove(board);
    }

    private boolean isWinningMove(Board board, Cell cell) {
        // Implement logic to check if placing a move in this cell wins the game
        return false;
    }

    private Move getRandomMove(Board board) {
        List<List<Cell>> grid = board.getBoard();
        int size = board.getSize();
        Random rand = new Random();

        while (true) {
            int row = rand.nextInt(size);
            int col = rand.nextInt(size);
            if (grid.get(row).get(col).getCellState().equals(CELL_STATE.EMPTY)) {
                return new Move(grid.get(row).get(col), null);
            }
        }
    }
}