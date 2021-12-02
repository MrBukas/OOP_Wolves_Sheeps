package gameobjects.board2;

import gameobjects.board.CellState;

import java.io.File;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {
    private BoardCell[] cells2;
    private Map<Integer, BoardCell> cells;
    final int size = BoardMethods.size;
    public Board(File boardFile) {
        cells = BoardMethods.readBoard(boardFile);
    }

    public BoardCell getCell(int height, int width){
        return cells.get(width + 8*height);
    }

    public BoardCell getCell(int index){
        return cells.get(index);
    }

    public BoardCell[][] getGameBoard(){
        BoardCell[][] board = new BoardCell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = cells.get(j + 8*i);
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

    public BoardCell getSheepCell(){
        return cells
                .values()
                .stream()
                .filter(cell -> cell.getState() == CellState.SHEEP)
                .collect(Collectors.toList()).get(0);
    }
}
