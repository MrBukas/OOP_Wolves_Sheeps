package gameobjects.board2;

import java.io.File;

public class Board {
    private BoardCell[] cells;
    final int size = BoardMethods.size;
    public Board(File boardFile) {
        cells = BoardMethods.readBoard(boardFile);
    }

    public BoardCell getCell(int height, int width){
        return cells[width + 8*height];
    }

    public BoardCell getCell(int index){
        return cells[index];
    }

    public BoardCell[][] getGameBoard(){
        BoardCell[][] board = new BoardCell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = cells[j + 8*i];
            }
        }
        return board;
    }

    public int getHeight(){
        return size;
    }

    public int getWidth(){
        return size;
    }
}
