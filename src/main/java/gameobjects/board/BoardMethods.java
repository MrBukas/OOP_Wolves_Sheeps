package gameobjects.board;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BoardMethods {
    public static String getCellCoordinate(BoardCell cell){
        return cell.getLetterCoordinate() + cell.getNumberCoordinate();
    }

    public static Board readBoard(File boardData){
        BoardCell[][] cells = new BoardCell[8][8];
        //TODO заполнить cell-ы
        CellColor[] colors = CellColor.values();
        int colorCount = colors.length;
        int currentColor = 1;
        Scanner scanner = null;
        try {
            scanner = new Scanner(boardData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                currentColor = (currentColor + 1) % colorCount;
                CellState state = null;
                assert scanner != null;//TODO создать альтернативный путь чтения доски
                String line = scanner.nextLine();
                char cellChar = line.charAt(j);
                switch (cellChar){
                    case '.': state = CellState.FREE; break;
                    case 'W': state = CellState.WOLF; break;
                    case 'S': state = CellState.SHEEP; break;
                }
                cells[i][j] = new BoardCell(i, j, state, colors[currentColor]);
            }
        }

        Board board = new Board(cells);
        return board;
    }
}
