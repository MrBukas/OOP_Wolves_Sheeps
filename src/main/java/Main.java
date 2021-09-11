import gameobjects.board.Board;
import gameobjects.board.BoardMethods;
import gameobjects.board.CellColor;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(new File("src/main/resources/board1.txt"));
        BoardMethods.printBoard(board);
    }
}
