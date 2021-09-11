package gameobjects.board;

import java.io.File;

public class Board {
    private BoardCell[][] gameBoard;

    public Board(File boardData) {
        gameBoard = BoardMethods.readBoard(boardData).gameBoard;
    }

    public Board(BoardCell[][] boardCells){
        gameBoard = boardCells;
    }

    public BoardCell[][] getGameBoard() {
        return gameBoard;
    }
}
