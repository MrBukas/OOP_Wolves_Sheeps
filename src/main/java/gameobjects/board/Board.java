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

    public BoardCell getCell(int height, int width){
        return gameBoard[height][width];//TODO Проверить правильно ли используется в других местах
    }

    public int getHeight(){
        return gameBoard.length;
    }

    public int getWidth(){
        return gameBoard[0].length;
    }
}
