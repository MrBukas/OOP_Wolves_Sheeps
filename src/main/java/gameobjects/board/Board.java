package gameobjects.board;

import java.io.File;

public class Board {
    private BoardCell[][] gameBoard;

    public Board(File boardData) {
        BoardMethods.readBoard(boardData);
    }

    public Board(BoardCell[][] boardCells){
        gameBoard = boardCells;
    }
}
