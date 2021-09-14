package gameobjects.board;

import gameobjects.units.Unit;
import gameobjects.units.UnitMethods;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class BoardMethods {
    private static final Map<CellState, String> cellStateCodeMap = Map.of(
            CellState.FREE,".",
            CellState.SHEEP, "S",
            CellState.WOLF,"W"
            );

    public static String getCellCoordinate(BoardCell cell){
        return cell.getLetterCoordinate() + cell.getNumberCoordinate() + "";
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
            assert scanner != null;//TODO создать альтернативный путь чтения доски
            String line = scanner.nextLine();
            for (int j = 0; j < cells[i].length; j++) {
                currentColor = (currentColor + 1) % colorCount;
                CellState state = null;
                char cellChar = line.charAt(j);
                switch (cellChar){
                    case '.': state = CellState.FREE; break;
                    case 'W': state = CellState.WOLF; break;
                    case 'S': state = CellState.SHEEP; break;
                }
                cells[i][j] = new BoardCell(i, j, state, colors[currentColor]);
            }
        }

        return new Board(cells);
    }

    public static String getCellStateCode(CellState state){
        return cellStateCodeMap.get(state);
    }

    public static void printBoard(Board board){
        BoardCell[][] cells = board.getGameBoard();
        for (BoardCell[] row : cells) {
            for (BoardCell cell : row) {
                System.out.print(getCellStateCode(cell.getState()));
            }
            System.out.println();
        }
    }


    public static boolean moveUnit(Board board,
                                   char letterStart, int numberStart,
                                   char letterEnd, int numberEnd){
        BoardCell startCell = board.getCell(letterStart, numberStart);
        BoardCell endCell = board.getCell(letterEnd, numberEnd);
        Unit unit = startCell.getUnit();
        if (UnitMethods.checkIfCanWalk(board,endCell,unit)){
            return false;
        }
        endCell.setUnit(startCell.getUnit());
        startCell.clearUnit();
        return true;

    }
}
