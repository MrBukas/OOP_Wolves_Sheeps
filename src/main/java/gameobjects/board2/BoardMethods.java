package gameobjects.board2;

import gameengine.position.BoardConverter;
import gameengine.position.Coordinate;
import gameobjects.board.CellColor;
import gameobjects.board.CellState;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class BoardMethods {
    final static int size = 8;
    public static Map<Integer, BoardCell> readBoard(File boardData){
        Scanner scanner = null;
        CellColor[] colors = CellColor.values();
        int colorCount = colors.length;
        int currentColor = 0;
        try {
            scanner = new Scanner(boardData);
        } catch (FileNotFoundException e) {
            return null;
        }
        //BoardCell[] cells = new BoardCell[size * size];
        Map<Integer, BoardCell> cells = new TreeMap<>();
        for (int i = 0; i < size; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < size; j++) {
                currentColor = (currentColor + 1) % colorCount;
                CellState state = null;
                char cellChar = line.charAt(j);
                switch (cellChar){
                    case '.': state = CellState.FREE; break;
                    case 'W': state = CellState.WOLF; break;
                    case 'S': state = CellState.SHEEP; break;
                }
                int index = j + 8*i;
                //cells[index] = new BoardCell(state, colors[currentColor], index);
                cells.put(index, new BoardCell(state, colors[currentColor], index));
                //cells[index].setAdjacentCells(setAdjacentCells(index));
                cells.get(index).setAdjacentCells(setAdjacentCells(index));
            }
            currentColor--;
        }
        return cells;
    }
    private static final Map<CellState, String> cellStateCodeMap = Map.of(
            CellState.FREE," ",
            CellState.SHEEP, "S",
            CellState.WOLF,"W"
    );




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
                System.out.print(getCellColor(cell) + " " + "\u001B[30m" + getCellStateCode(cell.getState()) + " ");
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
        CellState unit = startCell.getState();
        endCell.setState(unit);
        startCell.clearUnit();
        return true;

    }

    private static int[] setAdjacentCells(int id){
        int[] difs = {-9,-8,-7,-1,1,7,8,9};
        int[] adj = new int[8];
        for (int i = 0; i < 8; i++) {
            adj[i] = id + difs[i];
        }
//        Правый борт
        if (id % 8 == 7){
            adj[2] = -1;
            adj[4] = -1;
            adj[7] = -1;
        }
//        Левый борт
        if (id % 8 == 0){
            adj[0] = -1;
            adj[3] = -1;
            adj[5] = -1;
        }
//        Верхний борт
        if (id < 8){
            adj[0] = -1;
            adj[1] = -1;
            adj[2] = -1;
        }
//        Нижний борт
        if (id > 55){
            adj[5] = -1;
            adj[6] = -1;
            adj[7] = -1;
        }
        return adj;
    }

    public static void main(String[] args) {
        readBoard(new File("src/main/resources/board1.txt"));
    }
}
