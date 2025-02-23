package com.interview.strategy.botPlayingStrategy;

import com.interview.models.Board;
import com.interview.models.CELL_STATE;
import com.interview.models.Cell;
import com.interview.models.Move;

import java.util.List;

public class HardBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Move makeMove(Board board) {
        int bestScore = Integer.MIN_VALUE;
        Move bestMove = null;
        int size = board.getSize();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell cell = board.getBoard().get(i).get(j);
                if (cell.getCellState().equals(CELL_STATE.EMPTY)) {
                    cell.setCellState(CELL_STATE.BOT); // Try the move
                    int score = minimax(board, 0, false);
                    cell.setCellState(CELL_STATE.EMPTY); // Undo the move

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new Move(cell, null);
                    }
                }
            }
        }

        return bestMove;
    }

    private int minimax(Board board, int depth, boolean isMaximizing) {
        int score = evaluateBoard(board);
        if (score == 10 || score == -10 || isBoardFull(board)) {
            return score;
        }

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int size = board.getSize();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell cell = board.getBoard().get(i).get(j);
                if (cell.getCellState().equals(CELL_STATE.EMPTY)) {
                    cell.setCellState(isMaximizing ? CELL_STATE.BOT : CELL_STATE.HUMAN);
                    int currentScore = minimax(board, depth + 1, !isMaximizing);
                    cell.setCellState(CELL_STATE.EMPTY);

                    if (isMaximizing) {
                        bestScore = Math.max(bestScore, currentScore);
                    } else {
                        bestScore = Math.min(bestScore, currentScore);
                    }
                }
            }
        }
        return bestScore;
    }

    private int evaluateBoard(Board board) {
        // Implement logic to evaluate board and return score
        return 0;
    }

    private boolean isBoardFull(Board board) {
        for (List<Cell> row : board.getBoard()) {
            for (Cell cell : row) {
                if (cell.getCellState().equals(CELL_STATE.EMPTY)) {
                    return false;
                }
            }
        }
        return true;
    }
}

