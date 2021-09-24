package gameobjects.board;

import gameengine.position.BoardConverter;
import gameengine.position.Coordinate;
import gameobjects.units.Unit;
import gameobjects.units.UnitMethods;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class BoardMethods {
    private static final Map<CellState, String> cellStateCodeMap = Map.of(
            CellState.FREE," ",
            CellState.SHEEP, "S",
            CellState.WOLF,"W"
            );

    public static String getCellCoordinate(BoardCell cell){
        return cell.getLetterCoordinate() + cell.getNumberCoordinate() + "";
    }

    public static Board readBoard(File boardData){
        BoardCell[][] cells = new BoardCell[8][8];
        CellColor[] colors = CellColor.values();
        int colorCount = colors.length;
        int currentColor = 1;
        Scanner scanner = null;
        try {
            scanner = new Scanner(boardData);
        } catch (FileNotFoundException e) {
            return null;
        }

        for (int i = 0; i < cells.length; i++) {
//            assert scanner != null;
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
            currentColor--;
        }

        return new Board(cells);
    }

    public static String getCellStateCode(CellState state){
        return cellStateCodeMap.get(state);
    }

    public static String getCellColor(BoardCell cell){
        final String BLACK = "\u001B[40m";
        final String WHITE= "\u001B[47m";
        switch (cell.getColor()){
            case BLACK: return BLACK;
            case WHITE: return WHITE;
        }
        final String RESET = "\u001B[0m";
        return RESET;
    }



    public static void printBoard(Board board){
        final String RESET = "\u001B[0m";
        BoardCell[][] cells = board.getGameBoard();
        for (BoardCell[] row : cells) {
            for (BoardCell cell : row) {
                System.out.print(getCellColor(cell) + " " +  getCellStateCode(cell.getState()) + " ");
            }
            System.out.println(RESET);
        }
        System.out.println(RESET);
    }


    public static boolean moveUnit(Board board,
                                   Coordinate startCoordinate,
                                   Coordinate endCoordinate){
        BoardCell startCell = board.getCell(
                BoardConverter.boardNumberToHeight(startCoordinate.getNumber()),
                BoardConverter.boardLetterToWidth(startCoordinate.getLetter())
                );
        BoardCell endCell = board.getCell(
                BoardConverter.boardNumberToHeight(endCoordinate.getNumber()),
                BoardConverter.boardLetterToWidth(endCoordinate.getLetter())
        );
        Unit unit = startCell.getUnit();
        endCell.setUnit(unit);
        startCell.clearUnit();
        return true;

    }
}
